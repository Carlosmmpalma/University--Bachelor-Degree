import matplotlib.pyplot as plt
import numpy as np


from sklearn.datasets import load_breast_cancer
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier


cancer=load_breast_cancer()

test_scores=[]
train_scores=[]
for i in range(1,100):
    print("cancer.keys(): \n{}".format(cancer.keys()))
    print("Shape of cancer data: {}".format(cancer.data.shape))
    print("Sample counts per class:\n{}".format(
    {n: v for n, v in zip(cancer.target_names, np.bincount(cancer.target))}))
    print("Feature names:\n{}".format(cancer.feature_names))

    X_train, X_test, y_train, y_test = train_test_split( #retiramos o conjunto de teste
	cancer['data'], cancer['target'], random_state=None)

    dtc=DecisionTreeClassifier(max_depth=i)
    dtc.fit(X_train, y_train)

    train_accuracy=dtc.score(X_train,y_train)
    test_accuracy=dtc.score(X_test,y_test)

    test_scores= np.append(test_scores,test_accuracy)
    train_scores=np.append(train_scores,train_accuracy)

print("TEST SCORES:",test_scores)
print("TRAIN SCORES:",train_scores)

plt.plot((range(1,100)),train_scores)
plt.plot((range(1,100)),test_scores)

plt.show()

   