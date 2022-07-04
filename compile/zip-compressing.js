const compressing = require('compressing')
const fs = require('fs');
const path = require('path');
const filePath = process.argv[2];

const {name: appName} = require('../app.json');


function creatZIP() {
    console.log('查看文件:' + filePath);
    if (filePath) {
      if (fs.existsSync(filePath)) {
        console.log('开始压缩');
        let filePath1 = path.join(filePath,'../')
        compressing.zip.compressDir(filePath1,`bundles/ios/${appName}.zip`).then(res =>{
            console.log('压缩成功');
        }).catch(eer => {
            console.log(eer);
        })
      } else {
        console.log(filePath, '文件不存在');
      }
    }
}



module.exports = {
    creatZIP,
  };

