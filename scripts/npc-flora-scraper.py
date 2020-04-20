# TODO: For http://www.nativeplantcenter.net/plants/

import requests
from bs4 import BeautifulSoup as bs
import json
url="http://www.nativeplantcenter.net/plants/"
links=[]
r=requests.get(url)

if r.status_code==200:
    html=bs(r.text,features="html.parser")
    num=int(html.findAll("a",{"class", "page-numbers"})[-2].text)
for i in range(num):
    r=requests.get(url+"page/"+str(i+1))
    if r.status_code==200:
        html=bs(r.text,features="html.parser")
        for a in html.findAll("div",{"class":"recomended-plants-title"}):
            link=a.find_parents("a")[0]["href"]
            print(link)
            links.append(link)
'''
for l in links:
    with open("links.txt","w") as file:
        file.write(l+"\n")
'''
plants={}
all_images=[]
for plant in links:
    print("fetching "+plant+" now... please wait")
    r=requests.get(plant)
    if r.status_code==200:
        html=bs(r.text,features="html.parser")
        name=html.find(class_="title-left single_page_title").text
        plants[name]={}
        plants[name]["link"]=plant
        alias=html.find("strong").text.lstrip().split(", ")
        plants[name]["alias"]=alias
        imgs=html.findAll(class_="attachment-74x74 size-74x74")
        imgs_list=[]
        for img in imgs:
            i=img["src"].replace("-150x150","")
            imgs_list.append(i)
            all_images.append(i)
        plants[name]["image"]=imgs_list
        attr=html.find(class_="col-md-5 col-sm-6").findAll("p")
        for i in attr:
            if len(i.text.strip())>1:
                print(i.text)
                if i.text.strip()[0]!=";":
                    tmp=i.text.split(":")
                    key=tmp[0].lower()
                    value=' '.join(tmp[1].split())
                    plants[name][key]=value
                else:
                    plants[name][key]=value+' '.join(i.text.split())
        html.findAll("a",{"class","single_plant_thumb_nav_link"})

with open("images.txt", "w") as file:
    for i in imgs_list:
        file.write(i+"\n")
with open("plants.json", "w") as file:
    file.write(json.dumps(plants))
