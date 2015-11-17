/*
 * Copyright (C) 2015 Adrián Ledo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package envio_recepcion;

import clases.CodigoMetodo;
import clases.Contacto;
import clases.EnvioPrivado;
import clases.Grupo;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adri&aacute;n Ledo
 */
public class ComunicacionEntrante extends Subject implements Runnable
{
    private int codigoMetodo;
    private int resultado;
    private ArrayList<Contacto> contactos;
    private ArrayList<Grupo> grupos;
    private final Socket socket;
    private EnvioPrivado envioPrivado;
    private Grupo grupo;
    private Contacto contacto;
    private ArrayList<EnvioPrivado> enviosPrivados;

    public ComunicacionEntrante(Socket socket) {
        this.socket = socket;
    }

    public ArrayList<EnvioPrivado> getEnviosPrivados() {
        return enviosPrivados;
    }

    public Contacto getContacto() {
        return contacto;
    }
    
    public Grupo getGrupo() {
        return grupo;
    }

    public EnvioPrivado getEnvioPrivado() {
        return envioPrivado;
    }

    public int getCodigoMetodo() {
        return codigoMetodo;
    }

    public int getResultado() {
        return resultado;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public ArrayList<Grupo> getGrupos() {
        return grupos;
    }
    
    @Override
    public void run() {
        InputStream flujoEntrada = null;
        ObjectInputStream objFlujoE = null;
        try {
            flujoEntrada = this.socket.getInputStream();
            objFlujoE = new ObjectInputStream(flujoEntrada);
            while(this.socket.isConnected())
            {
                this.codigoMetodo = (Integer) objFlujoE.readObject();
                switch(this.codigoMetodo)
                {
                    case CodigoMetodo.REGISTRARSE:
                    case CodigoMetodo.INICIAR_SESION:
                    case CodigoMetodo.INSERTAR_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.LISTAR_CONTACTOS_USUARIO:
                        this.contactos = (ArrayList<Contacto>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.ELIMINAR_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.MODIFICAR_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.LISTAR_GRUPOS:
                        this.grupos = (ArrayList<Grupo>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INSERTAR_GRUPO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.LISTAR_CONTACTOS_GRUPO:
                        this.contactos = (ArrayList<Contacto>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.ELIMINAR_GRUPO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.MODIFICAR_GRUPO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.ENVIAR_MENSAJE_P:
                        this.resultado = (int) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.RECIBIR_MENSAJE_P:
                        this.envioPrivado = (EnvioPrivado) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INSERTAR_GRUPO_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.grupo = (Grupo) objFlujoE.readObject();
                        this.contacto = (Contacto) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.ELIMINAR_GRUPO_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        this.grupo = (Grupo) objFlujoE.readObject();
                        this.contacto = (Contacto) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.LISTAR_GRUPOS_CONTACTO:
                        this.grupos = (ArrayList<Grupo>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INFORME_CONTACTOS:
                        this.contactos = (ArrayList<Contacto>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INFORME_GRUPOS:
                        this.grupos = (ArrayList<Grupo>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INFORME_CONTACTOS_GRUPO:
                        this.contactos = (ArrayList<Contacto>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.INFORME_MENSAJES_CONTACTO:
                        this.enviosPrivados = (ArrayList<EnvioPrivado>) objFlujoE.readObject();
                        this.notifyObservers();
                        break;
                    default:
                        break;
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(ComunicacionEntrante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex){
            JOptionPane.showMessageDialog(null, "Se ha perdido la conexión con el servidor");
            System.exit(-1);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ComunicacionEntrante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
