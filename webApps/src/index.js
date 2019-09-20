import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import { createStore, combineReducers } from 'redux';
import reducer from './reducer/reducer';
import imageReducer from './reducer/imageReducer';
import { Provider } from 'react-redux'

// add session to local storage
export const loadState = () => {
    try {
      const serializedState = localStorage.getItem('states');
      if (serializedState === null) {
        return undefined;
      }
      console.log("This is initial state" )
      console.log(serializedState)
      
      return JSON.parse(serializedState);
    } catch (err) {
        console.log(err)
      return undefined;
    }
  }; 

  // store the states
const persistedState = loadState();

const rootReducer = combineReducers({
    colRd: reducer,
    imgRd: imageReducer
});

const store = createStore(rootReducer);

store.subscribe(() =>{
    const state= store.getState();

    // if(state === null || state === undefined) {
    //     console.log("store not saved");
    // } else{
    //     const serializedState = JSON.stringify(state);
    //     console.log("else store updated", store.getState());
    //         localStorage.setItem("states", serializedState);
    // }
});
 
ReactDOM.render(<Provider store={store}>
    <App />
</Provider>, document.getElementById('root'));


serviceWorker.unregister();
