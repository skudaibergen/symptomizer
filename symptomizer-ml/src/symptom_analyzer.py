
class SymptomAnalyzer:
    def __init__(self, symptoms):
        self.symptoms = symptoms

    def predict(self):
        for s in self.symptoms:
            print('PREDICTTIN', s)
        return 'UMLS:C0011847_diabetes'
