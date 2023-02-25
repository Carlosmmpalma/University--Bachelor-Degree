import pandas as pd
from sklearn.metrics import recall_score
from sklearn.metrics import precision_score

from trabalho2 import modelo

test = pd.read_csv("dropout-test.csv")
X_test =  test.drop('Failure',axis='columns') 
y_test =  test['Failure']
y_pred_test = modelo.predict(X_test)

print("Conjunto de teste - cobertura para a classe positiva: {:.2f}".format(recall_score(y_test, y_pred_test,pos_label=1)))
print("Conjunto de teste -  precisão para a classe positiva: {:.2f}".format(precision_score(y_test, y_pred_test,pos_label=1)))
