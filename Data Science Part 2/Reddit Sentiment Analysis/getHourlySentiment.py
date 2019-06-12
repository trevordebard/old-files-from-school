import json
import happybase

def getGraphData(tableName,startDate,endDate):
	dict = {}
	startKey = tableName+startDate
	endKey = tableName+endDate
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	connection.open()
	table = connection.table('vader_averages')
	for key, data in table.scan(row_start=startKey, row_stop=endKey):
		tmp = key.decode('utf8')
		dict[tmp] = {key.decode('utf8'): val.decode('utf8') for key, val in data.items() }
	sentVals = []
	dateVals = []
	tableVals = []
	for key, value in dict.items():
		table_name = key.split("(")[0]
		time = "("+key.split("(")[1]
		sentVals.append(data[b'averages:sentiment'].decode('utf8'))
		dateVals.append(time)
		tableVals.append(table_name)
	return json.dumps({'lables' : dateVals ,'data' : sentVals })
if __name__ == '__main__':
	json = getGraphData('vader_beto','(4, 4, 16)','(4, 5, 9)')
	print(json)

