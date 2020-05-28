import React from 'react';
import { Route, withRouter } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.css';
import './style/app.css';
import './style/style.default.css';
import './style/fontastic.css';
import './style/font-awesome.min.css'

import DashboardPage from './page/dashboard-page';
import SymptomsPage from './page/symptoms-page';
import DiseasesPage from './page/diseases-page';
import LoginPage     from './page/login-page/login-page';
import DiagnosticsPage from './page/diagnostics-page/diagnostics-page';
import {
    APP_ROUTE_DASHBOARD_PAGE,
    APP_ROUTE_DIAGNOSTICS_PAGE,
    APP_ROUTE_LOGIN_PAGE,
    APP_ROUTE_SYMPTOMS_PAGE,
    APP_ROUTE_DISEASES_PAGE
} from "../core/constants";


function App() {
    return (
        <div id="app">
            <Route exact path={APP_ROUTE_DASHBOARD_PAGE} component={DashboardPage}/>
            <Route exact path={APP_ROUTE_SYMPTOMS_PAGE} component={SymptomsPage}/>
            <Route exact path={APP_ROUTE_DISEASES_PAGE} component={DiseasesPage}/>
            <Route exact path={APP_ROUTE_DIAGNOSTICS_PAGE} component={DiagnosticsPage}/>
            <Route exact path={APP_ROUTE_LOGIN_PAGE} component={LoginPage}/>
        </div>
    );
}

export default withRouter(App);
