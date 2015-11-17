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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentacion.VentanaPrincipal;

/**
 *
 * @author Adrian Ledo
 */

public class EnvioArchivo extends Subject implements Runnable {

    private ObjectOutputStream flujoObjetos;
    private DataOutputStream flujoDatos;
    private long tam;
    private String nombreFich;
    private boolean iniciado;
    private boolean enviado;
    private int numIter;
    private final EnvioPrivado envPriv;
    private final VentanaPrincipal principal;


    public EnvioArchivo(VentanaPrincipal principal, String rutaFichero, EnvioPrivado envPriv) {
        this.principal = principal;
        this.nombreFich = rutaFichero;
        this.envPriv = envPriv;
        this.iniciado = false;
        this.enviado = false;
    }

    public EnvioPrivado getEnvPriv() {
        return envPriv;
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
        try {
            this.flujoObjetos = this.principal.getObjFlujoSalidaArchivos();
            this.flujoDatos = new DataOutputStream(this.principal.getFlujoSalidaArchivos());
            
            encriptar();
            
            File fichEnviar = new File(this.nombreFich);
            
            String soloNombre = this.nombreFich.substring(this.nombreFich.lastIndexOf('\\') + 1);
            this.envPriv.setContenido(soloNombre);
            this.flujoObjetos.writeObject(this.envPriv);
            this.tam = fichEnviar.length();
            this.flujoDatos.writeLong(this.tam);
            envioFich();
            System.out.println("Fichero enviado!!!");
            fichEnviar.delete();
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
        FileInputStream ficheroOrigen = null;
        try {
            ficheroOrigen = new FileInputStream(this.nombreFich);
            this.numIter = (int) this.tam / tamBuf;
            if (this.tam % tamBuf != 0) {
                this.numIter++;
            }
            this.iniciado = false;
            notifyObservers();
//---- NOTIFICAR A LA VENTANA EL VALOR DE numIter PARA INICIAR LA BARRA DE PROGRESO ---
            try {
                while (this.numIter > 0) {
                    numBytesLeidos = ficheroOrigen.read(buffer);
                    flujoDatos.write(buffer, 0, numBytesLeidos);
                    this.numIter--;
                    this.iniciado = true;
                    notifyObservers();
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
        } finally
        {
            try {
                ficheroOrigen.close();
            } catch (IOException ex) {
                Logger.getLogger(EnvioArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
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
