import { requireNativeComponent } from 'react-native';
import React, {forwardRef}from 'react';

const RNNativeView = requireNativeComponent('RCTCustomView');

const NativeView = (props: any, ref: any) => {

    const _onChange = (event: Event) =>{

      if (!props.onChangeMessage) {
          return;
        }
        // console.log(event);
        props.onChangeMessage();
      }

    return (
        <RNNativeView
            ref={ref}
            {...props}
            onChange={_onChange}
        />
        );
}

export default forwardRef(NativeView);
