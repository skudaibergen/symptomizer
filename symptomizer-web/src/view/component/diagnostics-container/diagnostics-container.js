import React from "react";

import './diagnostics-container.css'

function DiagnosticsContainer({ symptomsMap }) {
    return (
        <section className="">
            <div className="container-fluid">
                <div className="row">
                    <div className="col-lg-12">
                        <div className="diagnostics-container-card card">
                            <div className="card-close">
                                <div className="dropdown">
                                    <button type="button"
                                            id="closeCard1"
                                            data-toggle="dropdown"
                                            aria-haspopup="true"
                                            aria-expanded="false"
                                            className="dropdown-toggle">
                                        <i className="fa fa-ellipsis-v" />
                                    </button>

                                    <div aria-labelledby="closeCard1"
                                         className="dropdown-menu dropdown-menu-right has-shadow">
                                        <a href="#" className="dropdown-item remove"><i className="fa fa-times" />Close</a>
                                        <a href="#" className="dropdown-item edit"><i className="fa fa-gear" />Edit</a>
                                    </div>
                                </div>
                            </div>
                            <div className="card-body">
                                <h3 className="text-center">Choose symptoms</h3>
                                {/*<small>Lorem ipsum dolor sit amet.</small>*/}

                                <div className="row mt-4 d-flex justify-content-center">
                                    {   symptomsMap != null &&
                                        Object.keys(symptomsMap).map((key, index) =>
                                            <div key={key} className="card col-md-3 mr-4 ml-4">
                                                <div className="card-body">
                                                    <div className="form-group">
                                                        <label className="form-control-label">
                                                            {key} <br/>
                                                            {/*<small className="text-primary">Custom elements</small>*/}
                                                        </label>
                                                        <div>
                                                            {
                                                                symptomsMap[key].map((symptom, index) =>
                                                                    <div key={key + '-' + index} className="i-checks">
                                                                        <input id={'symptom-checkbox-' + symptom.id} type="checkbox" className="checkbox-template" />
                                                                        <label htmlFor={'symptom-checkbox-' + symptom.id}>{ symptom.name }</label>
                                                                    </div>
                                                                )
                                                            }
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        )
                                    }
                                </div>


                                <div className="row form-group d-flex justify-content-end mr-2">
                                    <div className="">
                                        <button type="submit" className="btn btn-secondary mr-2">Back</button>
                                        <button type="submit" className="btn btn-primary">Next</button>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default DiagnosticsContainer;