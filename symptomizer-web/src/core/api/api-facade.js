import axios from 'axios';

export const api = {
    fetchSymptoms: () => axios.get('/symptoms?grouped=true'),
    fetchQuestions: () => axios.get('/symptoms/questions'),
    predictDiagnosis: anamnesis => axios.post('/diagnostics', anamnesis)
};