
import {
  NativeModules, TurboModuleRegistry
} from 'react-native';
// var ErrorUtils = require('ErrorUtils');

const NativeModule = NativeModules.NativeModule;


import { setJSExceptionHandler, getJSExceptionHandler, setNativeExceptionHandler } from 'react-native-exception-handler';

export const registExceptionHandler = (pluginName:string):void => {
  //定义js excpectionHandler
  let exceptionhandler1 = (error:Error, isFatal:boolean) => {
    // your error handler function

    console.log("JS错误" + error + ":" + isFatal)
    NativeModule.nativeStackPop()
  };
  /**
   * 注册js expectionHandler
   * 第二个参数是布尔值，如果未指定，则默认值为false。如果设置为true，则将调用处理程序以代替红屏
   */
  setJSExceptionHandler(exceptionhandler1, true);


   //定义native excpectionHandler
  let exceptionhandler = (exceptionString:string) => {

    console.log("原生错误" + exceptionString)

  };
  //注册native expcetionHandler
  setNativeExceptionHandler(
    exceptionhandler,
    false,// 是否强制app推出，仅安卓
    true  //是否自定义处理程序
  );
}


// const currentHandler = getJSExceptionHandler();
