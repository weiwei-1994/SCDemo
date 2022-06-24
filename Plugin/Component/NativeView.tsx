import { requireNativeComponent } from 'react-native';
import React from 'react';

const RNNativeView = requireNativeComponent('RCTCustomView');

const NativeView = (props: any) => {

    const _onChange = (event: Event) =>{
        if (props.onChangeMessage) {
          return;
        }
        console.log(event.eventPhase.toString);
        props.onChangeMessage(event.eventPhase.toString);
      }

    return (
        <RNNativeView
            {...props}
            onChange={_onChange.bind(this)}
        />
        );
}

export default NativeView;
