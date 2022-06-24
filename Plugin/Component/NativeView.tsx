import { requireNativeComponent } from 'react-native';
import React, {forwardRef}from 'react';

const RNNativeView = requireNativeComponent('RCTCustomView');

const NativeView = (props: any) => {

    const _onChange = (event: Event) =>{

      if (!props.onChangeMessage) {
          return;
        }
        console.log(event);
        props.onChangeMessage();
      }

    return (
        <RNNativeView
            {...props}
            onChange={_onChange}
        />
        );
}

export default NativeView;
