# Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import precision_score, recall_score

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Criar o modelo de árvore de decisão
model = RandomForestClassifier()

# Define os hiperparâmetros que deseja otimizar
param_grid = {'n_estimators': [100,200,500,800,1000],
              'max_depth': [8, 10,12,14,20],
              'min_samples_split': [1,2,3,4, 8, 16]}

# Cria o otimizador
grid_search = GridSearchCV(RandomForestClassifier(), param_grid, cv=5)


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