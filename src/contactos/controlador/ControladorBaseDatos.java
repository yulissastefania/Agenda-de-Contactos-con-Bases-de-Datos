/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactos.controlador;

import contactos.modelo.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yulissa T
 */
public class ControladorBaseDatos {

    private final String URL = "jdbc:derby://localhost:1527/Contactos";
    private final String USUARIO = "administrador";
    private final String CLAVE = "admin";
    private Connection conexion;
    private PreparedStatement seleccionarPersonas;
    private PreparedStatement seleccionarPersonasPorApellido;
    private PreparedStatement insertarNuevaPersona;

    public ControladorBaseDatos() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //System.out.println("Driver JAVA DB cargado !!!");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: No se encuentra el Driver" + ex);
            Logger.getLogger(ControladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("Conexion establecida a la base de datos");
        } catch (SQLException ex) {
            System.out.println("ERROR: No se pudo establecer conexion a la base de datos" + ex);
            Logger.getLogger(ControladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Persona> getPersonas() {
        List<Persona> listaPersona = new ArrayList<>();
        try {
            seleccionarPersonas = conexion.prepareStatement("Select * from PERSONA");
            ResultSet resultado = seleccionarPersonas.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String nombre = resultado.getString("NOMBRES");
                String apellido = resultado.getString("APELLIDO");
                String email = resultado.getString("EMAIL");
                String telefono = resultado.getString("TELEFONO");
                listaPersona.add(new Persona(id, nombre, apellido, email, telefono));
            }
            resultado.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
            Logger.getLogger(ControladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPersona;
    }

    public List<Persona> getPersonasPorApellidos(String apellidob) {
        List<Persona> listaPersona = new ArrayList<>();
        try {
            seleccionarPersonasPorApellido = conexion.prepareStatement("Select * from PERSONA where Apellido = '"+apellidob+"'"); 
            ResultSet resultado = seleccionarPersonasPorApellido.executeQuery();

            while (resultado.next()) {
                int id = resultado.getInt("ID");
                String nombre = resultado.getString("NOMBRES");
                String apellido = resultado.getString("APELLIDO");
                String email = resultado.getString("EMAIL");
                String telefono = resultado.getString("TELEFONO");
                Persona p = new Persona(id, nombre, apellido, email, telefono);
                System.out.println(p);
                listaPersona.add(p);
            }
            resultado.close();
        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta" + ex);
        }
        return listaPersona;
    }

    public int agregarPersona(String nombres, String apellido, String email, String telefono) {
        try {
            insertarNuevaPersona = conexion.prepareStatement("insert into PERSONA values("
                    + (this.getPersonas().size() + 1) + ", '"
                    + nombres + "', '"
                    + apellido + "', '"
                    + email + "', '"
                    + telefono + "')");
            return insertarNuevaPersona.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
