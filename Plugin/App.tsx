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
  Text,
  View,
} from 'react-native';

const App = () => {
  return (
   <View style={styles.contanin}>
     <Text>欢迎</Text>
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
