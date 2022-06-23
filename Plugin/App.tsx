/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  StyleSheet,
  Platform,
  View,
  Button,
  NativeModules
} from 'react-native';
import NativeView from './Component/NativeView'

const App = () => {

  const callAndroidMoudle = (message:string) => {
    if (Platform.OS == 'android') {
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show(message, 1);
    }
  }

  return (
    <View style={styles.contanin}>
      <Button
        title='调用安卓原生模块'
        onPress={() => callAndroidMoudle('调用安卓原生模块')} />
      <NativeView
        style= {styles.nativeView}
        titleText='哈哈哈'
        onChangeMessage={() => callAndroidMoudle('点击原生组件回调')}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  contanin: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  nativeView:{
    width:100,
    height:100,
    marginTop:50
  }
});

export default App;
