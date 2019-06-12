import praw
import json
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
from kafka import KafkaProducer

def format_comment(comment):
	sentiment = sentiment_analyzer_scores(comment.body)
	formattedCom = {
		'author': str(comment.author),
		'body': comment.body,
		'id': comment.id,
		'time': str(comment.created_utc),
		'subreddit': str(comment.subreddit),
		'upvotes': comment.score,
		'stickied': str(comment.stickied),
		'sentiment': sentiment
	}
	#for k,v in formattedCom.items():
	#	formattedCom[k] = v.encode('utf-8')

	return formattedCom

def get_reddit_comments(subreddit):
	try:
		producer = KafkaProducer(bootstrap_servers='192.168.1.6:9092')
		reddit = praw.Reddit(client_id='fhN7b81fEY_LkQ',
				client_secret='BoZClqgDo5Ed7BpxKE-M7OkO4YU',
				user_agent='testing script')

		comments=0
	
		for comment in reddit.subreddit('politics'+'+CollegeBasketball'+'+politicaldiscussion').stream.comments():
			com=format_comment(comment)
			msg = producer.send('reddit_comments', json.dumps(com).encode('utf-8'))
			print(json.dumps(com)+'\n')
		producer.flush()
	except Exception as e:
		print(str(type(e)) + '     ' + str(e))
	producer.flush()
	return subreddit


# https://towardsdatascience.com/almost-real-time-twitter-sentiment-analysis-with-tweep-vader-f88ed5b93b1c
def sentiment_analyzer_scores(text):
    	analyzer = SentimentIntensityAnalyzer()
    	score = analyzer.polarity_scores(text)
    	sent = score['compound']
    	if sent >= 0.05:
        	return 1
    	elif (sent > -0.05) and (sent < 0.05):
        	return 0
    	else:
        	return -1

if __name__=="__main__":
	get_reddit_comments('cfb')
