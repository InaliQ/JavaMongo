from pymongo import MongoClient
import certifi

MONGO_URI = 'mongodb+srv://InaliQ:1234@cluster0.mwap0el.mongodb.net/?retryWrites=true&w=majority'
ca = certifi.where()

def dbConnection():
    try:
        client = MongoClient(MONGO_URI, tlsCAFile=ca)
        db = client["DB_productos"]
    except ConnectionError:
        print('Error de conexión con la bdd')
    return db
