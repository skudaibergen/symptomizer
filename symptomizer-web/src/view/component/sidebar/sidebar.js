import React from 'react';
import { Link } from "react-router-dom";
import {
    APP_ROUTE_DASHBOARD_PAGE,
    APP_ROUTE_DIAGNOSTICS_PAGE
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
                    <Link to="/"><i className="icon-home" />Home</Link>
                </li>
                <li className={location.pathname === APP_ROUTE_DIAGNOSTICS_PAGE ? 'active' : ''}>
                    <Link to="/diagnostics"><i className="icon-flask" />Diagnostics</Link>
                </li>
            </ul>
            {/*<span className="heading">Extras</span>*/}
            {/*<ul className="list-unstyled">*/}
                {/*<li>*/}
                    {/*<a href="#"><i className="icon-flask" />Demo</a>*/}
                {/*</li>*/}
                {/*<li>*/}
                    {/*<a href="#"><i className="icon-screen" />Demo </a>*/}
                {/*</li>*/}
            {/*</ul>*/}
        </nav>
    );
}

export default Sidebar;