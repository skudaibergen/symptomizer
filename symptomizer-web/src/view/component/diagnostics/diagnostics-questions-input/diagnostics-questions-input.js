import React from "react";

import './diagnostics-question-input.css';


function DiagnosticsQuestionsInput({ question, answerQuestion }) {
    return (
        <div className="mt-4 mb-4 pt-4 pb-4">
            <h3 className="text-center mb-4">{question}</h3>

            <div className=" d-flex justify-content-center">
                <div className="d-flex flex-row">
                    <button onClick={() => answerQuestion('YES')} className="question-input-button">
                        <i className="text-green fa fa-3x fa-check mt-2 pt-3" />
                        <br/>
                        <span className="mt-2 pb-2">Yes</span>
                    </button>

                    <button onClick={() => answerQuestion('NO')} className="question-input-button">
                        <i className="text-red fa fa-3x fa-times mt-2 pt-3" />
                        <br/>
                        <span className="mt-2 pb-2">No</span>
                    </button>

                    <button onClick={() => answerQuestion('DONT KNOW')} className="question-input-button">
                        <i className="text-gray fa fa-3x fa-arrow-right mt-2 pt-3" />
                        <br/>
                        <span className="mt-2 pb-2">Don't know</span>
                    </button>
                </div>
            </div>
        </div>
    );
}

export default DiagnosticsQuestionsInput;