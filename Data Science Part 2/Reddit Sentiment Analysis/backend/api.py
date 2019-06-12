#!flask/bin/python
import json
import happybase
import datetime
import pandas as pd
from flask_cors import CORS, cross_origin
from flask import Flask, request
from collections import OrderedDict
from getHourlySentiment import getGraphData
app = Flask(__name__)

cors = CORS(app)
app.config['CORS_HEADERS'] = 'Content-Type'


@app.route('/')
def index():
    return "Hello, World!"

@app.route('/tables')
def tables():
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	tables = [str(i.decode('utf8')) for i in connection.tables()]
	connection.close()
	return json.dumps({'tables': tables})		

@app.route('/averages')
@cross_origin()
def averages():
	table_name = request.args.get('table', default=None)
	start_time = request.args.get('start_time', default=None)
	end_time  = request.args.get('end_time', default=None)

	start_time = "(" + start_time.replace("_", ", ") + ")"
	end_time = "(" + end_time.replace("_", ", ") + ")"
	json = getGraphData(table_name, start_time, end_time)
	#json = getGraphData('vader_beto','(4, 4, 16)','(4, 5, 9)')
	return json

	


def to_xy_pairs(data):
	ret = []
	for key, value in data.items():
		ret.append({"x": key, "y": value})
	return ret


if __name__ == '__main__':
    app.run(debug=True)
