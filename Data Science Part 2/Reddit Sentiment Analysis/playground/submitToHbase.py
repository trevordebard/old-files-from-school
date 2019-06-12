from kafka import KafkaConsumer
import json
import happybase
import time
queue = {}
def ingestScoredComments(target_words):
	create_nonexistant_tables(target_words)
	
	consumer = KafkaConsumer('reddit_comments', group_id='reddit_group', bootstrap_servers=['192.168.1.6:9092'])
	for message in consumer:
		parsed = json.loads(message.value.decode("utf8"))
		subreddit = parsed['subreddit']
		
		# We only want to insert into hbase if a target word is in the body
		# Iterate through the returned set in case the body contains multiple target words
		# for example a comment that mentions "bernie" and "warrent" would get stored twice
		for word in words_in_string(target_words, parsed['body']):
			print(word + ' exists in ' + parsed['body'])
			table_name = get_table_name(word)
			add_to_batch(parsed, table_name)

def create_nonexistant_tables(target_words):
	# create table for each target word if the table does not exist
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	for word in target_words:
		# this has to be inside the loop to get an updated list of tables each time
		tables = [str(i.decode('utf8')) for i in connection.tables()]
		table_name = get_table_name(word)
		if table_name not in tables:
			families = { 'comments': dict() }
			connection.create_table(table_name, families)
	
def add_to_batch(parsed, table_name):
	if table_name in queue:
		queue[table_name].append(parsed)
	else:
		queue[table_name] = [parsed]
	
def check_batch():
	for key in queue.keys():
		if len(queue[key]) > 10:
			batch_insert(key)

def batch_insert(key):
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	connection.open()
	try:
		for parsed in queue[key]:
			# generate a unique row id that will be easy to parse if necessary
			row_id = parsed['time'] +  '--' + str(parsed['id'])
			table = connection.table(key)
			b = table.batch()
			print(table_name)
			values = {
				b'comments:author': bytes(parsed['author'], encoding='utf8'),
				b'comments:body': bytes(parsed['body'], encoding='utf8'),
				b'comments:id': parsed['id'],
				b'comments:time': bytes(parsed['time'], encoding='utf8'),
				b'comments:subreddit': bytes(parsed['subreddit'], encoding='utf8'),
				b'comments:upvotes': bytes(str(parsed['upvotes']), encoding='utf8'),
				b'comments:stickied': bytes(parsed['stickied'], encoding='utf8'),
				b'comments:sentiment': bytes(str(parsed['sentiment']), encoding='utf8')
			}

			b.put(bytes(row_id, encoding='utf-8'), values)
	except:
		print('-----------ERROR-------------')
	finally:
		connection.close()


def insert_into_hbase(parsed, table_name):
	
	connection = happybase.Connection('192.168.1.6',port=9090,transport='framed',protocol='compact')
	connection.open()
	try:

		# generate a unique row id that will be easy to parse if necessary
		row_id = parsed['time'] +  '--' + str(parsed['id'])
		table = connection.table(table_name)
		print(table_name)
		values = {
			b'comments:author': bytes(parsed['author'], encoding='utf8'),
			b'comments:body': bytes(parsed['body'], encoding='utf8'),
			b'comments:id': parsed['id'],
			b'comments:time': bytes(parsed['time'], encoding='utf8'),
			b'comments:subreddit': bytes(parsed['subreddit'], encoding='utf8'),
			b'comments:upvotes': bytes(str(parsed['upvotes']), encoding='utf8'),
			b'comments:stickied': bytes(parsed['stickied'], encoding='utf8'),
			b'comments:sentiment': bytes(str(parsed['sentiment']), encoding='utf8')
		}

		print(values)
		table.put(bytes(row_id, encoding='utf-8'), values)
	except:
		print('-----------ERROR-------------')
	finally:
		connection.close()
	
def get_table_name(word):
	word = word.replace(' ', '_').lower()
	ret = ''
	if word == 'msu':
		ret = 'vader_michigan_state'
	elif word == 'bernie':
		ret = 'vader_sanders'
	elif word == 'pete':
		ret = 'vader_buttigieg'
	elif word == "o'rourke":
		ret = 'vader_beto'
	else:
		ret = 'vader_'+word
	return ret

#https://stackoverflow.com/questions/21718345/python-how-to-determine-if-a-list-of-words-exist-in-a-string
def words_in_string(word_list, a_string):
    return set(word_list).intersection(a_string.split())


if __name__=="__main__":
	target_words = ['Auburn', 'Virginia', 'Michigan State', 'MSU', 'Texas Tech', 'Bernie', 'Sanders', 'Trump', 'Harris', 'Buttigieg', 'Beto', "O'Rourke", 'Warren', 'Biden']
	ingestScoredComments(target_words)
