import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';


class DashboardPage extends React.Component {

    render() {

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar />


                    <div className="content-inner">
                        <InnerHeader />
                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

export default DashboardPage;