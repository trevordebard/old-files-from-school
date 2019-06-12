import axios from 'axios'

export async function getSentiment(limit) {
    axios.get('http://192.168.1.6:5000/scan?table=vader_trump&start_time=04/06/2019:00&limit=1000')
        .then(function (response) {
            // handle success
            return response.data
        })
        .catch(function (error) {
            // handle error
            console.log(error);
            return 'error'
        })
        .then(function () {
            // always executed
        });
}


