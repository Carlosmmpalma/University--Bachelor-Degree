# Importar os módulos necessários
import pandas as pd
from sklearn.model_selection import GridSearchCV, train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, precision_score, recall_score


# Carregar os dados
df = pd.read_csv('dropout-trabalho2.csv')

# Separar as colunas de atributos (X) e a coluna alvo (y)
X = df.drop('Failure', axis=1)  # Todas as colunas, exceto a coluna alvo
y = df['Failure']  # Apenas a coluna alvo
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Defina os parâmetros desejados
params = {'max_depth': 12, 'min_samples_split': 3, 'n_estimators': 200}

# Crie o modelo
modelo = RandomForestClassifier(**params)

# Fit the model using the grid search
modelo.fit(X_train, y_train)

# Inicia o processo de otimização
modelo.fit(X_train, y_train)



