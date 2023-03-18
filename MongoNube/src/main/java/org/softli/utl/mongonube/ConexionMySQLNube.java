package org.softli.utl.mongonube;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQLNube {
    Connection conn;

    /**
     * El metodo open sirve para conectar con la base de datos a usar, se crea la variable user con nuestro usuario en MySQL
     * y otra con nuestra contraseña, también la url para conectarnos a la base de datos
     * @return
     */
    public Connection open() {
        String user = "uyfdnmqfz51srpgx";
        String password = "svGGzLPepclnwQfEwKPo";
        String url = "jdbc:mysql://bdhba7vbjjsyun0wduv4-mysql.services.clever-cloud.com:3306/bdhba7vbjjsyun0wduv4?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * El metodo close cierra la conexion con nuetra base de datos
     */
    public void close() {
        if (conn != null) {
            try{
                conn.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Exception controlada");
            }
        }
    }


}
