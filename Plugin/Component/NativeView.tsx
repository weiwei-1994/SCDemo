import { requireNativeComponent } from 'react-native';
import React from 'react';

const RNNativeView = requireNativeComponent('RCTCustomView');

const NativeView = (props: any) => {
    
    return (
        <RNNativeView
            {...props}
        />
        );
}

export default NativeView;
