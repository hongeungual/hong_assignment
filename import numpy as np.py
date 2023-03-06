import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.svm import SVC
from eegclassifier import EEGClassifier

# EEG 데이터 불러오기
X = np.loadtxt('sample.csv', delimiter=',')
y = np.loadtxt('labels.csv', delimiter=',')

# 데이터 분할
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

# EEGClassifier 생성
clf = EEGClassifier(make_pipeline(StandardScaler(), SVC(kernel='rbf', gamma='scale')), 
                    classes=[0, 1], scoring='roc_auc', random_state=42)

# 모델 학습 및 예측
clf.fit(X_train, y_train)
y_pred = clf.predict(X_test)

# 모델 평가
clf.score(X_test, y_test)

