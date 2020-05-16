import axios from 'axios';

export const api = {
    fetchSymptoms: () => axios.get('/symptoms?grouped=true'),
    predictDiagnosis: anamnesis => axios.post('/diagnostics', anamnesis)
};