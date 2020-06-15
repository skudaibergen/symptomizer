import React from "react";

import './diagnostics-question-input.css';


class DiagnosticsQuestionsInput extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentQuestionIdx: 0,
            questions: this.props.questions,
            symptomIds: [],
            symptomCodes: [],
        };
    }

    answerQuestion = (answer) => {
        const { currentQuestionIdx, questions, symptomIds, symptomCodes } = this.state;

        if (answer) {
            let currQuestion = questions[currentQuestionIdx];

            if (answer === 'YES') {
                symptomIds.push(currQuestion.symptomId);
                symptomCodes.push(currQuestion.symptomCode);
            }

            console.log("SOOOQ", questions.length, currentQuestionIdx)
            if (questions.length > currentQuestionIdx + 1)
                this.setState({ currentQuestionIdx: currentQuestionIdx + 1 });
            else
                this.props.answerQuestions(symptomIds, symptomCodes);
        }
    };

    render() {
        const { currentQuestionIdx, questions } = this.state;

        return (
            <div className="mt-4 mb-4 pt-4 pb-4">
                <h3 className="text-center mb-4">{questions.length > currentQuestionIdx ? questions[currentQuestionIdx].name : 'Unknown'}</h3>

                <div className=" d-flex justify-content-center">
                    <div className="d-flex flex-row">
                        <button onClick={() => this.answerQuestion('YES')} className="question-input-button">
                            <i className="text-green fa fa-3x fa-check mt-2 pt-3" />
                            <br/>
                            <span className="mt-2 pb-2">Yes</span>
                        </button>

                        <button onClick={() => this.answerQuestion('NO')} className="question-input-button">
                            <i className="text-red fa fa-3x fa-times mt-2 pt-3" />
                            <br/>
                            <span className="mt-2 pb-2">No</span>
                        </button>

                        <button onClick={() => this.answerQuestion('DONT KNOW')} className="question-input-button">
                            <i className="text-gray fa fa-3x fa-arrow-right mt-2 pt-3" />
                            <br/>
                            <span className="mt-2 pb-2">Don't know</span>
                        </button>
                    </div>
                </div>
            </div>
        );
    }
}

export default DiagnosticsQuestionsInput;