import urllib.request, urllib.parse, urllib.error 
import pyrebase
import os

config = {
  "apiKey": "uTP6tlP930oA1s9zuwGIZvrz1ef8ZjVLegROgNN0",
  "authDomain": "smarthome-5d11a.firebaseapp.com",
  "databaseURL": "https://smarthome-5d11a.firebaseio.com",
  "storageBucket": "smarthome-5d11a.appspot.com"
}
check=False
firebase = pyrebase.initialize_app(config)
db = firebase.database()

def stream_handler(message):
    # ghiAmList = db.child("BeTapKeChuyen/GhiAm").get()

    # # length=len(ghiAmList.each())
    # # print(str(length))
    # # lastItem=ghiAmList.each()[length-1].val()
    # # print(lastItem['fileName'])
    # for ghiAm in ghiAmList.each():
    #     print(ghiAm.val()["fileName"])
    global check
    if check:
        fileUrl =message["data"]["urlDownload"]
        urllib.request.urlretrieve(fileUrl,message["data"]["fileName"])
        print('done')
    else:
      print('listening')
      check= True
  

    
my_stream = db.child("BeTapKeChuyen/LastGhiAm").stream(stream_handler)