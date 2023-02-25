import pandas as pd
from sklearn.naive_bayes import CategoricalNB
from sklearn.model_selection import train_test_split

#1) Carregar dados

data = pd.read_csv("car.csv")

#2)Atributos X e classe y

X=data.iloc[:,:-1]
y=data.iloc[:,-1]

#3) Split de treino teste

X_train, X_test, y_train, y_test = train_test_split( #retiramos o conjunto de teste
	X, y, random_state=None)

#4)fit usando CategoricalNB

clf = CategoricalNB()
clf.fit(X, y)

print(clf.predict(X[2:3]))

