import json
import happybase
from collections import OrderedDict
from natsort import natsorted
def getGraphData(tableName,startDate,endDate):
	dict = {}
	startKey = tableName
	endKey = tableName
	splitEndKey = endKey.split('_')

	# Because hbase rows are stored alphabetically, we need to get the entire row for the candidate

	# This would make the end key for vader_beto be 'vader_cf'
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	connection.open()

	if splitEndKey[1]=="rolling":
		startKey = splitEndKey[0] + '_' + splitEndKey[2]
		print(startKey)
		endKey = splitEndKey[0] + '_' + splitEndKey[2][0] + chr(ord(splitEndKey[2][1]) + 1)
		table = connection.table(splitEndKey[0]+'_rolling_averages')
	else:
		endKey = splitEndKey[0] + '_' + splitEndKey[1][0] + chr(ord(splitEndKey[1][1]) + 1)
		table = connection.table(splitEndKey[0]+'_averages')

	for key, data in table.scan(row_start=startKey, row_stop=endKey):
		tmp = key.decode('utf8')
		print(tmp)
		dict[tmp] = {key.decode('utf8'): val.decode('utf8') for key, val in data.items() }
	sentVals = []
	dateVals = []
	tableVals = []
	dict = OrderedDict(natsorted(dict.items()))
	try:
		startIndex = list(dict.keys()).index(tableName+startDate)
	except:
		startIndex = 0
	for index, (key, value) in enumerate(dict.items()):
		if index >= startIndex:
			table_name = key.split("(")[0]
			time = "("+key.split("(")[1]
			sentVals.append(value['averages:sentiment'])
			dateVals.append(time)
			tableVals.append(table_name)

			if (key) == (tableName+endDate):
				break
		
	return json.dumps({'labels' : dateVals ,'data' : sentVals })
if __name__ == '__main__':
	json = getGraphData('vader_beto','(4, 4, 16)','(4, 5, 9)')
	print(json)

