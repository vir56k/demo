import React, { PureComponent } from 'react';
import Reflv from '@/Reflv/index';

import demo from './movie.flv';

export class MyDemo extends PureComponent {

  render() {
    return (
      <Reflv url={demo} type="flv"/>
    )
  }
}
