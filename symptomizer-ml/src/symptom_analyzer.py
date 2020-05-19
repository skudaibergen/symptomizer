import pandas as pd
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import train_test_split


class SymptomAnalyzer:
    def __init__(self, symptoms):
        self.symptoms = symptoms

    def predict(self):
        # Removing prefix from symptom code
        self.symptoms = [element[14:] for element in self.symptoms]
        # symptoms_np = np.array(self.symptoms)
        print('Predicting by', self.symptoms)

        # FIXME: Pivot data on the fly
        data_pivoted = pd.read_csv('assets/csv/data_pivoted.csv')
        data_pivoted = data_pivoted.fillna(0)

        cols = data_pivoted.columns.tolist()
        cols.remove('disease')
        x = data_pivoted[cols]
        y = data_pivoted.disease

        x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33, random_state=42)
        mnb = MultinomialNB()
        mnb = mnb.fit(x_train, y_train)

        to_pred = pd.read_csv('assets/csv/test.csv')
        to_pred = to_pred.fillna(0)
        colst = to_pred.columns.tolist()
        colst.remove('disease')
        to_pred = to_pred[colst]

        for s in self.symptoms:
            to_pred.at[0, s] = 1.0

        predicted = mnb.predict(to_pred)

        # print('Predicted: ', predicted)

        all_diseases = pd.read_csv('assets/csv/diseases.csv')

        # FIXME: temporary solution
        found_disease_code = all_diseases[all_diseases['Code'].str.contains(predicted[0])].iloc[0]['Code']

        return found_disease_code
