import pandas as pd
import os
def remove_null_characters(path):
    return path.replace('\x00', '')

file_path = "path/with/null\x00/character"

full_url = os.getcwd()
t = f'\{full_url}\08_day8\BostonHousing.csv'
cleaned_path = remove_null_characters(t)

print(full_url)
print(cleaned_path)
# path = os.path.exists(cleaned_path)

raw_path = r'f{t}'
# path =os.getcwd()
# print(path)
df = pd.read_csv(r"d:/vhvl2024/2. HK2/04_laptrinhpython/01_source/src/08_day8/BostonHousing.csv", sep=",", header = 0, index_col = None)
df.head()
# df.info()

df.describe()
bins = [-999999, 250, 400, 999999]
labels = ['low', 'normal', 'high']

df['tax_labels'] = pd.cut(df['tax'], bins=bins, labels=labels)
print(df[df['tax_labels']=='high'].head())
print(df)