/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState, useEffect } from 'react';
import {setJSExceptionHandler, setNativeExceptionHandler} from 'react-native-exception-handler';
import {Alert} from 'react-native';
import RNRestart from 'react-native-restart';
import {
  StyleSheet,
  Platform,
  View,
  Text,
  Button,
  NativeModules
} from 'react-native';
import {NavtiveView} from 'react-native-ww-plugin-sdk'

const App = (props: any) => {
  const [count, setCount] = useState(1);

  const callAndroidMoudle = (message: string) => {
    if (Platform.OS == 'android') {
      setCount(count + 1);
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show(message, 0);
    }
  }

  const errorHandler = (e, isFatal) => {
    if (isFatal) {
      Alert.alert(
          'Unexpected error occurred',
          `
          Error: ${(isFatal) ? 'Fatal:' : ''} ${e.name} ${e.message}
  
          We will need to restart the app.
          `,
        [{
          text: 'Restart',
          onPress: () => {
            RNRestart.Restart();
          }
        }]
      );
    } else {
      console.log(e+'-----js'); // So that we can see it in the ADB logs in case of Android if needed
    }
  };
  
  setJSExceptionHandler(errorHandler);
  setNativeExceptionHandler((exceptionString) => {
    // This is your custom global error handler
    // You do stuff likehit google analytics to track crashes.
    // or hit a custom api to inform the dev team.
    //NOTE: alert or showing any UI change via JS
    //WILL NOT WORK in case of NATIVE ERRORS.
    console.log('-----错误');
  });

  

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
  androidArea:{
    marginTop: 30
  },
  nativeView: {
    width: 100,
    height: 100,
    marginTop: 50
  }
});

export default App;
