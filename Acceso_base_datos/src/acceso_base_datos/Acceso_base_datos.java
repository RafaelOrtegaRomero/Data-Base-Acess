/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package acceso_base_datos;

import java.util.Calendar;
import java.util.TimeZone;
import java.sql.*;
/**
 *
 * @author Rafae
 */
public class Acceso_base_datos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String BD="peliculas";
        String USUARIO="root";
        String PASS="";
        String HOST="localhost";
        
        //Registrar driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        //Iniciar la conexion
        Calendar now = Calendar.getInstance();
        TimeZone zonaHoraria=now.getTimeZone();
        Connection connection=(Connection)DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BD+"?user="+USUARIO+"&password="+PASS+"&userLegacyDateTimeCode=false&serverTimeZone="+zonaHoraria.getID());
        
        //Crear Tabla
         PreparedStatement ST=null;
         try{
         ST=connection.prepareStatement("CREATE TABLE peliculas(nombre varchar(50) NOT NULL,year INT(5),director varchar(50))");
         ST.execute();
         ST.close();
         }catch(SQLException sqle){
             System.out.println("no se puede");
         }
        
         //crear un insert
         String consulta="INSERT INTO peliculas(nombre,year,director) values ('Titanic',1998,'Lopez')";
         try{
             
            Statement ST2=null;
            ST2=connection.createStatement();
          
             int fila=ST2.executeUpdate(consulta);
             
         }catch(SQLException e){
            e.printStackTrace();
         }
  
         //Realizar un SELECT  "CONSULTA"
        consulta=" SELECT * FROM peliculas ORDER BY nombre";
        Statement stm= connection.createStatement();
        ResultSet rset= stm.executeQuery(consulta);
        
        //Recorrer
        while(rset.next()){
        String nombre = rset.getString("nombre");
        int year= rset.getInt("year");
            System.out.println(nombre + year);
            
            
        }
         //Cierra la conexion
            connection.close();
            
          
        //borrar una tabla comentar la parte de insercion si no furula
           consulta = "DELETE FROM peliculas WHERE year=1998";

            try {
                Statement SMT2 = connection.createStatement();
                int fila = SMT2.executeUpdate(consulta);

            } catch (SQLException sqle) {
                System.out.println("Error en la ejecucion ");
            }

        
    }
   
}
