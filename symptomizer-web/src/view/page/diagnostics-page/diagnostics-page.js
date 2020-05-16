import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';
import DiagnosticsContainer from "../../component/diagnostics-container/diagnostics-container";

import { api } from '../../../core/api/api-facade';

class DiagnosticsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            symptomsMap: null
        };
    }

    componentDidMount() {
        api.fetchSymptoms()
            .then(res => res.data)
            .then(body => {
                if (body.status === 'success') return body.data;
                else throw new Error(`Api status was ${body.status}`);
            })
            .then(symptomsMap => this.setState({ symptomsMap }))
            .catch(err => console.error(err));
    }

    render() {
        const { symptomsMap } = this.state;

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar />


                    <div className="content-inner">
                        <InnerHeader title={'Diagnostics'} />
                        <DiagnosticsContainer predictDiagnosis={api.predictDiagnosis}
                                              symptomsMap={symptomsMap}/>
                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

export default DiagnosticsPage;