# TODO: for excel spreadsheet
import pandas as pd
import json
df=pd.read_excel("sunny-edge-plants-data.xlsx")
print(df)
keys=df.keys()
plants={}

for i in range(len(df)):
    name=df.loc[i][0].strip().split("/")[0]
    plants[name]={}
    plants[name]["latin"]=df.loc[i][0].strip().split("/")[1]
    for j in range(1,len(df.loc[i])):
        value="None" if df.loc[i][j]!=df.loc[i][j] else df.loc[i][j]
        plants[name][keys[j]]=value


with open("sunny-edge-plants-data.json", "w") as file:
    file.write(json.dumps(plants))