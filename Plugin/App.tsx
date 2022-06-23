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

const App = () => {

  const callAndroidMoudle = () => {
    if(Platform.OS == 'android'){
      const androidNativeMoudle = NativeModules.ToastExample;
      androidNativeMoudle.show('调用安卓原生模块',1);
    }
  }

  return (
   <View style={styles.contanin}>
     <Button 
      title='调用安卓原生模块'
      onPress={callAndroidMoudle}/>
   </View>
  );
};

const styles = StyleSheet.create({
  contanin:{
    flex:1,
    justifyContent:'center',
    alignItems:'center'
  }
});

export default App;
