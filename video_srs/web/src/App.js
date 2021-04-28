import './App.css';
import Reflv from 'reflv';

function VideoPlay(){
  return (
    <Reflv url="{`http://localhost:8080/live/livestream.flv`}" type="flv" islive="" cors="">
    </Reflv>
  );
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          播放器示例
        </p>
        <VideoPlay/>
      </header>
    </div>
  );
}

export default App;
