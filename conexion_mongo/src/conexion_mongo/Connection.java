
package conexion_mongo;

/**
 *
 * @author inaliq
 */
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    DB BaseDatos;
    DBCollection coleccion;
    BasicDBObject document = new BasicDBObject();
    
    public Connection(){
    try{
        Mongo mongo = new Mongo("Localhost",27017);
        BaseDatos = mongo.getDB("inalibd");
        coleccion = BaseDatos.getCollection("alumnos");
        System.out.println("Coneccion exitosa");
    }catch(UnknownHostException ex){
        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,null,ex);
    }
    }
    
    //Se crea el CRUD
    
    //insert 
    public boolean insert(String name){
    document.put("name", name);
    coleccion.insert(document);
    return true;
    }
    
    //Show
    public void show(){
        DBCursor cursor = coleccion.find();
        while (cursor.hasNext()) {            
            System.out.println(cursor.next());
        }
    }
    
    //Update
    public boolean update(String vieja, String nueva){
    document.put("name", vieja);
    BasicDBObject bObject = new BasicDBObject();
    bObject.put("name",nueva);
    coleccion.findAndModify(document, bObject);
    return true;
    }
    
    //Delete
    public boolean delete(String name){
    document.put("name",name);
    coleccion.remove(document);
    return true;
    }
}
