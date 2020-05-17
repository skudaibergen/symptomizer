import React from "react";

import DiagnosticsSymptomsInput from "../diagnostics-symptoms-input/diagnostics-symptoms-input";
import DiagnosticsQuestionsInput from "../diagnostics-questions-input/diagnostics-questions-input";

import './diagnostics-container.css'
import DiagnosticsResultCard from "../diagnostics-result-card/diagnostics-result-card";


class DiagnosticsContainer extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            anamnesis: {
                symptomIds: []
            },
            currentStep: 0,
            stepNum: 4
        }
    }

    submit = () => {
        const { currentStep, stepNum, anamnesis } = this.state;

        if (currentStep === stepNum - 1)
            this.props.submit(anamnesis);
    };

    goNext = () => {
        let currentStep = this.state.currentStep;

        if (currentStep !== this.state.stepNum - 1) {
            currentStep++;
            this.setState({
                currentStep
            })
        }
    };

    goBack = () => {
        let currentStep = this.state.currentStep;

        if (currentStep !== 0) {
            currentStep--;
            this.setState({
                currentStep
            })
        }
    };

    addSymptom = (symptom, checked) => {
        if (symptom) {
            const anamnesis = this.state.anamnesis;
            let symptomIds = anamnesis.symptomIds;

            if (checked) {
                symptomIds.push(symptom.id);
            } else {
                symptomIds.splice(symptomIds.findIndex(id => id === symptom.id), 1)
            }

            let newAnamnesis = { ...anamnesis, symptomIds };
            this.setState({ anamnesis: newAnamnesis });
        }
    };

    render() {
        const { currentStep, stepNum } = this.state;
        const { symptomsMap, predictionResult } = this.props;

        return (
            <section className="">
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-lg-12">
                            {/*Progress bar*/}
                        </div>

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
                                {   predictionResult ?
                                    <div className="card-body">
                                        <DiagnosticsResultCard />
                                    </div> :
                                    <div className="card-body">
                                        {   currentStep === 0 ?
                                            <div>
                                                <h3 className="text-center mb-2">Terms of Service</h3>

                                                <small className="text-center text-primary">Before using the checkup, please read Terms of Service. Remember that:</small>

                                                <ul className="mt-3">
                                                    <li className="mb-3">
                                                        <strong className="text-primary">Checkup is not a diagnosis.</strong> <span>Checkup is for informational purposes and is not a qualified medical opinion.</span>
                                                    </li>
                                                    <li className="mb-3">
                                                        <strong className="text-primary">Do not use in emergencies.</strong> <span>In case of health emergency, call your local emergency number immediately.</span>
                                                    </li>
                                                    <li className="mb-3">
                                                        <strong className="text-primary">Your data is safe.</strong> <span>Information that you provide is anonymous and not shared with anyone.</span>
                                                    </li>
                                                </ul>
                                            </div> :

                                            currentStep === 1 ?
                                                <div>
                                                    <h3 className="text-center mb-2">Please choose your sex</h3>

                                                    <div className="mt-4 mb-4 pt-4 pb-4 d-flex justify-content-center">
                                                        <div className="d-flex flex-row">
                                                            <button className="sex-input-button">
                                                                <i className="fa fa-5x fa-female mt-2 pt-2" />
                                                                <br/>
                                                                <span className="mt-4 pb-2">Female</span>
                                                            </button>

                                                            <button className="sex-input-button">
                                                                <i className="fa fa-5x fa-male mt-2 pt-2" />
                                                                <br/>
                                                                <span className="mt-4 pb-2">Male</span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div> :

                                                currentStep === 2 ?
                                                    <DiagnosticsSymptomsInput symptomsMap={symptomsMap}
                                                                              addSymptom={this.addSymptom} /> :

                                                    currentStep === 3 ?
                                                        <DiagnosticsQuestionsInput question={'The question here'}/> :
                                                        null
                                        }

                                        <div className="row form-group d-flex justify-content-end mr-2">
                                            <div className="">
                                                {   currentStep !== 0 &&
                                                <button onClick={this.goBack} type="button" className="btn btn-secondary mr-2">Back</button>
                                                }
                                                {
                                                    currentStep !== stepNum - 1 &&
                                                    <button onClick={this.goNext} type="button" className="btn btn-primary">Next</button>
                                                }
                                                {
                                                    currentStep === stepNum - 1 &&
                                                    <button onClick={this.submit} type="button" className="btn btn-primary">Finish</button>
                                                }
                                            </div>
                                        </div>
                                    </div>
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        );
    }
}

export default DiagnosticsContainer;