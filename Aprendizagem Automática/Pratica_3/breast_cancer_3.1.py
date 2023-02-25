import  pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.datasets import load_breast_cancer

from sklearn.utils import Bunch


cancer=load_breast_cancer()

data=cancer
df = pd.DataFrame(data.data,columns=data.feature_names)
df['target'] = data.target
selecao=df.iloc[:,[1,3,5,7,30]] #seleciona apenas colunas 1,3,5 e 7 (30 é a classe/target)

sns.pairplot(selecao, hue="target")
plt.show()


