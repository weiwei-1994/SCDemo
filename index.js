/**
 * @format
 */

import {AppRegistry} from 'react-native';
import {name as appName} from './app.json';
//import Navigate from './Plugin/Navigate/NavigateComponent'
import Push from './Plugin/Push'

AppRegistry.registerComponent(appName, () => Push);
