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

import EncriptDecriptArchivo.TestBouncy;
import clases.EnvioPrivado;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADRIANLC
 */
public class RecepcionArchivo extends Subject implements Runnable {

    private ObjectInputStream flujoObjetos;
    private DataInputStream flujoDatos;
    private long tam;
    private String nombreArchivo;
    private boolean iniciado, recibido, aceptacion;
    private int numIter;
    private String directorio;
    private String IP;
    private String aliasContacto;
    private final Socket socketArchivo;
    private EnvioPrivado envPriv;

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public RecepcionArchivo(Socket socketArchivo) {
        this.iniciado = false;
        this.recibido = false;
        this.aceptacion = false;
        this.socketArchivo = socketArchivo;
    }

    public Socket getSocketArchivo() {
        return socketArchivo;
    }

    public String getAliasContacto() {
        return aliasContacto;
    }

    public String getIP() {
        return IP;
    }

    public int getNumIter() {
        return numIter;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public boolean isIniciado() {
        return iniciado;
    }

    public boolean isRecibido() {
        return recibido;
    }
    
    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }
    
    public synchronized void detener() {
        try {
            wait();
        } catch (InterruptedException e) {
        }
    }
    
    public synchronized void despertar() {
        notifyAll();
    }

    public void recepcionFichero() {
        int tamBuf = 256;
        byte buffer[] = new byte[tamBuf];
        int numBytesLeidos = 0;
        try {
//            String nomCom = System.getProperty("java.io.tmpdir").concat(nombreArchivo);
            String nomCom = this.directorio.concat("/"+this.nombreArchivo);
            FileOutputStream ficheroDestino = new FileOutputStream(nomCom);
            this.numIter = (int) this.tam / tamBuf;
            if (this.tam % tamBuf != 0) {
                this.numIter++;
            }
            
            notifyObservers();
            this.iniciado = true;
//---- NOTIFICAR A LA VENTANA EL VALOR DE numIter PARA INICIAR LA BARRA DE PROGRESO ---
            try {
                while (this.numIter > 0) {
                    numBytesLeidos = this.flujoDatos.read(buffer);
                    ficheroDestino.write(buffer, 0, numBytesLeidos);
                    this.numIter--;
                    
                    notifyObservers();
//---- NOTIFICAR A LA VENTANA EL VALOR 1 PARA ACTUALIZAR LA BARRA DE PROGRESO ---
                }
                ficheroDestino.close();
            } catch (IOException e) {
                Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, e);
            }
            
            desencriptar(nomCom);
            
            this.recibido = true;
            notifyObservers();
        } catch (FileNotFoundException e) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void run() {
        byte[] mensaje;
        InputStream flujoLectura;
    //    Socket comunicaCliente;
    //    ServerSocket socketServidor;
        int i;
        while(true)
        {
            this.iniciado = false;
            this.recibido = false;
            this.aceptacion = false;
            try {
            //    socketServidor = new ServerSocket(62003);
            //    comunicaCliente = socketServidor.accept();
                flujoLectura = this.socketArchivo.getInputStream();
                this.flujoObjetos = new ObjectInputStream(flujoLectura);
                this.flujoDatos = new DataInputStream(flujoLectura);
                this.tam = this.flujoObjetos.readLong();
                System.out.printf("Tamaño de fichero recibido: "+this.tam);
            /*    i = this.flujo.readInt();
                mensaje = new byte[i];
                this.flujo.readFully(mensaje);
                this.IP = this.flujo.readUTF();
                this.nombreArchivo = new String(mensaje);*/
                this.envPriv = (EnvioPrivado) this.flujoObjetos.readObject();
                System.out.println("EnvioPrivado recibido");
                this.nombreArchivo = this.envPriv.getContenido();
                this.aliasContacto = this.envPriv.getRemitente();
                notifyObservers();
                while (!this.isAceptacion()) {
                    this.detener();
                }
                recepcionFichero();
                System.out.println("Fichero recibido!!!");
            //    comunicaCliente.close();
            //    socketServidor.close();
            } catch (IOException | SecurityException | ClassNotFoundException ex) {
                Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void desencriptar(String nomCom) {
        try
        {
            File fichEntrada = new File(nomCom);
            FileInputStream imageInFile2 = new FileInputStream(fichEntrada);
            byte imageData2[] = new byte[(int) fichEntrada.length()];
            imageInFile2.read(imageData2);

            TestBouncy esource2 = new TestBouncy();
            String key2 = "12345678";
            byte[] cad2 = imageData2;
//            byte[] keyb2 = key2.getBytes();

            byte[] des2 = esource2.Decrypt(key2, cad2);
//            byte[] imageByteArray2 = des2;
            nomCom = nomCom.substring(0, nomCom.length()-3);
            FileOutputStream imageOutFile2 = new FileOutputStream(nomCom);
            imageOutFile2.write(des2);

            imageInFile2.close();
            imageOutFile2.close();
            fichEntrada.delete();

            System.out.println("File Successfully restored!");
        } catch (IOException ex) {
            Logger.getLogger(RecepcionArchivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}