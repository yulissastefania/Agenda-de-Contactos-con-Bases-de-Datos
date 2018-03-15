/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactos.modelo;

/**
 *
 * @author Yulissa T
 */
public class Persona {
    
    private int id;
    private String nombres;
    private String apellido;
    private String email;
    private String telefono;

    public Persona(int id, String nombres, String apellido, String email, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public Persona() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Nombres=" + nombres + ", Apellido=" + apellido + ", Email=" + email + ", Telefono=" + telefono ;
    }

    
    
    
    
    
}
