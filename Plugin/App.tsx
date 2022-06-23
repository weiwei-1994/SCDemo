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

  const callAndroidMoudle = () => {
    if (Platform.OS == 'android') {
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show('调用安卓原生模块', 1);
    }
  }

  return (
    <View style={styles.contanin}>
      <Button
        title='调用安卓原生模块'
        onPress={callAndroidMoudle} />
      <NativeView
        style= {styles.nativeView}
        titleText='哈哈哈'
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
