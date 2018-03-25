import pyrebase
import os
import threading

config = {
  "apiKey": "uTP6tlP930oA1s9zuwGIZvrz1ef8ZjVLegROgNN0",
  "authDomain": "smarthome-5d11a.firebaseapp.com",
  "databaseURL": "https://smarthome-5d11a.firebaseio.com",
  "storageBucket": "smarthome-5d11a.appspot.com"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()

#Play story
def playStory(arg,arg2):
    if arg=="ruavatho":
       os.system("mpg123 ruavatho.mp3")
    else:
       os.system("mpg123 haichacon.mp3")

#Stop story          
def stopStory():
     os.system("pkill mpg123")
    
def stream_haiChaCon(message):
     value=message["data"]

     if value==True:  
        thread = threading.Thread(target=playStory, args=("haichacon",'fkf'))
        thread.start()
        print('start hai cha con')
     else:
        stopStory()
        print('stop hai cha con')

haChaConStream = db.child("BeTapKeChuyen/1").stream(stream_haiChaCon)

def stream_ruaVaTho(message):
     value=message["data"]

     if value==True:  
        thread = threading.Thread(target=playStory, args=("ruavatho",'fkf'))
        thread.start()
        print('start rua va tho')
     else:
        stopStory()
        print('stop rua va tho')

ruaVaThoStream = db.child("BeTapKeChuyen/2").stream(stream_ruaVaTho)