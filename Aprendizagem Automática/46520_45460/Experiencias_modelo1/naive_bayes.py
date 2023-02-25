import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score, precision_score, recall_score

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

param_grid = {'var_smoothing': [1e-14,1e-13,1e-12,1e-11,1e-10,1e-9, 1e-8, 1e-7, 1e-6, 1e-5]}

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