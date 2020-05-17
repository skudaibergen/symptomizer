import React from "react";


function DiagnosticsResultCard({ predictionResult }) {
    return (
        <div>
            <h3 className="text-center">Results</h3>

            <div className="row mt-4">
                <div className="col-lg-12">
                    <h4>Predicted disease:</h4>
                    <span className="text-primary">{predictionResult.predictedDisease.name}</span>
                    {/*- {predictionResult.predictedDisease.description}*/}
                </div>
                <div className="col-lg-12 mt-4">
                    <h4>Suggestions:</h4>
                    <span>Please contact your local emergency</span>
                </div>
            </div>
        </div>
    );
}

export default DiagnosticsResultCard;