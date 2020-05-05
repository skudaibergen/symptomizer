import React from "react";

function Header() {
    return (
        <header className="header">
            <nav className="navbar">
                {/*  Search Box  */}
                <div className="search-box">
                    <button className="dismiss">
                        <i className="icon-close" />
                    </button>
                    <form id="searchForm" action="#" role="search">
                        <input type="search" placeholder="What are you looking for..." className="form-control" />
                    </form>
                </div>
                <div className="container-fluid">
                    <div className="navbar-holder d-flex align-items-center justify-content-between">
                        {/*  Navbar Header  */}
                        <div className="navbar-header">
                            {/*  Navbar Brand  */}
                            <a href="#" className="navbar-brand d-none d-sm-inline-block">
                                <div className="brand-text d-none d-lg-inline-block">
                                    <span>Symptom </span><strong>Analyzer</strong>
                                </div>
                                <div className="brand-text d-none d-sm-inline-block d-lg-none">
                                    <strong>BD</strong>
                                </div>
                            </a>
                            {/*  Toggle Button  */}
                            <a id="toggle-btn" href="#" className="menu-btn active">
                                <span/><span/><span/>
                            </a>
                        </div>

                        {/*  Navbar Menu  */}
                        <ul className="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                            {/*  Search  */}
                            <li className="nav-item d-flex align-items-center">
                                <a id="search" href="#">
                                    <i className="icon-search" />
                                </a>
                            </li>

                            {/*  Logout  */}
                            <li className="nav-item">
                                <a href="#" className="nav-link logout">
                                    <span className="d-none d-sm-inline">Logout</span>
                                    <i className="fa fa-sign-out" />
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Header;