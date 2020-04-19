import {applyMiddleware, createStore} from 'redux'
import rootReducer from './reducers/rootReducer'
import logger from 'redux-logger'
import ReduxThunk from 'redux-thunk'; 

import { composeWithDevTools } from 'redux-devtools-extension';

export const store = createStore(rootReducer, composeWithDevTools(
  applyMiddleware(ReduxThunk, logger)
));