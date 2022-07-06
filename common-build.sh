

buildIos(){
    #  打包到工程
    # react-native bundle --platform ios --dev false --entry-file  common-entry.js --bundle-output ios/mainBundle/basic.jsbundle --assets-dest ios/mainBundle/ --config ./metro.base.config.js --reset-cache && node ./compile/split-common.js ios/mainBundle/basic.jsbundle
    
    # 打包到build文件
    
    react-native bundle --platform ios --dev false --entry-file  common-entry.js --bundle-output bundles/common/ios/common.jsbundle --assets-dest bundles/common/ios/ --config ./metro.base.config.js --reset-cache
    node ./compile/split-common.js bundles/common/ios/common.jsbundle
    node ./compile/zip-compressing.js bundles/common/ios ios
}

buildAndroid(){
     
    react-native bundle --platform android --dev false --entry-file index.js --bundle-output bundles/common/android/common.jsbundle  --assets-dest bundles/common/android/  --config ./metro.business.config.js  --reset-cache
    node ./compile/split-common.js bundles/common/android/common.jsbundle
    node ./compile/zip-compressing.js bundles/common/android android
}


echo '  请输入打包类型：iOS/android: '
select var in "ios" "android" ;do
break
done
# read FILM
case $var in
     ios)  echo '正在打包ios'
            buildIos
    ;;
    android)  echo '正在打包android'
            buildAndroid
    ;;
    
    *)  echo '请输入1 或者 2'
    ;;

esac


