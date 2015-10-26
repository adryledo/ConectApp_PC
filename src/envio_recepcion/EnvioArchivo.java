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
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import presentacion.VentanaPrincipal;

/**
 *
 * @author ADRIANLC
 */

public class EnvioArchivo extends Subject implements Runnable {

    private ObjectOutputStream flujoObjetos;
    private DataOutputStream flujoDatos;
    private long tam;
    private String nombreFich;
    private boolean iniciado;
    private boolean enviado;
    private int numIter;
    //private String IP;
//    private final Socket socketSalida;
//    private final String aliasContacto;
    private final EnvioPrivado envPriv;
    private final VentanaPrincipal principal;

/*    public Contacto getContacto() {
        return contacto;
    }*/

    public EnvioArchivo(VentanaPrincipal principal, String rutaFichero, EnvioPrivado envPriv) {
//        this.IP = c.getIp();
    //    this.socketSalida = socket;
    //    this.fichEnviar = fichero;
    //    this.nombreFich = fichero.getPath();
        this.principal = principal;
        this.nombreFich = rutaFichero;
        this.envPriv = envPriv;
    //    this.aliasContacto = envPriv.getDestinatario();
        this.iniciado = false;
        this.enviado = false;
    }

    public int getNumIter() {
        return numIter;
    }

    public boolean isIniciado() {
        return iniciado;
    }
    
    public boolean isEnviado() {
        return enviado;
    }

    public String getRutaFich() {
        return nombreFich;
    }

 
    @Override
    public void run() {
    //    OutputStream flujoSalida;
        try {
        //    Socket socketEnviar = new Socket(DialogIniciarSesion.IP_SERVIDOR, 62005);
        //    flujoSalida = this.socketSalida.getOutputStream();
            this.flujoObjetos = this.principal.getObjFlujoSalidaArchivos();
            this.flujoDatos = new DataOutputStream(this.principal.getFlujoSalidaArchivos());
            
            encriptar();
            
            File fichEnviar = new File(this.nombreFich);
            
            String soloNombre = this.nombreFich.substring(this.nombreFich.lastIndexOf('\\') + 1);
        /*    this.flujoDatos.writeInt(soloNombre.length());
            System.out.printf("Tamaño de nombre enviado: "+soloNombre.length());
            this.flujoDatos.writeBytes(soloNombre);
            System.out.println("Nombre enviado: "+soloNombre);*/
        //    this.flujo.writeUTF(InetAddress.getLocalHost().getHostAddress());
            this.envPriv.setContenido(soloNombre);
            this.flujoObjetos.writeObject(this.envPriv);
            System.out.println("EnvioPrivado enviado");
        /*    this.flujo.writeInt(this.aliasContacto.length());
            System.out.println("Tamaño de alias enviado: "+this.aliasContacto.length());
            this.flujo.writeBytes(this.aliasContacto);
            System.out.println("Alias enviado: "+this.aliasContacto);*/
            this.tam = fichEnviar.length();
            this.flujoDatos.writeLong(this.tam);
            System.out.println("Tamaño de fichero enviado: "+this.tam);
            envioFich();
            System.out.println("Fichero enviado!!!");
        //    fichEnviar.delete();
            //socketEnviar.close();
        } catch (UnknownHostException e) {
            System.out.println("Referencia a host no resuelta");
        } catch (IOException e) {
            System.out.println("Error en las comunicacines");
        } catch (SecurityException e) {
            System.out.println("Comunicación no permitida");
        }
    }
       
    void envioFich() {
        int tamBuf = 256;
        byte buffer[] = new byte[tamBuf];
        int numBytesLeidos = 0;
        FileInputStream ficheroOrigen;
        try {
            ficheroOrigen = new FileInputStream(this.nombreFich);
            this.numIter = (int) this.tam / tamBuf;
            if (this.tam % tamBuf != 0) {
                this.numIter++;
            }
            this.iniciado = false;
//            notifyObservers();
//---- NOTIFICAR A LA VENTANA EL VALOR DE numIter PARA INICIAR LA BARRA DE PROGRESO ---
            try {
                while (this.numIter > 0) {
                    numBytesLeidos = ficheroOrigen.read(buffer);
                    flujoDatos.write(buffer, 0, numBytesLeidos);
                    this.numIter--;
                    this.iniciado = true;
//                    notifyObservers();
//---- NOTIFICAR A LA VENTANA EL VALOR 1 PARA ACTUALIZAR LA BARRA DE PROGRESO ---
                }
            //    ficheroOrigen.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("-----***** ERROR RECIBIENDO ***----");
            }
            this.enviado = true;
            notifyObservers();
        } catch (FileNotFoundException e) {
        }
    }

    private void encriptar() throws FileNotFoundException, IOException {
        FileInputStream ficheroOrigen;
        File file = new File(this.nombreFich);
        ficheroOrigen = new FileInputStream(file);
        byte imageData[] = new byte[(int) file.length()];
        ficheroOrigen.read(imageData);
        TestBouncy esource = new TestBouncy();
        String key = "12345678";
        byte enc[] = esource.Encrypt(key, imageData);
        
        this.nombreFich = this.nombreFich+"Enc";
        try (FileOutputStream ficheroEncriptado = new FileOutputStream(this.nombreFich)) {
            ficheroEncriptado.write(enc);
        }
        ficheroOrigen.close();
    }
}
