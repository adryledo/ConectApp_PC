/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package envio_recepcion;

import clases.CodigoMetodo;
import clases.Contacto;
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
 * @author infdaid2
 */
public class ComunicacionEntrante extends Subject implements Runnable
{
    private int codigoMetodo;
    private int resultado;
    private ArrayList<Contacto> contactos;
    private ArrayList<Grupo> grupos;
    private final Socket socket;

    public ComunicacionEntrante(Socket socket) {
        this.socket = socket;
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
        //    this.resultado = objFlujoE.readInt();
            while(this.socket.isConnected())
            {
                this.codigoMetodo = (Integer) objFlujoE.readObject();
                System.out.println("ComunicacionEntrante: Codigo leído de flujo");
                switch(this.codigoMetodo)
                {
                    case CodigoMetodo.REGISTRARSE:
                    case CodigoMetodo.INICIAR_SESION:
                    case CodigoMetodo.INSERTAR_CONTACTO:
                        this.resultado = (int) objFlujoE.readObject();
                        System.out.println("ComunicacionEntrante: Resultado leído de flujo");
                        this.notifyObservers();
                        break;
                    case CodigoMetodo.LISTAR_CONTACTOS:
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
        }/* finally
        {
            try {
                objFlujoE.close();
                flujoEntrada.close();
            } catch (IOException ex) {
                Logger.getLogger(ComunicacionEntrante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
    
}
