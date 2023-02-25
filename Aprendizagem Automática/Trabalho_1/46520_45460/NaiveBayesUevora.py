import numpy as np 

class  NaiveBayesUevora:

	def __init__(self,alpha):

		""" P=(Probabilidade dos atributos tendo em conta a classe*Probabilidade da classe)
			/Probabilidade independente de cada feature"""	

		self.features = list #Lista para features
		self.alpha = alpha #Alpha escolhido pelo utilizador
		self.featureperclass = {} #Dicionário para probabilidade de cada feature por classe
		self.probyesorno = {} #Dicionário para probabilidade à priori de cada classe
		self.featuretotals = {} #Dicionário para probabilidade à priori de cad afeature

		self.X_train = np.array
		self.y_train = np.array
		self.train_size = int
		self.num_feats = int
		self._classes = int

	def fit(self, X, y):

		self.features = list(X.columns) #Lista com as features(Class,age,menopause...)
		self.X_train = X #Tabela tirando classes
		self.y_train = y #Tabela das classes (YES/NO)
		self.train_size = X.shape[0] #Numero de elementos da tabela X
		self.num_feats = X.shape[1] #Numero de elementos da tabela y
		self._classes = len(np.unique(y)) #Numero de classes

		#Inicializar com 0's todos os valores nos dicionários
		for feature in self.features:
			self.featureperclass[feature] = {}
			self.featuretotals[feature] = {}

			for feat_val in np.unique(self.X_train[feature]):
				self.featuretotals[feature].update({feat_val: 0})
				for outcome in np.unique(self.y_train):
					self.featureperclass[feature].update({feat_val+'_'+outcome:0})
					self.probyesorno.update({outcome: 0})

		self._calc_probyesorno()
		self._calc_featureperclass()
		self._calc_featuretotal()

	def _calc_probyesorno(self):

		""" P(Yes) && P(No) - probabilidade de cada classe """

		for outcome in np.unique(self.y_train.to_numpy()):
			outcome_count = sum(self.y_train == outcome) #Verifica numero de NO e YES
			self.probyesorno[outcome] = (outcome_count +self.alpha)  / ((self.train_size) + (self.alpha * self._classes))  # type: ignore
		
	def _calc_featureperclass(self):

		""" P(feature|classe) - probabilidade de cada atributo por classe """

		for feature in self.features: # type: ignore #Percorre as features(colunas)
			for outcome in np.unique(self.y_train.to_numpy()):  
				outcome_count = sum(self.y_train == outcome) #Numero de NO e YES

				#Calcula e guarda o numero de ocorrencias de cada feature
				feat_featureperclass = self.X_train[feature][self.y_train[self.y_train == outcome].index.values.tolist()].value_counts().to_dict()    # type: ignore
				#Calcula e guarda a probabilidade de cada feature consoante a classe e guarda indicando a classe
				for feat_val, count in feat_featureperclass.items():
					"""feature_classe(20-29_no)>ç<=>P=(n_ocorrencias(20-29)/n_ocorrencias(no))"""
					self.featureperclass[feature][feat_val + '_' + outcome] = (count+self.alpha)/(outcome_count + (self.alpha * self._classes))

	def _calc_featuretotal(self):

		""" P(feature) - probabilidade total de cada atributo """

		for feature in self.features:    # type: ignore

			#numero probyesorno de ocorrencias de cada feature
			feat_vals = self.X_train[feature].value_counts().to_dict()    # type: ignore
			for feat_val, count in feat_vals.items():
				#Calcula e guarda no respetivo sitio a probabilidade de cada feature ocorrer indepentemente da classe
				self.featuretotals[feature][feat_val] = (count+self.alpha)/(self.train_size + (self.alpha * self._classes))

	def predict(self, X):

		""" Calcula a probabilidade tendo em conta o conjunto de dados de teste """
		results = []
		X = np.array(X)

		for query in X:
			probs_outcome = {}
			for outcome in np.unique(self.y_train.to_numpy()):
				prior = self.probyesorno[outcome]
				featureperclass = 1
				featureind = 1

				for feat, feat_val in zip(self.features, query):  # type: ignore
					
					#Se exisitir nos dados de treino 
					if((feat_val + '_no-recurrence-events' or feat_val+ '_recurrence-events') in self.featureperclass[feat].keys()): 
						#Probabilidade de cada feature segundo a classe
						featureperclass *= self.featureperclass[feat][feat_val + '_' + outcome]
						#Probabilidade independente de cada feature
						featureind *= self.featuretotals[feat][feat_val]
					#Se não existir nos dados de treino
					else:
						featureperclass*=1
						featureind*=1

				#Calcula a probabilidade maior(YES OR NO)
				posterior = (featureperclass * prior )/featureind
				probs_outcome[outcome] = posterior
				
			#Verifica a maior probabilidade e define o resultado
			result = max(probs_outcome, key = lambda x: probs_outcome[x])
			
			results.append(result)		

		return np.array(results)

	def accuracy(self,X,y):
		count=0

		for pred,res in zip(X,y):  #Verifica Verdadeiros positivos
			if(pred==res):
				count+=1

		acur=count/len(y) #Calcula a accuracy

		return acur

	def precision_score(self,X,y):
		#Classes
		classes=np.unique(X)
		n_classes=len(classes)
		total=0
		
		#VP/VP+FP
		count_vp=0
		count_fp=0

		for c in classes:
			for pred,res in zip(X,y):
				if(pred==c and pred==res):
					count_vp+=1
				elif(pred==c and pred!=res):
					count_fp+=1
			if(count_fp+count_vp==0):
				prec=0
			else:
				prec=count_vp/(count_fp+count_vp)
			
			total=total+prec

		return total/n_classes
