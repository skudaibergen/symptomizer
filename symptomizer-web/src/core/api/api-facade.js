import axios from 'axios';

export const api = {
    fetchSymptoms: (params = { grouped: true }) => axios.get('/symptoms', { params }),
    fetchDiseases: (params = { grouped: true }) => axios.get('/diseases', { params }),
    fetchQuestions: () => axios.get('/symptoms/questions'),
    predictDiagnosis: anamnesis => axios.post('/diagnostics', anamnesis)
};