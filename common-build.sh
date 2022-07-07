

buildIos(){
    #  打包到工程
    # react-native bundle --platform ios --dev false --entry-file  common-entry.js --bundle-output ios/mainBundle/basic.jsbundle --assets-dest ios/mainBundle/ --config ./metro.base.config.js --reset-cache && node ./compile/split-common.js ios/mainBundle/basic.jsbundle
    
    # 打包到build文件
    outputPath="./bundles/common/ios/"
    pathIsExist $outputPath
    react-native bundle --platform ios --dev false --entry-file  common-entry.js --bundle-output ${outputPath}"common.jsbundle" --assets-dest ${outputPath} --config ./metro.base.config.js --reset-cache
    node ./compile/split-common.js ${outputPath}"common.jsbundle"
    node ./compile/zip-compressing.js ${outputPath} ios
}

buildAndroid(){
     
    outputPath="./bundles/common/android/"
    pathIsExist $outputPath

    react-native bundle --platform android --dev false --entry-file index.js --bundle-output ${outputPath}"common.jsbundle"  --assets-dest ${outputPath}  --config ./metro.business.config.js  --reset-cache
    node ./compile/split-common.js ${outputPath}"common.jsbundle"
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
     ios)  echo '正在打包ios'
            buildIos
    ;;
    android)  echo '正在打包android'
            buildAndroid
    ;;
    
    *)  echo '请输入1 或者 2'
    ;;

esac


