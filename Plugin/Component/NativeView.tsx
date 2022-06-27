import { requireNativeComponent } from 'react-native';
import React, {forwardRef}from 'react';

const RNNativeView = requireNativeComponent('RCTCustomView');

const NativeView = (props: any) => {

    const _onChange = (event: any) =>{

      if (!props.onChangeMessage) {
          return;
        }
        props.onChangeMessage(event.nativeEvent);
      }

    return (
        <RNNativeView
            {...props}
            onChange={_onChange}
        />
        );
}

export default NativeView;
