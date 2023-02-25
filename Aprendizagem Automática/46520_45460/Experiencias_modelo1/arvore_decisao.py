# Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score

# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo

# Dividir os dados em conjuntos de treino e teste
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Define os hiperparâmetros que deseja otimizar
param_grid = {'max_depth': [1,2, 4, 6],
              'min_samples_split': [12,14,16]}

# Cria o otimizador
grid_search = GridSearchCV(DecisionTreeClassifier(), param_grid, cv=5)

# Inicia o processo de otimização
grid_search.fit(X_train, y_train)

# Imprime os melhores hiperparâmetros encontrados
print(grid_search.best_params_)

y_pred = grid_search.predict(X_test)

# Calcular a precisão
precision = precision_score(y_test, y_pred)

# Calcular a cobertura
recall = recall_score(y_test, y_pred)


# Imprimir os resultados
print('Precisão: ', precision*100, "%")
print('Cobertura: ', recall*100, "%")
