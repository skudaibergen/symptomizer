import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';
import ContentTable from "../../component/content-table/content-table";
import {api} from "../../../core/api/api-facade";
import LoaderMaterial from "../../component/loader/loader-material";


const columns = [
    {
        name: 'ID',
        selector: 'id',
        sortable: true,
        style: {
            color: 'rgba(0,0,0,.54)'
        }
    },
    {
        name: 'Name',
        selector: 'name',
        sortable: true,
        editable: true,
        style: {
            color: '#202124',
            fontSize: '14px',
            fontWeight: 500,
        }
    },
    {
        name: 'Name Ru',
        selector: 'nameRu',
        sortable: true,
        right: true,
        style: {
            color: '#202124',
            fontSize: '14px',
            fontWeight: 500,
        }
    }
];

class SymptomsPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            symptoms: null,
            loading: true
        };
    }

    componentDidMount() {
        this.getSymptoms()
            .then(this.setState({ loading: false }));
    }

    getSymptoms = () => {
        return api.fetchSymptoms({ grouped: false, size: 1000 })
            .then(res => res.data)
            .then(body => {
                if (body.status === 'success') return body.data;
                else throw new Error(`Api status was ${body.status}`);
            })
            .then(symptoms => this.setState({ symptoms }))
            .catch(err => console.error(err));
    };

    render() {
        const { symptoms, loading } = this.state;

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar location={this.props.location}/>

                    <div className="content-inner">
                        <InnerHeader title={'Symptoms'}/>


                        {
                            loading ?
                                <LoaderMaterial className="mt-5" loading={loading}/> :
                            symptoms ?
                                <section className="">
                                    <div className="container-fluid">
                                        <ContentTable title={'Data'}
                                                      columns={columns}
                                                      data={symptoms} />
                                    </div>
                                </section> :
                                null
                        }

                        <Footer />
                    </div>
                </div>
            </div>
        );
    }
}

export default SymptomsPage;