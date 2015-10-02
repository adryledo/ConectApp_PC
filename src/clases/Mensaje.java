/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Date;

/**
 *
 * @author ADRIANLC
 */
public class Mensaje
{
    private String aliasUsuario, aliasContacto, contenido;
    private Date fecha;

    public Mensaje(String aliasUsuario, String aliasContacto, Date fecha, String contenido)
    {
        this.aliasUsuario = aliasUsuario;
        this.aliasContacto = aliasContacto;
        this.fecha = fecha;
        this.contenido = contenido;
    }

    public String getAliasContacto() {
        return aliasContacto;
    }

    public String getAliasUsuario() {
        return aliasUsuario;
    }

    public String getContenido() {
        return contenido;
    }

    public Date getFecha() {
        return fecha;
    }
}
