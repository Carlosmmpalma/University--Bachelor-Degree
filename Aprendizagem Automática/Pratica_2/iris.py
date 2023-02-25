import matplotlib.pyplot as plt
import numpy as np

from sklearn.datasets import load_iris

from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix



iris = load_iris() #Load and return the iris dataset (classification).

print("iris.keys(): \n{}".format(iris.keys()))
print("Shape of iris data: {}".format(iris.data.shape))
print("Sample counts per class:\n{}".format(
{n: v for n, v in zip(iris.target_names, np.bincount(iris.target))}))
print("Feature names:\n{}".format(iris.feature_names))

X_train, X_test, y_train, y_test = train_test_split( #retiramos o conjunto de teste
	iris['data'], iris['target'], random_state=0)

knn = KNeighborsClassifier(n_neighbors=1)
knn.fit(X_train, y_train)
pred_knn = knn.predict(X_test)

confusion = confusion_matrix(y_test, pred_knn)
print("Confusion matrix:\n{}".format(confusion))
print("Test set accuracy: {:.2f}".format(knn.score(X_test, y_test)))



size=100

while(size != 0):
	X_train2, X_test2, y_train2, y_test2 = train_test_split( #retiramos o conjunto de teste
	X_train,y_train,test_size=size,random_state=0)

	knn = KNeighborsClassifier(n_neighbors=1)
	knn.fit(X_train2, y_train2)
	pred_knn = knn.predict(X_test2)

	confusion = confusion_matrix(y_test2, pred_knn)
	print("Confusion matrix:\n{}".format(confusion))
	print("Test set accuracy: {:.2f}".format(knn.score(X_test2, y_test2)))

	xpoints = np.array([(knn.score(X_test2, y_test2)),size])
	ypoints = np.array([(knn.score(X_test2, y_test2)),size])

	plt.plot(xpoints, ypoints)
	plt.show()	

	size=size-20

#Elementos no conjunto de teste é somar todos os elementos da matriz
#Percentagem de elementos no conjunto de teste é numero de elementos no conjunto de testes/150
#Por default é 25% para treino e 75% para teste