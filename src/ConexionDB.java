import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexionDB {
    //Funcion para establecer la conexion con la base de datos
    public static Connection conectar(){
        String url = "jdbc:mysql://localhost:3306/poo";
        String usuarioDB = "root";
        String contrasenaBD = "123456";

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, usuarioDB, contrasenaBD);
            //JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");

        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + error.getMessage());
        }
        return conexion;
    }
}
