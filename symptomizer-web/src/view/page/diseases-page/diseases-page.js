import React from 'react';

import Header from '../../component/header/header';
import Sidebar from '../../component/sidebar/sidebar';
import InnerHeader from '../../component/inner-header/inner-header';
import Footer from '../../component/footer/footer';
import {api} from "../../../core/api/api-facade";
import LoaderMaterial from "../../component/loader/loader-material";
import ContentTable from "../../component/content-table/content-table";


const columns = [
    {
        name: 'ID',
        selector: 'id',
        sortable: true,
        style: {
            color: 'rgba(0,0,0,.54)',
        },
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

class DiseasesPage extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            diseases: null,
            loading: true
        };
    }

    componentDidMount() {
        this.getDiseases()
            .then(this.setState({ loading: false }));
    }

    getDiseases = () => {
        return api.fetchDiseases({ grouped: false, size: 1000 })
            .then(res => res.data)
            .then(body => {
                if (body.status === 'success') return body.data;
                else throw new Error(`Api status was ${body.status}`);
            })
            .then(diseases => this.setState({ diseases }))
            .catch(err => console.error(err));
    };

    render() {
        const { diseases, loading } = this.state;

        return (
            <div className="page">
                <Header />

                <div className="page-content d-flex align-items-stretch">
                    <Sidebar location={this.props.location}/>

                    <div className="content-inner">
                        <InnerHeader title={'Diseases'}/>

                        {
                            loading ?
                                <LoaderMaterial className="mt-5" loading={loading}/> :
                                diseases ?
                                    <section className="">
                                        <div className="container-fluid">
                                            <ContentTable title={'Data'}
                                                          columns={columns}
                                                          data={diseases} />
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

export default DiseasesPage;