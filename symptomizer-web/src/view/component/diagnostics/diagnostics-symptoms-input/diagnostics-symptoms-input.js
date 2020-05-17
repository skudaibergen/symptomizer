import React from "react";


function DiagnosticsSymptomsInput({ symptomsMap, addSymptom }) {
    return (
        <div>
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
                                                <input id={'symptom-checkbox-' + symptom.id}
                                                       type="checkbox"
                                                       className="checkbox-template" onClick={e => addSymptom(symptom, e.target.checked)}/>
                                                <label htmlFor={'symptom-checkbox-' + symptom.id}>{ symptom.name }</label>
                                            </div>
                                        )
                                    }
                                </div>
                            </div>
                        </div>
                    </div>)}
            </div>
        </div>
    );
}

export default DiagnosticsSymptomsInput;