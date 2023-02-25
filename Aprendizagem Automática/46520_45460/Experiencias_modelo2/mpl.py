#Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.metrics import  precision_score, recall_score
from sklearn.metrics import make_scorer, f1_score
from sklearn.neural_network import MLPClassifier

def f1_eval(y_true, y_pred):
    precision = precision_score(y_true, y_pred)
    if precision < 0.7:
        return 0
    return f1_score(y_true, y_pred)

scorer = make_scorer(f1_eval, greater_is_better=True)

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

# Crie o modelo de MLP
model = MLPClassifier()

# Defina os valores a serem testados para os hiperparâmetros
param_grid = {'hidden_layer_sizes': [(60,200)],
              'max_iter': [500, 1000, 2000],
              'alpha': [0.0001, 0.001, 0.01]}

# Crie o objeto GridSearchCV
grid_search = GridSearchCV(model, param_grid, scoring=scorer, refit=True, cv=5)

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