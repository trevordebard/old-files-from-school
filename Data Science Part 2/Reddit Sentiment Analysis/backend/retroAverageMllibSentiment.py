from kafka import KafkaConsumer
import json
import happybase
import pandas as pd
import datetime
from datetime import timedelta
from collections import OrderedDict
from natsort import natsorted

def calculate_hourly_averages(table_name, timeOffset):
	ret = OrderedDict()
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	connection.open()
	try:
		table = connection.table(table_name)
		#average = connection.table("vader_averages")
		#start_time = datetime.datetime.strptime("04/04/2019:09", "%m/%d/%Y:%H").strftime('%s').encode()
		#Current time is end time will scan and average the whole table
		start_hour = datetime.datetime.now().replace(microsecond=0,second=0,minute=0) - timedelta(hours=timeOffset+1)
		start_time = start_hour.strftime('%s').encode()
		end_hour = datetime.datetime.now().replace(microsecond=0,second=0,minute=0) - timedelta(hours=timeOffset)
		end_time = end_hour.strftime('%s').encode()
		print("HOUR GROUP")
		print(start_hour)
		print(end_hour)
		filterString = "QualifierFilter (=, 'binary:time') OR QualifierFilter (=, 'binary:sentiment')"
		for key, data in table.scan(row_start=start_time, row_stop=end_time, filter=filterString):
			tmp = key.decode('utf8')
			ret[tmp] = {key.decode('utf8'): val.decode('utf8') for key, val in data.items() }	
		df = pd.DataFrame(ret).transpose()
		df['comments:time'] = df['comments:time'].apply(lambda input : datetime.datetime.fromtimestamp(float(input)).strftime("%Y-%m-%d %H:%M:%S"))	
		df.index = df['comments:time']
		df = df.drop(['comments:time'], axis=1)
		df['comments:sentiment'] = df['comments:sentiment'].apply(pd.to_numeric)
		df = df[~df.index.duplicated()]
		times = pd.to_datetime(df.index)
		groupedDf = df.groupby([times.month, times.day, times.hour]).mean()
		countDf = df.groupby([times.month, times.day, times.hour]).agg(['mean', 'count'])

		groupedDf = groupedDf.reindex(index=natsorted(groupedDf.index))
		dictValues = groupedDf['comments:sentiment'].to_dict(into=OrderedDict)
		#Open connection to vader_averages
		table = connection.table("mllib_averages")
		for key, value in dictValues.items():
			row_id = table_name+str(key)
			byte_value = {b'averages:sentiment' : bytes(str(value), encoding='utf8')}
			table.put(bytes(str(row_id), encoding='utf8'), byte_value)
		connection.close()
	except:
		connection.close()

if __name__ == '__main__':
	for x in range(276):
		calculate_hourly_averages("mllib_sanders",x)
		calculate_hourly_averages("mllib_beto",x)
		calculate_hourly_averages("mllib_trump",x)
		calculate_hourly_averages("mllib_biden",x)
		calculate_hourly_averages("mllib_buttigieg",x)
		calculate_hourly_averages("mllib_harris",x)
		calculate_hourly_averages("mllib_warren",x)

