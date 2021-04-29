import './App.css';
import React, {useState ,PureComponent } from 'react';
import Reflv from 'reflv';
import { Button, Layout, Image } from 'antd';
import 'antd/dist/antd.css';


function VideoPlay(prop){
  const url = "http://localhost:3000/movie.flv";

  return (
    <div>
      <h1>url is:{url}</h1>
      <Reflv url={url} type="flv" isLive cors config={{
              enableWorker: true,
              enableStashBuffer: false,
              stashInitialSize: 128,
            }}>
      </Reflv>
    </div>
  );
}

function App() {
  const [isShow, setIsShow] = useState(false);

  let onBtnClcik = ()=>{
    setIsShow(!isShow);
    console.log("onBtnClcik");
  };


  return (
    <div className="App">
      <header className="App-header">
        <p>
          播放器示例
        </p>
        <Button type="primary" onClick={() => onBtnClcik()}>点击 切换</Button>

        {
          isShow ? (<VideoPlay/>) : null
        }
      </header>
    </div>
  );
}

export default App;
