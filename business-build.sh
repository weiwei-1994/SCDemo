
#下载iOS的json文件
downloadIosJson(){
     
     curl https://raw.githubusercontent.com/weiwei-1994/SCDemo/master/compile/common-modules-ios.json -o ./compile/common-modules-ios.json
}

#下载android的json文件
downloadAndroidJson(){
     
    curl https://raw.githubusercontent.com/weiwei-1994/SCDemo/master/compile/common-modules-android.json -o ./compile/common-modules-android.json
}


buildIos(){
    outputPath="./bundles/business/ios/"
    pathIsExist $outputPath
     
    react-native bundle --platform ios --dev false --entry-file index.js --bundle-output ${outputPath}"business.jsbundle"  --assets-dest ${outputPath}  --config ./metro.business.config.js  --reset-cache
    node ./compile/zip-compressing.js ${outputPath} ios
}

buildAndroid(){
     outputPath="./bundles/business/android/"
     pathIsExist $outputPath
     
    react-native bundle --platform android --dev false --entry-file index.js --bundle-output ${outputPath}"business.jsbundle" --assets-dest ${outputPath}  --config ./metro.business.config.js  --reset-cache
    node ./compile/zip-compressing.js ${outputPath} android
}

pathIsExist(){
    if [ ! -d $1 ];then
        mkdir -p $1
        echo "自动创建文件目录"

     else
        echo "目录已经存在"
     fi
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
