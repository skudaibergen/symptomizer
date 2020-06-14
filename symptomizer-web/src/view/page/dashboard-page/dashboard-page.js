import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';
import ModelPlot from "../../component/model-plot/model-plot";


class DashboardPage extends React.Component {

    render() {

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar location={this.props.location}/>

                    <div className="content-inner">
                        <InnerHeader title={'Dashboard'}/>


                        <section className="charts">
                            <div className="container-fluid">
                                <ModelPlot />
                            </div>
                        </section>

                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

export default DashboardPage;