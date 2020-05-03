import React from 'react';
import ReactDOM from 'react-dom';

import App from './view/app';
import * as serviceWorker from './serviceWorker';

import createHistory from 'history/createBrowserHistory';
import { Router } from "react-router-dom";

const history = createHistory();

ReactDOM.render(
  <React.StrictMode>
      <Router history={history}>
          <App />
      </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
