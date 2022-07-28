import {
    NativeModules
} from 'react-native';

const NativeModule = NativeModules.NativeModule;

const allowInDevMode = true;

import {setJSExceptionHandler, getJSExceptionHandler, setNativeExceptionHandler} from 'react-native-exception-handler';

// For most use cases:
// registering the error handler (maybe u can do this in the index.android.js or index.ios.js)
setJSExceptionHandler((error, isFatal) => {
  
    console.log("错误00")


});
//=================================================
// ADVANCED use case:
const exceptionhandler = (error, isFatal) => {
  // your error handler function

  console.log("错误11" + error +":"+ isFatal)
  NativeModule.nativeStackPop()
};
setJSExceptionHandler(exceptionhandler, allowInDevMode);
const currentHandler = getJSExceptionHandler();

//For most use cases:
setNativeExceptionHandler((exceptionString) => {

    console.log("错误222")
  });
