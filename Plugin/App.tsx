/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React,{useState,useEffect} from 'react';
import {
  StyleSheet,
  Platform,
  View,
  Button,
  NativeModules
} from 'react-native';
import NativeView from './Component/NativeView'

const App = () => {
  const [count, setCount] = useState(1);

  const callAndroidMoudle = (message:string) => {
    if (Platform.OS == 'android') {
      setCount(count + 1);
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show(message, 0);
    }
  }

  return (
    <View style={styles.contanin}>
      <Button
        title='调用安卓原生模块'
        onPress={() => callAndroidMoudle('调用安卓原生模块')} />
      <NativeView
        style= {styles.nativeView}
        titleText={count.toString()}
        onChangeMessage={(nativeEvent:any) => callAndroidMoudle(nativeEvent.message)}
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
