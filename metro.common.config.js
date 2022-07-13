const metroClear = require('./compile/metro-clearInfo');
metroClear.clearFileInfo();

const metroCfg = require('./compile/metro-base');

module.exports = {
  serializer: {
    createModuleIdFactory: metroCfg.createCommonModuleIdFactory,
  },
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true,
      },
    }),
  },
};
