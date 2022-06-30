import React from 'react'
import {
    Button,
    NativeModules
} from 'react-native';
import { NavigationContainer } from '@react-navigation/native'
import { createNativeStackNavigator } from '@react-navigation/native-stack'

import App from '../App';
import SeetingPage from '../Pages/SettingPage'

const Stack = createNativeStackNavigator();
const NativeModule = NativeModules.NativeModule;

const Navigate = (props:any) => {

    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen
                    name="Home"
                    component={App}
                    options={{
                        headerTitle: "插件首页",
                        headerLeft: (props) => {
                            return (
                                <Button
                                    title="返回App"
                                    onPress={() => NativeModule.nativeStackPop()} />)
                        },
                        // headerRight: (props) => {
                        //     return (
                        //         <Button
                        //             title="设置"
                        //             onPress={() => props.navigation.navigate("Setting")} />)
                        // }
                    }}
                />
                <Stack.Screen
                    name='Setting'
                    component={SeetingPage}
                    options={{
                        headerTitle: ""
                    }}
                />
            </Stack.Navigator>
        </NavigationContainer>
    );
}
export default Navigate