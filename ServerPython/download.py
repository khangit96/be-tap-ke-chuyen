import urllib 
import pyrebase
import os
import threading
from playsound import playsound

config = {
  "apiKey": "uTP6tlP930oA1s9zuwGIZvrz1ef8ZjVLegROgNN0",
  "authDomain": "smarthome-5d11a.firebaseapp.com",
  "databaseURL": "https://smarthome-5d11a.firebaseio.com",
  "storageBucket": "smarthome-5d11a.appspot.com"
}
check=False
firebase = pyrebase.initialize_app(config)
db = firebase.database()

#Play voice
def playVoice(arg,arg2):
     os.system("mpg123 "+arg)

def stream_handler(message):
    global check
    if check:
        fileUrl =message["data"]["urlDownload"]
        fileName=message["data"]["fileName"]
        urllib.urlretrieve(fileUrl,fileName)
        os.system('omxplayer -o both '+fileName)

        print('done')
    else:
      print('listening')
      check= True
  
my_stream = db.child("BeTapKeChuyen/LastGhiAm").stream(stream_handler)
