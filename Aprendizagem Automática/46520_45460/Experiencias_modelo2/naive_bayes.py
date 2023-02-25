import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, precision_score, recall_score

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Criar a nova coluna com a media global de _grade
df['mean_grade'] = df[['Y1s1_grade', 'Y1s2_grade', 'Y2s1_grade', 'Y2s2_grade', 'Y3s1_grade', 'Y3s2_grade', 'Y4s1_grade', 'Y4s2_grade', 'Rest_grade']].mean(axis=1)

# Criar a nova coluna com a média global de _enrol
df['mean_enrol'] = df[['Y0s1_enrol', 'Y0s2_enrol', 'Y1s1_enrol', 'Y1s2_enrol', 'Y2s1_enrol', 'Y2s2_enrol', 'Y3s1_enrol', 'Y3s2_enrol', 'Y4s1_enrol', 'Y4s2_enrol', 'Rest_enrol']].mean(axis=1)

# Selecionar apenas as colunas mean_grade, mean_enrol e failure
df_new = df[['mean_grade', 'mean_enrol', 'Failure']]

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df_new.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df_new['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

param_grid = {'var_smoothing': [1e-9, 1e-8, 1e-7, 1e-6, 1e-5]}

grid_search = GridSearchCV(GaussianNB(), param_grid, cv=5)

grid_search.fit(X_train, y_train)

print(grid_search.best_params_)

#Fazer previsões nos dados de teste
y_pred = grid_search.predict(X_test)

#Calcular a precisão
precision = precision_score(y_test, y_pred)

#Calcular a cobertura
recall = recall_score(y_test, y_pred)

#Imprimir os resultados
print('Precisão: ', precision*100, "%")
print('Cobertura: ', recall*100, "%")