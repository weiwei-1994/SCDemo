
#下载iOS的json文件
downloadIosJson(){
     
     curl https://raw.githubusercontent.com/weiwei-1994/SCDemo/master/compile/common-modules-ios.json -o ./compile/common-modules-ios.json
}

#下载android的json文件
downloadAndroidJson(){
     
    curl https://raw.githubusercontent.com/weiwei-1994/SCDemo/master/compile/common-modules-android.json -o ./compile/common-modules-android.json
}


buildIos(){
     
    react-native bundle --platform ios --dev false --entry-file index.js --bundle-output bundles/business/ios/business.jsbundle  --assets-dest bundles/business/ios/  --config ./metro.business.config.js  --reset-cache
    node ./compile/zip-compressing.js bundles/business/ios ios
}

buildAndroid(){
     
    react-native bundle --platform android --dev false --entry-file index.js --bundle-output bundles/business/android/business.jsbundle  --assets-dest bundles/business/android/  --config ./metro.business.config.js  --reset-cache
    node ./compile/zip-compressing.js bundles/business/android android
}



echo '  请输入打包类型：iOS/android: '
select var in "ios" "android" ;do
break
done
# read FILM
case $var in
     ios)  echo '正在下载ios打包文件'
            downloadIosJson
            buildIos
    ;;
    android)  echo '正在下载android打包文件'
            downloadAndroidJson
            buildAndroid
    ;;
    
    *)  echo '请输入1 或者 2'
    ;;

esac








    

    
    
    
#    react-native bundle --platform ios --dev false --entry-file index.js --bundle-output ios/PluginBundle/business.jsbundle  --assets-dest ios/PluginBundle/  --config ./metro.business.config.js  --reset-cache
#else
#    echo "params error"
#fi
