import pandas as pd
import matplotlib.pyplot as plt

# Carrega os dados em um dataframe
df = pd.read_csv("dropout-trabalho2.csv")

# Mostra as primeiras linhas do dataframe
print(df.head())

# Mostra um resumo estatístico dos atributos numéricos
print(df.describe())

# Mostra informações sobre o dataframe
print(df.info())

# Calcula a correlação entre os atributos
corr = df.corr()
print(corr)

# Seleciona apenas as colunas "grade" e "failure"
subset = df[["Rest_grade", "Failure"]]

# Plota um gráfico de dispersão entre as colunas selecionadas
plt.scatter(subset["Rest_grade"], subset["Failure"])
plt.xlabel("Rest_grade")
plt.ylabel("Failure")
plt.show()