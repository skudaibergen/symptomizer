import numpy as np
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

    def predict_v2(self):
        # Removing prefix from symptom code
        self.symptoms = [element[14:] for element in self.symptoms]
        # symptoms_np = np.array(self.symptoms)
        print('Predicting by', self.symptoms)

        data_pivoted = pd.read_csv('assets/csv/disease_symptom_pivoted.csv')
        del data_pivoted['Unnamed: 0']

        cols = data_pivoted.columns.tolist()
        cols.remove('disease')
        x = data_pivoted[cols]
        y = data_pivoted.disease

        x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.33, random_state=42)

        mnb = MultinomialNB()
        mnb = mnb.fit(x, y)
        print('Score:', mnb.score(x, y))

        to_pred = x_train.iloc[0:0]
        to_pred.loc[len(to_pred)] = 0

        for s in self.symptoms:
            to_pred.at[0, s] = 1.0

        predicted = mnb.predict(to_pred)
        pred_proba = mnb.predict_proba(to_pred)
        max_proba = np.amax(pred_proba[0])

        print('Predicted Disease: ', predicted, 'max proba:', max_proba)

        # indices sorted by max elements
        max_inds = pred_proba.argsort()[-3:][::-1]
        # last 5 indices
        max_inds = max_inds[0][-5:]
        # reversed
        max_inds = max_inds[::-1]

        print('Predicted Diseases: ', y[max_inds].tolist())

        all_diseases = pd.read_csv('assets/csv/diseases.csv')

        # FIXME: temporary solution
        found_disease_code = all_diseases[all_diseases['Code'].str.contains(predicted[0])].iloc[0]['Code']

        vals = pred_proba[0][max_inds]
        keys = y[max_inds].tolist()
        keys = [all_diseases[all_diseases['Code'].str.contains(v)].iloc[0]['Code'] for v in keys]

        pred_key_val = dict(zip(keys, vals))
        print('pred_key_val', pred_key_val)

        res = dict()
        res['code'] = found_disease_code
        res['proba'] = max_proba
        res['closestPreds'] = pred_key_val

        return res

    class Predicted:
        def __init__(self, predicted, prediction_map):
            self.predicted = predicted
            self.prediction_map = prediction_map