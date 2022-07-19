const fs = require('fs');
const buildType = process.argv[4];
const {name: appName} = require('../app.json');
const commonModulesFileName = `${__dirname}/common-modules-${buildType}.json`;


function clearFileInfo() {
      fs.writeFileSync(commonModulesFileName, '[]');
    
  }

  module.exports = {
    clearFileInfo,
  };