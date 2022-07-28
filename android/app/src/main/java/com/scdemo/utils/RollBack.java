package com.scdemo.utils;

import android.util.Log;

import com.microsoft.codepush.react.CodePush;
import com.scdemo.BuildConfig;
import com.scdemo.MainApplication;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RollBack {

    public static void setRollBack(){
        CodePush codePush = new CodePush(BuildConfig.CODEPUSH_KEY,MainApplication.getContext(),BuildConfig.DEBUG);
        Class<?> codeClass = codePush.getClass();
        try {
//            Field needToRollBack = codeClass.getField("sNeedToReportRollback");
//            needToRollBack.setAccessible(true);//压制java检查
//            needToRollBack.set(codePush,true);
            codePush.setNeedToReportRollback(true);
            Method rollMethod = codeClass.getDeclaredMethod("rollbackPackage");
            rollMethod.setAccessible(true);
            rollMethod.invoke(codePush);
        } catch (NoSuchMethodException e) {
            Log.d("yangjie","reflect::NoSuchMthodException::"+e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.d("yangjie","reflect::NoSuchMthodException::"+e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.d("yangjie","reflect::IllegalAccessException::"+e.getMessage());
            e.printStackTrace();
        }
//        catch (NoSuchFieldException e) {
//            Log.d("yangjie","reflect::NoSuchFieldException param::"+e.getMessage());
//            e.printStackTrace();
//        }
    }

//    public static void setNeedtoRollBack(boolean isNeedToRollBack){
//        CodePush codePush = new CodePush(BuildConfig.CODEPUSH_KEY, MainApplication.getContext(),BuildConfig.DEBUG);
//        Class<?> codeClass = codePush.getClass();
//        try {
//            Field needToRollBack = codeClass.getField("sNeedToReportRollback");
//            needToRollBack.setAccessible(true);//压制java检查
//            needToRollBack.set(codePush,isNeedToRollBack);
//        } catch (NoSuchFieldException e) {
//            Log.d("yangjie","reflect::NoSuchFieldException param::"+e.getMessage());
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            Log.d("yangjie","reflect::IllegalAccessException param::"+e.getMessage());
//            e.printStackTrace();
//        }
//
//    }
}
