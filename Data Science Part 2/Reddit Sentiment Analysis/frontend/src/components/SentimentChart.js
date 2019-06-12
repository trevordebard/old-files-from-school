import React, { useState, useEffect } from 'react'
import { Line } from 'react-chartjs-2';
import { DateRangePicker } from "@blueprintjs/datetime";
import { Checkbox, Card, H3 } from "@blueprintjs/core";

import axios from 'axios'
const grid = {
	display: 'flex',
	margin: '10px',
	justifyContent: 'center'
	
}
const options = {
	scales: {
	  yAxes: [{
		scaleLabel: {
		  display: true,
		  labelString: 'Sentiment (1: positive, 0: neutral, -1: negative)'
		}
	  }],
	  xAxes: [{
		scaleLabel: {
		  display: true,
		  labelString: 'Time (<month>, <day>, <hour>)'
		}
	  }],
	},
	title: {
		display: true,
		text: "Vader Sentiment Analysis of Reddit Comments"
	   }
  }

function SentimentChart(props) {
	const [datasets, setDataSets] = useState([]);
	const [labels, setLabels] = useState([]);
	const [chartVisible, setChartVisible] = useState(false)
	
	const [startDate, setStartDate] = useState(new Date(2019, 3, 10))
	const [endDate, setEndDate] = useState(new Date(2019, 3, 13, 11))

	const [chartColors, setChartColors] = useState([
		'#e41a1c',
		'#377eb8',
		'#4daf4a',
		'#984ea3',
		'#ff7f00',
		'#a65628',
		'#bcbc38',
		'#f781bf'
	])

	useEffect(() => {
		// If the start date or end date changes, this will empty the whole table
		if(chartVisible) {
			if(startDate !== null && endDate !== null) {
				setChartVisible(false)
				setDataSets([])
				setLabels([])
				const checks = document.querySelectorAll('div#check-boxes input[type=checkbox]')
				checks.forEach(item => {
					if(item.checked) {
						item.checked = false
					}
				})
			}
		}
	}, [startDate, endDate])
	
	const getData = async (table="vader_trump", start="4_5_0", end="4_10_2", limit="100000") => {
		return axios.get(`http://24.248.167.51:5000/averages?table=${table.toLowerCase()}&start_time=${start}&end_time=${end}`)
			.then((response) => {
				return response
			})
			.catch((error) => {
				console.log(error);
			});
	}

	const getRandomColor = () => {
		const random = chartColors[Math.floor(Math.random()*chartColors.length)]
		const filteredItems = chartColors.filter((item) => {
		  return item !== random
		})
		setChartColors(filteredItems)
		return random;
	}

	const data1 = {
		labels,
		datasets,
	};
	const formatTimeForRequest = () => {
		let startMonth = (startDate.getMonth()+1).toString();
		let startDay = startDate.getDate().toString();
		let startHours = startDate.getHours().toString();

		let endMonth = (endDate.getMonth()+1).toString();
		let endDay = endDate.getDate().toString();
		let endHours = endDate.getHours().toString();

		let start = `${startMonth}_${startDay}_${startHours}`
		let end = `${endMonth}_${endDay}_${endHours}`
		return {start, end}
	}

	const addValueToTable = async (tableName) => {
		const { start, end } = formatTimeForRequest()
		const response = await getData(tableName, start, end)
		
		let values = response.data.data;
		console.log(response)
		let ret = {
			data: values,
			label: tableName,
			borderColor: `${getRandomColor()}`,
			fill: false
		}
		setDataSets([...datasets, ret])
		setLabels(response.data.labels)
		setChartVisible(true)
	}
	const removeValueFromTable = (tableName) => {
		let color = ''
		let ret = datasets.filter(({ label, borderColor }) => {
			color = borderColor;
			return label !== tableName;
		})
		setDataSets(ret)
		setChartColors([...chartColors, color])
	}

	const handleItemChecked = (e) => {
		if(e.target.checked) {
			addValueToTable(e.target.value)
		} else {
			removeValueFromTable(e.target.value)
		}
		
	}
	return (
		<div>
			<div style={{margin: "auto", width: "min-content"}}>
				<DateRangePicker
					singleMonthOnly
					value={[startDate, endDate]}
					shortcuts={false}
					timePrecision={"minute"}
					allowSingleDayRange={true}
					onChange={(arr) => {
						setStartDate(arr[0])
						setEndDate(arr[1])
					}}
					minDate={new Date(2019, 3, 10)}
					maxDate={new Date(2019, 3, 17, 11)}
				/>
			</div>
			<div id="check-boxes" onChange={handleItemChecked} style={grid}>
				<Card interactive={true} elevation={2}>
					<H3>Vader</H3>
					<Checkbox label="Trump" value="vader_trump"/>
					<Checkbox label="Warren" value="vader_warren"/>
					<Checkbox label="Sanders" value="vader_sanders"/>
					<Checkbox label="Biden" value="vader_biden"/>
					<Checkbox label="Harris"value="vader_harris"/>
					<Checkbox label="Beto"value="vader_beto"/>
					<Checkbox label="Buttigieg"value="vader_buttigieg"/>
					</Card>
					<Card interactive={true} elevation={2}>
					<H3>MLlib</H3>
					<Checkbox label="Trump" value="mllib_trump"/>
					<Checkbox label="Warren" value="mllib_warren"/>
					<Checkbox label="Sanders" value="mllib_sanders"/>
					<Checkbox label="Biden" value="mllib_biden"/>
					<Checkbox label="Harris" value="mllib_harris"/>
					<Checkbox label="Beto" value="mllib_beto"/>
					<Checkbox label="Buttigieg" value="mllib_buttigieg"/>
					</Card>
					<Card interactive={true} elevation={2}>
					<H3>MLLib (rolling)</H3>
					<Checkbox label="Trump" value="mllib_rolling_trump"/>
					<Checkbox label="Warren" value="mllib_rolling_warren"/>
					<Checkbox label="Sanders" value="mllib_rolling_sanders"/>
					<Checkbox label="Biden" value="mllib_rolling_biden"/>
					<Checkbox label="Harris" value="mllib_rolling_harris"/>
					<Checkbox label="Beto" value="mllib_rolling_beto"/>
					<Checkbox label="Buttigieg" value="mllib_rolling_buttigieg"/>
				</Card>
			</div>
				
			{
				chartVisible && (
					<div style={{margin: '10px'}}>
						<Line data={data1} options={options}/>
						<h3>Notes:</h3>
						<ul>
							<li>All times are local</li>
							<li>It may seem odd that some data points are as high as 1 or as low as -1. Because we average all comments of a particular hour, if there is a time where only 3 comments mention a word, data is more likely to be extreme since there are fewer data points</li>
						</ul>
					</div>
				)
			}
		</div>
	)
}

export default SentimentChart

