const fs = require('fs');
const buildType = process.argv[4];
const {name: appName} = require('../app.json');
const commonModulesFileName = `${__dirname}/common-modules-${buildType}.json`;
const commonModulesIndexMapFileName = `${__dirname}/common-modules-index-map-${buildType}.json`;


function clearFileInfo() {
      fs.writeFileSync(commonModulesIndexMapFileName, '{}');
      fs.writeFileSync(commonModulesFileName, '[]');
    
  }

  module.exports = {
    clearFileInfo,
  };