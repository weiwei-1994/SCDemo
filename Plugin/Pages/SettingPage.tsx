import React, { useState, useEffect } from 'react';
import {
    StyleSheet,
    Platform,
    View,
    Text,
    Button,
    NativeModules
} from 'react-native';

const SettingPage = (props: any) => {
    return(
        <View style={styles.contain}>
            <Text>设置页</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    contain: {
        flex: 1,
        justifyContent:'center',
        alignItems:'center'
    }
});

export default SettingPage