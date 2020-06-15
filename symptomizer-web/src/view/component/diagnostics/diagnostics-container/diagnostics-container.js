import React from "react";

import { makeStyles } from '@material-ui/core/styles';
import Stepper from '@material-ui/core/Stepper';
import Step from '@material-ui/core/Step';
import StepLabel from '@material-ui/core/StepLabel';
import _ from 'lodash';

import DiagnosticsSymptomsInput from "../diagnostics-symptoms-input/diagnostics-symptoms-input";
import DiagnosticsQuestionsInput from "../diagnostics-questions-input/diagnostics-questions-input";
import DiagnosticsResultCard from "../diagnostics-result-card/diagnostics-result-card";

import './diagnostics-container.css'




const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
    button: {
        marginRight: theme.spacing(1),
    },
    instructions: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
    },
}));

function getSteps() {
    return ['Begin', 'Choose your sex', 'Select symptoms', 'Interview', 'Results'];
}

function HorizontalLinearStepper({ currentStep }) {
    const classes = useStyles();
    const steps = getSteps();

    return (
        <div className={classes.root}>
            <Stepper activeStep={currentStep}>
                {steps.map((label) => {
                    const stepProps = {};
                    const labelProps = {};

                    return (
                        <Step key={label} {...stepProps}>
                            <StepLabel {...labelProps}>{label}</StepLabel>
                        </Step>
                    );
                })}
            </Stepper>
        </div>
    );
}

class DiagnosticsContainer extends React.Component{

    constructor(props) {
        super(props);

        this.state = {
            anamnesis: this.props.anamnesis ?  this.props.anamnesis : {
                sex: '',
                age: 0,
                symptomIds: [],
                symptomCodes: [],
            },
            currentStep: this.props.currentStep ? this.props.currentStep : 0,
            stepNum: 3 + 1 // TODO: Questions length from state
                // + (this.props.symptomQuestions && this.props.symptomQuestions.length ?
                // this.props.symptomQuestions.length :
                // 0)
            ,
            isQuestion: false
        }
    }

    submit = () => {
        const { currentStep, anamnesis } = this.state;
        this.props.submit(anamnesis, 1 + currentStep);
    };

    submitFinal = (currentStep, anamnesis) => {
        this.props.submit(anamnesis, 1 + currentStep);
    };

    goNext = () => {
        let currentStep = this.state.currentStep;

        if (currentStep !== this.state.stepNum - 1) {
            if (currentStep === 2) {
                this.submit();
            }

            currentStep++;
            let isQuestion = currentStep === 1 || currentStep >= 3;
            this.setState({
                currentStep,
                isQuestion
            })
        }
    };

    goBack = () => {
        let currentStep = this.state.currentStep;

        if (currentStep !== 0) {
            currentStep--;
            let isQuestion = currentStep === 1 || currentStep >= 3;
            this.setState({
                currentStep,
                isQuestion
            })
        }
    };

    addSymptom = (symptom, checked) => {
        if (symptom) {
            const anamnesis = this.state.anamnesis;
            let symptomIds = anamnesis.symptomIds,
                symptomCodes = anamnesis.symptomCodes;

            if (checked) {
                symptomIds.push(symptom.id);
                symptomCodes.push(symptom.code);
            } else {
                symptomIds.splice(symptomIds.findIndex(id => id === symptom.id), 1);
                symptomCodes.splice(symptomCodes.findIndex(code => code === symptom.code), 1);
            }

            let newAnamnesis = { ...anamnesis, symptomIds, symptomCodes };
            this.setState({ anamnesis: newAnamnesis });
        }
    };

    changeSex = (sex) => {
        if (sex) {
            const anamnesis = this.state.anamnesis;
            let newAnamnesis = { ...anamnesis, sex };
            this.setState({ anamnesis: newAnamnesis });
            this.goNext();
        }
    };

    answerQuestions = (symptomIds, symptomCodes) => {
        const anamnesis = this.state.anamnesis;
        let symptomIds_ = anamnesis.symptomIds,
            symptomCodes_ = anamnesis.symptomCodes;

        if (symptomCodes && symptomCodes) {

            let newAnamnesis = {
                ...anamnesis,
                symptomIds: _.union(symptomIds_, symptomIds),
                symptomCodes: _.union(symptomCodes_, symptomCodes)
            };

            this.setState({ anamnesis: newAnamnesis });
            this.submitFinal(this.state.currentStep, newAnamnesis);
        }

    };

    render() {
        const { currentStep, stepNum, isQuestion } = this.state;
        const { symptomsMap, predictionResult } = this.props;

        return (
            <div className="row">
                <div className="col-lg-12">
                    <HorizontalLinearStepper currentStep={currentStep} />
                </div>

                <div className="col-lg-12">
                    <div className="diagnostics-container-card card">
                        {   predictionResult && (!predictionResult.questions || !predictionResult.questions.length) ?
                            <div className="card-body">
                                <DiagnosticsResultCard predictionResult={predictionResult} />
                            </div> :
                            <div className="card-body">
                                {   currentStep === 0 ?
                                    <div>
                                        <h3 className="text-center mb-4">Terms of Service</h3>

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
                                            <h3 className="text-center mb-4">Please choose your sex</h3>

                                            <div className="mt-4 mb-4 pt-4 pb-4 d-flex justify-content-center">
                                                <div className="d-flex flex-row">
                                                    <button onClick={() => this.changeSex('FEMALE')} className="sex-input-button">
                                                        <i className="fa fa-5x fa-female mt-2 pt-2" />
                                                        <br/>
                                                        <span className="mt-4 pb-2">Female</span>
                                                    </button>

                                                    <button onClick={() => this.changeSex('MALE')} className="sex-input-button">
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

                                    currentStep >= 3 && predictionResult.questions ?
                                        <DiagnosticsQuestionsInput questions={predictionResult.questions} answerQuestions={this.answerQuestions} />
                                        :
                                        null
                                }


                                <div className="row form-group d-flex justify-content-end mr-2">
                                    <div className="">
                                        {   currentStep !== 0 &&
                                        <button onClick={this.goBack} type="button" className="btn btn-secondary mr-2">Back</button>
                                        }
                                        {   !isQuestion && currentStep !== stepNum - 1 &&
                                        <button onClick={this.goNext} type="button" className="btn btn-primary">Next</button>
                                        }
                                        {   !isQuestion && currentStep === stepNum - 1 &&
                                        <button onClick={this.submit} type="button" className="btn btn-primary">Finish</button>
                                        }
                                    </div>
                                </div>

                            </div>
                        }
                    </div>
                </div>
            </div>
        );
    }
}

export default DiagnosticsContainer;