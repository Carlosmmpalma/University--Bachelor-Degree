import pandas as pd
from sklearn.metrics import recall_score
from sklearn.metrics import precision_score

from trabalho2_secundario import modelo

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Criar a nova coluna com a media global de _grade
df['mean_grade'] = df[['Y1s1_grade', 'Y1s2_grade', 'Y2s1_grade', 'Y2s2_grade', 'Y3s1_grade', 'Y3s2_grade', 'Y4s1_grade', 'Y4s2_grade', 'Rest_grade']].mean(axis=1)

# Criar a nova coluna com a média global de _enrol
df['mean_enrol'] = df[['Y0s1_enrol', 'Y0s2_enrol', 'Y1s1_enrol', 'Y1s2_enrol', 'Y2s1_enrol', 'Y2s2_enrol', 'Y3s1_enrol', 'Y3s2_enrol', 'Y4s1_enrol', 'Y4s2_enrol', 'Rest_enrol']].mean(axis=1)

# Selecionar apenas as colunas mean_grade, mean_enrol e failure
df_new = df[['mean_grade', 'mean_enrol', 'Failure']]

# Separar as colunas de atributos (X) e a coluna alvo (y)
X_test = df_new.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y_test = df_new['Failure']  # Apenas a coluna alvo

y_pred_test = modelo.predict(X_test)

print("Conjunto de teste - cobertura para a classe positiva: {:.2f}".format(recall_score(y_test, y_pred_test,pos_label=1)))
print("Conjunto de teste -  precisão para a classe positiva: {:.2f}".format(precision_score(y_test, y_pred_test,pos_label=1)))
