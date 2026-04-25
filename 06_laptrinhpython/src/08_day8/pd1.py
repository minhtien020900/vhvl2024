import pandas as pd
import matplotlib.pyplot as plt
df = pd.read_csv(r"d:/vhvl2024/2. HK2/04_laptrinhpython/01_source/src/08_day8/Churn_Modelling.csv", sep=",", header=0, index_col='CustomerId')


# df['age_labels'] = pd.qcut(df['Age'], q=5, labels=['very young', 'young', 'middle-aged', 'old', 'very old'])

# df_summary = df[['age_labels','Age']].groupby('age_labels').count()

# df_summary.plot.bar()
# plt.show()
# data  = {'a':123,'b':456,'d':789}
# ser = pd.Series(data, index=['x', 'b', 'y'])
# print(ser)

# s = {'mot': pd.Series([1, 2, 3], index=['a', 'b', 'c']),
#      'hai': pd.Series([4, 5, 6], index=['a', 'b', 'g'])}

# df2= pd.DataFrame(s)
# df2= pd.DataFrame([''], index=['a', 'b', 'c'], columns=['d'])
# print(df2)

sapxep = df[df['Age'] > 70].sort_values('CustomerId').sort_values('Balance')
print(sapxep.head())