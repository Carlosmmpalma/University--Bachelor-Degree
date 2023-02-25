#Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

#Criar o modelo
model = KNeighborsClassifier()

# Define the hyperparameter grid
param_grid = {'n_neighbors': [1,3, 5, 7, 9, 11],
              'metric': ['euclidean', 'manhattan'],
              'weights': ['uniform', 'distance']}

# Create the grid search object
grid_search = GridSearchCV(model, param_grid, cv=5)

# Fit the model using the grid search
grid_search.fit(X_train, y_train)

# Print the best hyperparameters found
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