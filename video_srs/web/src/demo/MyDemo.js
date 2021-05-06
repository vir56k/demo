import React, { PureComponent } from 'react';
import Reflv from '@/Reflv/index';

export class MyDemo extends PureComponent {

  constructor(props) {
    super(props);
    this.state = {
        "Video_URL": 'http://localhost:8080/live/livestream.flv'
      }
  }

  componentDidMount() {
  }

  render() {
    return (
      <Reflv url={this.state.Video_URL} type="flv"/>
    )
  }
}
