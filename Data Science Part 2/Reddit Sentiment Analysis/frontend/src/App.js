import React, { Component } from 'react';
import { H1 } from "@blueprintjs/core";
import './App.css';
import SentimentChart from './components/SentimentChart.js'
class App extends Component {
  render() {
    return (
      <div className="App">
        <H1 style={{margin: 'auto', width: 'min-content'}}>Pulse</H1>
        <SentimentChart/>
      </div>
    );
  }
}

export default App;
