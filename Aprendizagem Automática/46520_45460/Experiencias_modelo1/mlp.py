#Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.metrics import  precision_score, recall_score
from sklearn.metrics import make_scorer, f1_score
from sklearn.neural_network import MLPClassifier

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Crie o modelo de MLP
model = MLPClassifier()

# Defina os valores a serem testados para os hiperparâmetros
param_grid = {'hidden_layer_sizes': [(100,200),(230,90),(140,90),(5,40)],
              'max_iter': [500, 1000, 2000],
              'alpha': [0.0001, 0.001, 0.01]}

# Crie o objeto GridSearchCV
grid_search = GridSearchCV(model, param_grid, scoring='recall',cv=5)

# Treine o modelo usando a validação cruzada
grid_search.fit(X_train, y_train)

# Imprima os melhores parâmetros encontrados
print("Melhores parâmetros: ", grid_search.best_params_)

# Faça previsões nos dados de teste com o modelo otimizado
y_pred = grid_search.predict(X_test)

# Calcular a precisão
precision = precision_score(y_test, y_pred)

# Calcular a cobertura
recall = recall_score(y_test, y_pred)

# Imprimir os resultados
print('Precisão: ', precision*100, "%")
print('Cobertura: ', recall*100, "%")