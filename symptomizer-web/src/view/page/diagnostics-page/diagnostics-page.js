import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';
import DiagnosticsContainer from "../../component/diagnostics/diagnostics-container/diagnostics-container";

import { api } from '../../../core/api/api-facade';
import LoaderMaterial from "../../component/loader/loader-material";

class DiagnosticsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            symptomsMap: null,
            predictionResult: null,
            loading: false
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

    submit = anamnesis => {
        this.setState({ loading: true });

        api.predictDiagnosis(anamnesis)
            .then(res => res.data)
            .then(body => {
                if (body.status === 'success') this.setState({ loading: false, predictionResult: body.data });
                else throw new Error(`Api status was ${body.status}`);
            })
            .catch(err => console.error(err));
    };

    render() {
        const { symptomsMap, predictionResult, loading } = this.state;

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar location={this.props.location} />


                    <div className="content-inner">
                        <InnerHeader title={'Diagnostics'} />
                        {   loading ?
                            <LoaderMaterial className="mt-5" loading={loading}/> :
                            <DiagnosticsContainer submit={this.submit}
                                                  symptomsMap={symptomsMap}
                                                  predictionResult={predictionResult} />
                        }
                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

export default DiagnosticsPage;