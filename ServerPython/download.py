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

urllib.urlretrieve('https://firebasestorage.googleapis.com/v0/b/smarthome-5d11a.appspot.com/o/G6CR37O1JTRIBIWE3T.mp3?alt=media&token=e37b635d-12c5-4771-8a49-b30a30e8d01d','khang.mp3')
#Play voice
def playVoice(arg,arg2):
     os.system("mpg123 "+arg)

def stream_handler(message):
    global check
    if check:
        fileUrl =message["data"]["urlDownload"]
        fileName=message["data"]["fileName"]
        urllib.urlretrieve(fileUrl,fileName)
        # thread = threading.Thread(target=playVoice, args=(message["data"]["fileName"],'fkf'))
        # thread.start()
        playsound(fileName)
        print('done')
    else:
      print('listening')
      check= True
  
my_stream = db.child("BeTapKeChuyen/LastGhiAm").stream(stream_handler)