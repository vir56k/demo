import './App.css';
import React, {useState ,PureComponent } from 'react';
import Reflv from '@/components/video/index';
import { Button, Layout, Image } from 'antd';
import 'antd/dist/antd.css';

// const URL = "http://localhost:3000/movie.flv";
const URL = "http://localhost:8080/live/livestream.flv";

class VideoPlay extends React.Component {
  constructor(props) {
    super(props);
    this.state = {url: props.url};
  }

  componentWillReceiveProps (nextProps) {
    console.log("nextProps = ",nextProps.url)
    this.setState({
      url: nextProps.url
    });
  }

  render() {
    return (
      <div>
        <p>url is:{this.state.url}</p>
        <Reflv key={this.state.url} url={this.state.url} type="flv"  cors config={{
                enableWorker: true,
                enableStashBuffer: false,
                stashInitialSize: 128,
              }}>
        </Reflv>
      </div>
    );
  }
}

let count = 0;

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {url: ""};
  }

  onBtnClcik(){
    count++;
    if(count%2 == 1){
      this.setState({
        url: URL
      });
    }else{
      this.setState({
        url: ""
      });
    }
    console.log("onBtnClcik");
  };

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <p>
            播放器示例
          </p>
          <Button type="primary" onClick={() => this.onBtnClcik()}>点击播放</Button>

          <VideoPlay key={this.state.url} url={this.state.url}/>

        </header>
      </div>
    );
  }
}

export default App;
