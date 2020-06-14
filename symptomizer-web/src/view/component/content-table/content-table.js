import React from "react";
import DataTable from 'react-data-table-component';

const styles = {
    headRow: {
        style: {
            border: 'none',
        },
    },
    headCells: {
        style: {
            color: '#202124',
            fontSize: '14px',
        },
    },
    rows: {
        highlightOnHoverStyle: {
            backgroundColor: 'rgb(230, 244, 244)',
            borderBottomColor: '#FFFFFF',
            borderRadius: '25px',
            outline: '1px solid #FFFFFF',
        },
    },
    pagination: {
        style: {
            border: 'none',
        },
    },
};

function ContentTable({ columns, data }) {
    return (
        <div className="card">
            <div className="card-body">
                <div className="card-close">
                    <div className="dropdown">
                        <button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false" className="dropdown-toggle"><i className="fa fa-ellipsis-v"></i>
                        </button>
                        <div aria-labelledby="closeCard1" className="dropdown-menu dropdown-menu-right has-shadow">
                            <a href="#" className="dropdown-item edit"> <i className="fa fa-gear"></i>Create</a>
                        </div>
                    </div>
                </div>

                <div className="table-responsive">
                    <input type="text" placeholder="Search..." className="form-control w-25" />

                    <DataTable columns={columns}
                               data={data}
                               noHeader={true}
                               customStyles={styles}

                               expandableRows
                               // expandableRowsComponent={}
                               pagination
                               fixedHeader
                               fixedHeaderScrollHeight="500px"/>
                </div>
            </div>
        </div>
    );
}

export default ContentTable;