/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState, useEffect } from 'react';
import {
  StyleSheet,
  Platform,
  View,
  Text,
  Button,
  NativeModules
} from 'react-native';
import { NavtiveView } from 'react-native-ww-plugin-sdk'
import { registExceptionHandler } from './Exception/ExceptionHandler'
import {name} from '../app.json'

const App = (props: any) => {
  const [count, setCount] = useState(1);

  const callAndroidMoudle = (message: string) => {
    if (Platform.OS == 'android') {
      setCount(count + 1);
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show(message, 0);
    }
  }


  const androidCompent = () => {
    if (Platform.OS == 'android') {
      return (
        <View style={styles.androidArea}>
          <Button
            title='调用安卓原生模块'
            onPress={() => callAndroidMoudle('调用安卓原生模块')} />
          <NavtiveView
            style={styles.nativeView}
            titleText={count.toString()}
            onChangeMessage={(nativeEvent: any) => callAndroidMoudle(nativeEvent.message)}
          />
        </View>
      )
    }
    return null;
  }

  useEffect(() => {
    registExceptionHandler(name);
  })

  return (
    <View style={styles.contanin}>
      <Button
        title="进入设置页"
        onPress={() => props.navigation.navigate("Setting")} />
      {androidCompent()}
    </View>
  );
};

const styles = StyleSheet.create({
  contanin: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  androidArea: {
    marginTop: 30
  },
  nativeView: {
    width: 100,
    height: 100,
    marginTop: 50
  }
});

export default App;
