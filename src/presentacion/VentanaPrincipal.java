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

package presentacion;

import clases.CodigoMetodo;
import clases.Contacto;
import clases.EnvioPrivado;
import clases.Grupo;
import clases.Usuario;
import envio_recepcion.ComunicacionEntrante;
import envio_recepcion.EnvioArchivo;
import envio_recepcion.Observer;
import envio_recepcion.RecepcionArchivo;
import envio_recepcion.Subject;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adrian Ledo
 */
public class VentanaPrincipal extends javax.swing.JFrame implements Observer{

    private JDesktopPane escritorio;
    private VentanaContactos vContactos;
    private VentanaGrupos vGrupos;
    private DialogSeleccionContacto dlgConversacion;
    private DialogIniciarSesion dlgInicioSesion;
    private VentanaInformes vInformes;
    
    private File fileAplicacion;
    private String caminoAplicacion;
    private String caminoArchivosConfiguracionConEspacios;
    //private String url, puerto, usuario, nombreBD, clave;
    public static String rutaPDF;
    public String IP_SERVIDOR;
    
    private Socket socket;
    private ComunicacionEntrante comEntrante;
    private Thread thComEntrante;
    private OutputStream flujoSalida;
    private ObjectOutputStream objFlujoS;
    
    private Socket socketArchivos;
    private RecepcionArchivo recepcionArchivo;
    private Thread thRecepcionArchivo;
    private Thread thEnvioArch;
    private OutputStream flujoSalidaArchivos;
    private ObjectOutputStream objFlujoSalidaArchivos;
    
    private Usuario login;
    private DialogRecibirArchivo dlgRecepArch;
    
//    private DialogRecibirArchivo dra;
    
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        configurarDirectoriosProperties();
        leerProperties();
        int intentosConexion = 0;
        
        try {
            while(intentosConexion < 10)
            {
                try
                {
                    this.socket = new Socket(this.IP_SERVIDOR, 62006);
                    this.socketArchivos = new Socket(this.IP_SERVIDOR, 62005);
                    break;
                } catch(IOException e)
                {
                    System.out.println("Conectando con el servidor: "+(++intentosConexion));
                }
                Thread.sleep(1000);
            }
            if(intentosConexion >= 10)
            {
                JOptionPane.showMessageDialog(this, "No se pudo conectar con el servidor");
                this.cerrar();
                return;
            }
                
            this.comEntrante = new ComunicacionEntrante(this.socket);
            this.comEntrante.registerObserver(this);
            this.thComEntrante = new Thread(this.comEntrante);
            this.thComEntrante.start();
            
            this.recepcionArchivo = new RecepcionArchivo(this.socketArchivos);
            this.recepcionArchivo.registerObserver(this);
            this.thRecepcionArchivo = new Thread(this.recepcionArchivo);
            this.thRecepcionArchivo.start();
            
            this.flujoSalida = this.socket.getOutputStream();
            this.objFlujoS = new ObjectOutputStream(this.flujoSalida);
            
            this.flujoSalidaArchivos = this.socketArchivos.getOutputStream();
            this.objFlujoSalidaArchivos = new ObjectOutputStream(flujoSalidaArchivos);
            this.dlgInicioSesion = new DialogIniciarSesion(this, true);
            this.dlgInicioSesion.setVisible(true);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.escritorio = new JDesktopPane();
        this.setContentPane(this.escritorio);
        initComponents();
        this.setVisible(false);
        
//        CREAR CARPETA JavaHelpSearch
        java.net.URL helpURL = this.getClass().getResource("/ayudas/ayuda.hs");
        try {
            HelpSet helpset = new HelpSet(null, helpURL);
            HelpBroker broker = helpset.createHelpBroker();
            broker.enableHelpOnButton(this.mnuAyuda, "pagOpciones", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OutputStream getFlujoSalidaArchivos() {
        return flujoSalidaArchivos;
    }

    public ObjectOutputStream getObjFlujoSalidaArchivos() {
        return objFlujoSalidaArchivos;
    }

/*    public JDesktopPane getEscritorio() {
        return escritorio;
    }*/

    public Usuario getLogin() {
        return login;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuContactos = new javax.swing.JMenuItem();
        mnuGrupos = new javax.swing.JMenuItem();
        mnuConversacion = new javax.swing.JMenuItem();
        mnuInformes = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ConectApp - Adrián Ledo Castro");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jMenu1.setText("Opciones");

        mnuContactos.setText("Contactos");
        mnuContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuContactosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuContactos);

        mnuGrupos.setText("Grupos");
        mnuGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGruposActionPerformed(evt);
            }
        });
        jMenu1.add(mnuGrupos);

        mnuConversacion.setText("Conversacion");
        mnuConversacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuConversacionActionPerformed(evt);
            }
        });
        jMenu1.add(mnuConversacion);

        mnuInformes.setText("Informes");
        mnuInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInformesActionPerformed(evt);
            }
        });
        jMenu1.add(mnuInformes);

        mnuAyuda.setText("Ayuda");
        jMenu1.add(mnuAyuda);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
private void mnuContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuContactosActionPerformed
    if(!GestorVentanas.isContactosAbierta())
    {
        this.vContactos = new VentanaContactos(this);
        this.escritorio.add(this.vContactos);
        GestorVentanas.setContactosAbierta(true);
    //    this.vContactos.moveToFront();
    }
    try {
        this.vContactos.setSelected(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mnuContactosActionPerformed

private void mnuConversacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConversacionActionPerformed
    if(!GestorVentanas.isSelContactoAbierta())
    {
        this.dlgConversacion = new DialogSeleccionContacto(this, true);
        GestorVentanas.setSelContactoAbierta(true);
        this.dlgConversacion.setVisible(true);
    }
}//GEN-LAST:event_mnuConversacionActionPerformed

private void mnuInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInformesActionPerformed
    if(!GestorVentanas.isInformesAbierta())
    {
        this.vInformes = new VentanaInformes(this, caminoArchivosConfiguracionConEspacios);
        this.escritorio.add(this.vInformes);
        GestorVentanas.setInformesAbierta(true);
        
    }
    try
    {
        this.vInformes.setSelected(true);
    } catch (PropertyVetoException ex)
    {
        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mnuInformesActionPerformed

private void mnuGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGruposActionPerformed
    if(!GestorVentanas.isGruposAbierta())
    {
        this.vGrupos = new VentanaGrupos(this);
        this.escritorio.add(this.vGrupos);
        GestorVentanas.setGruposAbierta(true);
        
    }
    try {
        this.vGrupos.setSelected(true);
    } catch (PropertyVetoException ex) {
        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_mnuGruposActionPerformed

private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
/*    if(thRecibirMensajes.isAlive())
    {
        thRecibirMensajes.interrupt();
    }
    if(thRecibirArchivos.isAlive())
    {
        thRecibirArchivos.interrupt();
    }*/
}//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mnuAyuda;
    private javax.swing.JMenuItem mnuContactos;
    private javax.swing.JMenuItem mnuConversacion;
    private javax.swing.JMenuItem mnuGrupos;
    private javax.swing.JMenuItem mnuInformes;
    // End of variables declaration//GEN-END:variables
    
    void mostrarGrupos(ArrayList<Grupo> grupos)
    {
        if(GestorVentanas.isGruposAbierta())
        {
            this.vGrupos.actualizarMdlGrupos(grupos);
        }
    /*    if(GestorVentanas.isContactosAbierta())
        {
            this.vContactos.actualizarMdlGrupos(grupos);
        }*/
        if(GestorVentanas.isInformesAbierta())
        {
            this.vInformes.cargarGrupos(grupos);
        }
    }

    void mostrarContactos(ArrayList<Contacto> contactos) {
        if(GestorVentanas.isContactosAbierta())
        {
            this.vContactos.actualizarMdlContactos(contactos);
        }
        if(GestorVentanas.isSelContactoAbierta())
        {
            this.dlgConversacion.actualizarMdlContactos(contactos);
        }
        if(GestorVentanas.isGruposAbierta())
        {
            this.vGrupos.actualizarMdlContactosUsuario(contactos);
        }
        if(GestorVentanas.isInformesAbierta())
        {
            this.vInformes.cargarContactos(contactos);
        }
            
    }
    
    /**
     * 
     * @param alias
     * @param nombre
     * @return 
     */
    public VentanaConver addConversacion(String alias, String nombre)
    {
        VentanaConver vc;
        if((vc=this.recuperarVentanaConver(alias)) == null)
        {
            vc = new VentanaConver(this, alias, nombre);
        //    GestorVentanas.addVentanaConver(vc);
            this.escritorio.add(vc);
            
        }
        try {
            vc.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vc;
    }
    
    /**
     * 
     * @param aliasContacto
     * @return 
     */
    private VentanaConver recuperarVentanaConver(String aliasContacto) {
        VentanaConver vc;
        for(Object o : this.escritorio.getAllFrames())
        {
            if(o.getClass().equals(VentanaConver.class))
            {
                vc = (VentanaConver) o;
                if(vc.getAlias().equals(aliasContacto)
                        /*&& c.getIdGrupo() == contacto.getIdGrupo()*/)
                {
                    return vc;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof ComunicacionEntrante)
        {
            ComunicacionEntrante comE = (ComunicacionEntrante) subject;
            switch(comE.getCodigoMetodo())
            {
                case CodigoMetodo.REGISTRARSE:
                    if(comE.getResultado() == -1)
                    {
                        JOptionPane.showMessageDialog(this, "No se pudo registrar");
                        this.login = null;
                    }else
                    {
                        try {
                            objFlujoSalidaArchivos.writeObject(this.login);
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        this.setVisible(true);
                        this.dlgInicioSesion.dispose();
                    }
                    break;
                case CodigoMetodo.INICIAR_SESION:
                    if(comE.getResultado() != 1)
                    {
                        JOptionPane.showMessageDialog(this, "No se pudo iniciar sesión");
                        this.login = null;
                    }else
                    {
                        try {
                            objFlujoSalidaArchivos.writeObject(this.login);
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                        this.setVisible(true);
                        this.dlgInicioSesion.dispose();
                    }
                    break;
                case CodigoMetodo.INSERTAR_CONTACTO:
                    if(comE.getResultado() == -1)
                    {
                        JOptionPane.showMessageDialog(this, "El contacto no se pudo insertar");
                    }
                    else if(comE.getResultado() == -2)
                    {
                        JOptionPane.showMessageDialog(this, "El contacto ya existe");
                    }
                    else if(comE.getResultado() == -3)
                    {
                        JOptionPane.showMessageDialog(this, "Este es el usuario actual");
                    }
                    else if(comE.getResultado() == -4)
                    {
                        JOptionPane.showMessageDialog(this, "No existe ningún usuario con ese alias");
                    }
                    else if(comE.getResultado() == 0)
                    {
                        JOptionPane.showMessageDialog(this, "Contacto insertado");
                    //    this.contactos.mostrarContactos();
                    }
                    break;
                case CodigoMetodo.LISTAR_CONTACTOS_USUARIO:
                    /*if(!comE.getContactos().isEmpty())
                    {*/
                        try {
                            Thread.sleep(100); // Sino lanza un NullPointerException
                            this.mostrarContactos(comE.getContactos());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 //   }
                    break;
                case CodigoMetodo.ELIMINAR_CONTACTO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "El contacto no pudo ser eliminado");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Contacto eliminado");
                    }
                    break;
                case CodigoMetodo.MODIFICAR_CONTACTO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "El contacto no pudo ser modificado");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Contacto modificado");
                        this.actualizarContactos();
                    }
                    break;
                case CodigoMetodo.LISTAR_GRUPOS:
                    /*if(!comE.getGrupos().isEmpty())
                    {*/
                        try {
                            Thread.sleep(100);
                            this.mostrarGrupos(comE.getGrupos());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                //    }
                    break;
                case CodigoMetodo.INSERTAR_GRUPO:
                    try {
                        Thread.sleep(100);
                        switch(comE.getResultado())
                        {
                            case -2:
                                JOptionPane.showMessageDialog(this, "No existe ningún usuario con ese alias");
                                break;
                            case -1:
                                JOptionPane.showMessageDialog(this, "El grupo no pudo ser creado");
                                break;
                            case 0:
                                JOptionPane.showMessageDialog(this, "Grupo creado");
                                this.actualizarGrupos();
                                break;
                            default:
                                break;
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.LISTAR_CONTACTOS_GRUPO:
                    try {
                        Thread.sleep(100);
                        this.vGrupos.actualizarMdlContactosGrupo(comE.getContactos());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.ELIMINAR_GRUPO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "El grupo no se ha podido eliminar");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Grupo eliminado");
                        this.actualizarGrupos();
                        this.actualizarContactos();
                    }
                    break;
                case CodigoMetodo.MODIFICAR_GRUPO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "El grupo no se ha podido modificar");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Grupo modificado");
                        this.actualizarGrupos();
                    }
                    break;
                case CodigoMetodo.ENVIAR_MENSAJE_P:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "Error al enviar el mensaje");
                    }
                    break;
                case CodigoMetodo.RECIBIR_MENSAJE_P:
                    try {
                        Thread.sleep(100);EnvioPrivado envP = comE.getEnvioPrivado();
                            // Comprobar si existe el contacto para poner su nombre en vez de null
                            VentanaConver vc = this.addConversacion(envP.getRemitente(), null);
                            vc.recibirMensajeP(envP);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    /*if((vc=this.recuperarVentanaConver(envP.getRemitente())) != null)
                    {
                        vc.recibirMensajeP(envP);
                    } else
                    {
                        this.addConversacion(envP.getRemitente(), null);
                        
                    }*/
                    break;
                case CodigoMetodo.INSERTAR_GRUPO_CONTACTO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "No se ha podido añadir el contacto al grupo");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Contacto añadido al grupo");
                        this.mostrarContactosGrupo(comE.getGrupo());
                    //    this.mostrarGruposContacto(comE.getContacto());
                    }
                    break;
                case CodigoMetodo.ELIMINAR_GRUPO_CONTACTO:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "No se ha podido eliminar el contacto del grupo");
                    }else
                    {
                        JOptionPane.showMessageDialog(this, "Contacto expulsado del grupo");
                        this.mostrarContactosGrupo(comE.getGrupo());
                    //    this.mostrarGruposContacto(comE.getContacto());
                    }
                    break;
                case CodigoMetodo.LISTAR_GRUPOS_CONTACTO:
                    try {
                        Thread.sleep(100);
                        this.vContactos.actualizarMdlGrupos(comE.getGrupos());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.INFORME_CONTACTOS:
                    try {
                        Thread.sleep(100);
                        this.vInformes.genInfContactos(comE.getContactos());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.INFORME_GRUPOS:
                    try {
                        Thread.sleep(100);
                        this.vInformes.genInfGrupos(comE.getGrupos());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.INFORME_CONTACTOS_GRUPO:
                    try {
                        Thread.sleep(100);
                        this.vInformes.genInfContactosGrupo(comE.getContactos());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case CodigoMetodo.INFORME_MENSAJES_CONTACTO:
                    try {
                        Thread.sleep(100);
                        this.vInformes.genInfMensajesContacto(comE.getEnviosPrivados());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }
        }
        else if(subject instanceof RecepcionArchivo)
        {
            RecepcionArchivo recArch = (RecepcionArchivo) subject;
            /*VentanaConver vConver = this.addConversacion(recArch.getAliasContacto(), null);
            vConver.actualizaBarraProgreso(recArch.getNumIter());*/
            
            if (recArch.isAceptacion()) {
                if(!recArch.isIniciado())
                {
                    this.dlgRecepArch.iniciaBarraProgreso(recArch.getNumIter());
                }
                else
                {
                    this.dlgRecepArch.actualizaBarraProgreso(recArch.getNumIter());
                }

                if(recArch.isRecibido())
                {
                    JOptionPane.showMessageDialog(this, "Archivo recibido");
                    this.dlgRecepArch.dispose();
                }
            } else {
                int respuesta;
                respuesta = JOptionPane.showConfirmDialog(this, "¿Guardar archivo?", recArch.getAliasContacto()+" intenta enviarle "+recArch.getNombreArchivo(),
                JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    recArch.setAceptacion(true);
                    //Mostrar diálogo de selección de carpeta
                    recArch.setDirectorio(this.obtenerDirectorio());
                    recArch.despertar();
                    this.dlgRecepArch = new DialogRecibirArchivo(this, false);
                    this.dlgRecepArch.setVisible(true);
                }
//                else {
//                    t.interrupt();
//                }
            }
        } else if(subject instanceof EnvioArchivo)
        {
            EnvioArchivo envArch = (EnvioArchivo) subject;
            VentanaConver vConver = this.recuperarVentanaConver(envArch.getEnvPriv().getDestinatario());
            if(!envArch.isIniciado())
            {
                vConver.iniciaBarraProgreso(envArch.getNumIter());
            } else
            {
                vConver.actualizaBarraProgreso(envArch.getNumIter());
            }
        }
    }
    
    public String obtenerDirectorio()
    {
        String directorio;
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File f = chooser.getSelectedFile();
            directorio=f.getPath();
        }else
            directorio="";
        return directorio;
    }

    private void configurarDirectoriosProperties() {
        this.fileAplicacion = new File(VentanaPrincipal.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        this.caminoAplicacion = this.fileAplicacion.getAbsolutePath();
        this.caminoArchivosConfiguracionConEspacios = this.caminoAplicacion.replaceAll("%20", " ").replaceAll("\\\\", "/");
        this.caminoArchivosConfiguracionConEspacios = this.caminoArchivosConfiguracionConEspacios.concat("/../../build/classes");
    }

    private void leerProperties() {
        Properties propiedades = new Properties();
        try {
            String fichConf = this.caminoArchivosConfiguracionConEspacios.concat("/configuracion/config.properties");
            propiedades.load(new FileInputStream(fichConf));
        //    this.url = propiedades.getProperty("url");
        //    this.puerto = propiedades.getProperty("puerto");
        //    this.usuario = propiedades.getProperty("usuario");
        //    this.nombreBD = propiedades.getProperty("nombreBD");
        //    this.clave = propiedades.getProperty("clave");
            rutaPDF = propiedades.getProperty("RUTA_PDF");
            this.IP_SERVIDOR = propiedades.getProperty("IP_SERVIDOR");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void iniciarSesion(Usuario usuario) throws IOException {
        objFlujoS.writeObject(CodigoMetodo.INICIAR_SESION);
        System.out.println("DialogIniciarSesion: Código de método insertado en flujo");
        objFlujoS.writeObject(usuario);
        this.login = usuario;
        System.out.println("DialogIniciarSesion: Usuario insertado en flujo");
    /*    String IP = new String("192.168.21.17");
        String IP2 = this.socket.getInetAddress().getHostAddress();
        String IP3 = this.socket.getLocalSocketAddress().toString();
        String IP4 = this.socket.getLocalAddress().getHostAddress();
        objFlujoS.writeObject(IP);
        System.out.println("DialogIniciarSesion: IP insertada en flujo");*/
    }

    void registrarse(Usuario usuario) throws IOException {
        objFlujoS.writeObject(CodigoMetodo.REGISTRARSE);
        System.out.println("DialogIniciarSesion: Código de método insertado en flujo");
        objFlujoS.writeObject(usuario);
        this.login = usuario;
        System.out.println("DialogIniciarSesion: Usuario insertado en flujo");
        //flujo.writeUTF(socketCliente.getLocalSocketAddress().toString());
    /*    String IP = new String("192.168.21.17");
        objFlujoS.writeObject(IP);
        System.out.println("DialogIniciarSesion: IP insertada en flujo");*/
    }

    final void cerrar() {
        System.exit(0);
    }

    void guardarContacto(Contacto c) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INSERTAR_CONTACTO);
            c.setCreador(this.login.getAlias());
            this.objFlujoS.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(VentanaContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void actualizarContactos() {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.LISTAR_CONTACTOS_USUARIO);
            this.objFlujoS.writeObject(this.login.getAlias());
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarContacto(Contacto contacto) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.ELIMINAR_CONTACTO);
            this.objFlujoS.writeObject(contacto);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void modificarContacto(Contacto c) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.MODIFICAR_CONTACTO);
            c.setCreador(this.login.getAlias());
            this.objFlujoS.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void actualizarGrupos() {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.LISTAR_GRUPOS);
            this.objFlujoS.writeObject(this.login.getAlias());
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void guardarGrupo(Grupo grupo) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INSERTAR_GRUPO);
            grupo.setAdmin(this.login.getAlias());
            this.objFlujoS.writeObject(grupo);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarContactosGrupo(Grupo g) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.LISTAR_CONTACTOS_GRUPO);
            this.objFlujoS.writeObject(g);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void eliminarGrupo(Grupo g) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.ELIMINAR_GRUPO);
            this.objFlujoS.writeObject(g);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void modificarGrupo(Grupo grupo, String nuevoNombre) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.MODIFICAR_GRUPO);
            this.objFlujoS.writeObject(grupo);
            this.objFlujoS.writeObject(nuevoNombre);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void enviarMensajeP(String contenido, String destinatario) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.ENVIAR_MENSAJE_P);
            
        /*LocalDateTime ldt = LocalDateTime.now();
            java.sql.Timestamp f = java.sql.Timestamp.valueOf(ldt);f.getTime()
                    
            java.sql.Date fecha = new java.sql.Date(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));*/
            this.objFlujoS.writeObject(new EnvioPrivado(this.login.getAlias(), destinatario, this.getFechaHora(), contenido, EnvioPrivado.Enum_tipo.MENSAJE));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void insertarGrupoContacto(Grupo grupo, Contacto contacto) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INSERTAR_GRUPO_CONTACTO);
            this.objFlujoS.writeObject(grupo);
            this.objFlujoS.writeObject(contacto);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void expulsarContactoDeGrupo(Grupo g, Contacto c) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.ELIMINAR_GRUPO_CONTACTO);
            this.objFlujoS.writeObject(g);
            this.objFlujoS.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarGruposContacto(Contacto c) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.LISTAR_GRUPOS_CONTACTO);
            this.objFlujoS.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void enviarArchivo(String rutaFichero, String aliasContacto) {
        EnvioPrivado envPriv = new EnvioPrivado(this.login.getAlias(), aliasContacto, this.getFechaHora(), "", EnvioPrivado.Enum_tipo.ARCHIVO);
        EnvioArchivo envArch = new EnvioArchivo(this, rutaFichero, envPriv);
        envArch.registerObserver(this);
        thEnvioArch = new Thread(envArch);
        thEnvioArch.start();
    }
    
    private Timestamp getFechaHora()
    {
        return new Timestamp(new java.util.Date().getTime());
    }

    void getInformeContactos() {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INFORME_CONTACTOS);
            this.objFlujoS.writeObject(this.login.getAlias());
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void getInformeGrupos() {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INFORME_GRUPOS);
            this.objFlujoS.writeObject(this.login.getAlias());
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void getInformeContactosGrupo(Grupo grupo) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INFORME_CONTACTOS_GRUPO);
            this.objFlujoS.writeObject(grupo);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void getInformeMensajesContacto(Contacto contacto) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INFORME_MENSAJES_CONTACTO);
            this.objFlujoS.writeObject(contacto);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
