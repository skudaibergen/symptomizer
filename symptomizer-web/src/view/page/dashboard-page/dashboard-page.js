import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';


class DashboardPage extends React.Component {

    render() {

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar />
                </div>
            </div>
        );
    }
}

export default DashboardPage;