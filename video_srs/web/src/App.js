import logo from './logo.svg';
import './App.css';
import { MyDemo } from '@/demo/MyDemo';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
         视频 demo
        </p>
        <div className="reflv-wrap">
          <MyDemo/>
        </div>
      </header>
    </div>
  );
}

export default App;
