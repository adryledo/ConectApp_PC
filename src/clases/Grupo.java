/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author ADRIANLC
 */
public class Grupo
{
    private int id;
    private String nombre;

    public Grupo(String nombre)
    {
        this.nombre = nombre;
    }
    
    public Grupo(int id, String nombre)
    {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
}
