import React from 'react';
import { Route, withRouter } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.css';
import './style/app.css';
import './style/style.default.css';
import './style/fontastic.css';
import './style/font-awesome.min.css'


import DashboardPage from './page/dashboard-page';
import LoginPage     from './page/login-page/login-page';

function App() {
    return (
        <div id="app">
            <Route exact path="/" component={DashboardPage}/>
            <Route exact path="/login" component={LoginPage}/>
        </div>
    );
}

export default withRouter(App);
