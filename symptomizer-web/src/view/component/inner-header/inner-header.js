import React from "react";

function InnerHeader({ title }) {
    return (
        <header className="page-header">
            <div className="container-fluid">
                <h2 className="no-margin-bottom">{title}</h2>
            </div>
        </header>
    );
}

export default InnerHeader;