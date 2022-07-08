import React from 'react';
import {
    StyleSheet,
    View,
    Text,
    Image,
    Button
} from 'react-native';
import { Calendar } from 'react-native-calendars';
import {Input,NavBar} from 'react-native-ww-plugin-sdk'
 
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
            <Image 
            style={styles.image}
            source={require('../Resource/star.png')}
            />
            <Input
                labelText = 'test'
                placeHolder = 'placeHolder'
                securityInput = {false}
            />
            <NavBar 
                style = {styles.navBar}
                leftButtonText = '哈哈哈'
            />
        </View>
    );
}

const styles = StyleSheet.create({
    contain: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center'
    },
    image:{
        marginTop:20,
        width:100,
        height:100
    },
    navBar:{
        width:200
    }
});

export default SettingPage