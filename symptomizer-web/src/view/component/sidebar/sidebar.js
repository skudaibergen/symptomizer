import React from 'react';
import { Link } from "react-router-dom";
import {
    APP_ROUTE_DASHBOARD_PAGE,
    APP_ROUTE_DIAGNOSTICS_PAGE, APP_ROUTE_DISEASES_PAGE, APP_ROUTE_SYMPTOMS_PAGE
} from '../../../core/constants';

function Sidebar({ location }) {

    return (
        <nav className="side-navbar">
            {/* Sidebar Header */}
            <div className="sidebar-header d-flex align-items-center">
                <div className="avatar">
                    <img src="/img/avatar-1.jpg" alt="..." className="img-fluid rounded-circle" />
                </div>
                <div className="title">
                    <h1 className="h4">Mark Stephen</h1>
                    <p>Medicine Doctor</p>
                </div>
            </div>
            {/* Sidebar Navigation Menus */}
            <span className="heading">Main</span>
            <ul className="list-unstyled">
                <li className={location.pathname === APP_ROUTE_DASHBOARD_PAGE ? 'active' : ''}>
                    <Link to={APP_ROUTE_DASHBOARD_PAGE}><i className="icon-home" />Home</Link>
                </li>
                <li className={location.pathname === APP_ROUTE_DIAGNOSTICS_PAGE ? 'active' : ''}>
                    <Link to={APP_ROUTE_DIAGNOSTICS_PAGE}><i className="icon-flask" />Diagnostics</Link>
                </li>
            </ul>
            <span className="heading">Dictionary</span>
            <ul className="list-unstyled">
                <li className={location.pathname === APP_ROUTE_SYMPTOMS_PAGE ? 'active' : ''}>
                    <Link to={APP_ROUTE_SYMPTOMS_PAGE}><i className="icon-flask" />Symptoms</Link>
                </li>
                <li className={location.pathname === APP_ROUTE_DISEASES_PAGE ? 'active' : ''}>
                    <Link to={APP_ROUTE_DISEASES_PAGE}><i className="icon-screen" />Diseases</Link>
                </li>
            </ul>
        </nav>
    );
}

export default Sidebar;