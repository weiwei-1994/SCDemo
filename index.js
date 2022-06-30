/**
 * @format
 */

import {AppRegistry} from 'react-native';
import {name as appName} from './app.json';
import Navigate from './Plugin/Navigate/NavigateComponent'

AppRegistry.registerComponent(appName, () => Navigate);
