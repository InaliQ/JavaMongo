
package conexion_mongo;

/** CONEXION MONGO CON JAVA
 * Eduardo Ulises Hernandez Medina
 * DSM 501 
 * @author inaliq
 */
public class Conexion_mongo {


    public static void main(String[] args) {
            Connection connection = new Connection();
            
            System.out.println("Segundo registro");
            connection.show();
            connection.insert("Pedro daniel");
            System.out.println("Uno mas");
             connection.insert("Jose Angel");
            connection.show();
            connection.update("Pedro daniel", "Pedro daniel Hernandez Medina");
            connection.delete("Jose Angel");
    }
    
}
