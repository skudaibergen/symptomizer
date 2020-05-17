import React from 'react';

import CircularProgress from '@material-ui/core/CircularProgress';

function LoaderMaterial({ children, loading, className }) {
    return (
        <div>
            { !loading && children }

            { loading &&
            <div className="d-flex align-items-center justify-content-center">
                <CircularProgress className={className} style={{ color: '#5D5F66' }} />
            </div>
            }
        </div>
    );
}

export default LoaderMaterial;