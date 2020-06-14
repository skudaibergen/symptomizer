import React from "react";

function ModelPlot({}) {
    return (
        <div className="line-chart-example card">
            <div className="card-header d-flex align-items-center">
                <h3 className="h4">Model Line Chart</h3>
            </div>
            <div className="card-body">
                <canvas id="lineChartExample" />
            </div>
        </div>
    );
}

export default ModelPlot;