import pandas as pd
from NaiveBayesUevora import NaiveBayesUevora	

#Carregar dados de treino
train = pd.read_csv('breast-cancer-train2.csv')
train = train.astype(str)

X_train = train.drop([train.columns[0]], axis = 1) #Features
y_train = train[train.columns[0]] #YES/NO

#Carregar dados de teste
test= pd.read_csv('breast-cancer-test2.csv')
test = test.astype(str)

X_test = test.drop([train.columns[0]], axis = 1) #Features
y_test = test[train.columns[0]] #YES/NO


alpha = input("Digite o alpha:")

nbu = NaiveBayesUevora(int(alpha))
nbu.fit(X_train, y_train)

predictions=nbu.predict(X_test)

print("ACCURACY: ",nbu.accuracy(predictions,y_test)*100,"%")
print("PRECISION: ",nbu.precision_score(predictions,y_test)*100,"%")