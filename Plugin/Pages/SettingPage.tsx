import React, { useState, useEffect } from 'react';
import {
    StyleSheet,
    Platform,
    View,
    Text,
    Button,
    NativeModules
} from 'react-native';
import { Calendar } from 'react-native-calendars';

const SettingPage = (props: any) => {

    return (
        <View style={styles.contain}>
            <Text>设置页</Text>
            <Calendar
                // Collection of dates that have to be marked. Default = {}
                markedDates={{
                    '2012-05-16': { selected: true, marked: true, selectedColor: 'blue' },
                    '2012-05-17': { marked: true },
                    '2012-05-18': { marked: true, dotColor: 'red', activeOpacity: 0 },
                    '2012-05-19': { disabled: true, disableTouchEvent: true }
                }}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    contain: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    }
});

export default SettingPage