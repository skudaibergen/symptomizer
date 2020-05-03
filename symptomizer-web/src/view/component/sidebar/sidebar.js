import React from 'react';

function Sidebar() {
    return (
        <nav className="side-navbar">
            {/* Sidebar Header */}
            <div className="sidebar-header d-flex align-items-center">
                <div className="avatar">
                    <img src="/img/avatar-1.jpg" alt="..." className="img-fluid rounded-circle" />
                </div>
                <div className="title">
                    <h1 className="h4">Mark Stephen</h1>
                    <p>Web Designer</p>
                </div>
            </div>
            {/* Sidebar Navigation Menus */}
            <span className="heading">Main</span>
            <ul className="list-unstyled">
                <li className="active">
                    <a href="#">
                        <i className="icon-home" />Home
                    </a>
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