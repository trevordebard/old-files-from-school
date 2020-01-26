import praw
import json
from kafka import KafkaProducer

def format_comment(comment):
	if comment.author_flair_css_class is None:
		comment.author_flair_css_class=' '

	formattedCom = {
		'author': str(comment.author),
		'body': comment.body,
		'flairs': comment.author_flair_css_class,
		'id': comment.id,
		'time': str(comment.created_utc),
		'subreddit': str(comment.subreddit),
		'upvotes': comment.score,
		'stickied': str(comment.stickied)
	}
	#for k,v in formattedCom.items():
	#	formattedCom[k] = v.encode('utf-8')

	return formattedCom

def get_reddit_comments(subreddit):
	producer = KafkaProducer(bootstrap_servers='192.168.1.6:9092')
	reddit = praw.Reddit(client_id='fhN7b81fEY_LkQ',
			client_secret='BoZClqgDo5Ed7BpxKE-M7OkO4YU',
			user_agent='testing script')

	comments=0
	try:
		for comment in reddit.subreddit(subreddit).stream.comments():
			com=format_comment(comment)
			msg = producer.send('reddit_comments', json.dumps(com).encode('utf-8'))
			print(json.dumps(com)+'\n')
		producer.flush()
	except Exception as e:
		print(str(type(e)) + '     ' + str(e))
	producer.flush()
	return subreddit

if __name__=="__main__":
	get_reddit_comments('CollegeBasketball')
