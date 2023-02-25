# Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.ensemble import RandomForestClassifier
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

# Defina os parâmetros desejados
params = {'max_depth': 8, 'min_samples_split': 16, 'n_estimators': 800}

# Crie o modelo
modelo = RandomForestClassifier(**params)

# Fit the model using the grid search
modelo.fit(X_train, y_train)

# Inicia o processo de otimização
modelo.fit(X_train, y_train)



