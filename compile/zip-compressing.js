const compressing = require('compressing')
const fs = require('fs');
const path = require('path');
const filePath = process.argv[2];
const type = process.argv[3];

const {name: appName} = require('../app.json');


function creatZIP() {
    console.log('查看文件:' + filePath);
    if (filePath) {
      if (fs.existsSync(filePath)) {
        console.log('开始压缩');
        let filePathLast = path.join(filePath,'../')

        let zipfilePath = `${filePathLast}${type}_${appName}.zip`

        compressing.zip.compressDir(filePath,zipfilePath).then(res =>{
            console.log('压缩成功');
        }).catch(eer => {
            console.log(eer);
        })
      } else {
        console.log(filePath, '文件不存在');
      }
    }
}


// //文件遍历方法
// function fileDisplay(filePath){


//   const zipStream = new compressing.zip.Stream();
//   //根据文件路径读取文件，返回文件列表
//   fs.readdir(filePath,function(err,files){
//       if(err){
//           console.warn(err)
//       }else{
//           //遍历读取到的文件列表
//           files.forEach(function(filename){
//               //获取当前文件的绝对路径
//               var filedir = path.join(filePath, filename);

//               zipStream.addEntry(filedir);
           
//           });
//       }
//   });


// }

creatZIP();


module.exports = {
    creatZIP,
  };

