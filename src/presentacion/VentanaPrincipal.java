package presentacion;

import clases.CodigoMetodo;
import clases.Contacto;
import clases.Usuario;
import envio_recepcion.ComunicacionEntrante;
import envio_recepcion.Observer;
import envio_recepcion.Subject;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
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
 * @author Adry
 */
public class VentanaPrincipal extends javax.swing.JFrame implements Observer{

    private JDesktopPane escritorio;
    private VentanaContactos contactos;
    /*private VentanaGrupos grupos;
    private DialogSeleccionContacto conversacion;*/
    private DialogIniciarSesion dlgInicioSesion;
    
    private File fileAplicacion;
    private String caminoAplicacion;
    private String caminoArchivosConfiguracionConEspacios;
    private String url, puerto, usuario, nombreBD, clave;
    public static String rutaPDF;
    public static String IP_SERVIDOR;
    
    private Socket socket;
    private ComunicacionEntrante comEntrante;
    private Thread thComEntrante;
    private OutputStream flujoSalida;
    private ObjectOutputStream objFlujoS;
    private Usuario login;
    
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
            
            this.flujoSalida = this.socket.getOutputStream();
            this.objFlujoS = new ObjectOutputStream(this.flujoSalida);
        
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

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

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
        this.contactos = new VentanaContactos(this);
        this.escritorio.add(this.contactos);
        GestorVentanas.setContactosAbierta(true);
    }
}//GEN-LAST:event_mnuContactosActionPerformed

private void mnuConversacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuConversacionActionPerformed
    /*this.conversacion = new DialogSeleccionContacto(this, true);
    conversacion.setVisible(true);*/
}//GEN-LAST:event_mnuConversacionActionPerformed

private void mnuInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInformesActionPerformed
    /*if(!GestorVentanas.isInformesAbierta())
    {
        //this.informes = new VentanaInformes(caminoArchivosConfiguracionConEspacios);
//        this.escritorio.add(this.informes);
        GestorVentanas.setInformesAbierta(true);
    }*/
}//GEN-LAST:event_mnuInformesActionPerformed

private void mnuGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGruposActionPerformed
    /*if(!GestorVentanas.isGruposAbierta())
    {
        this.grupos = new VentanaGrupos(this);
        this.escritorio.add(this.grupos);
        GestorVentanas.setGruposAbierta(true);
    }*/
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
    
    /*public void mostrarGrupos()
    {
        if(GestorVentanas.isContactosAbierta())
        {
            this.contactos.mostrarGrupos();
        }
        if(GestorVentanas.isInformesAbierta())
        {
//            this.informes.cargarGrupos();
        }
    }*/

    /*void mostrarContactos() {
        if(GestorVentanas.isGruposAbierta())
        {
            this.grupos.mostrarContactos();
        }
        if(GestorVentanas.isInformesAbierta())
        {
//            this.informes.cargarContactos();
        }
            
    }*/
    
    public void addConversacion(Contacto c)
    {
    //    this.escritorio.add(new VentanaConver(this, c));
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
                        this.setVisible(true);
                        //dlgInicioSesion.setVisible(false);
                        this.dlgInicioSesion.dispose();
                    }
                    break;
                case CodigoMetodo.INICIAR_SESION:
                    if(comE.getResultado() != 0)
                    {
                        JOptionPane.showMessageDialog(this, "No se pudo iniciar sesión");
                        this.login = null;
                    }else
                    {
                        this.setVisible(true);
                        //dlgInicioSesion.setVisible(false);
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
                case CodigoMetodo.LISTAR_CONTACTOS:
                    if(!comE.getContactos().isEmpty())
                    {
                        try {
                            Thread.sleep(100); // Sino lanza un NullPointerException
                            this.contactos.actualizarMdlContactos(comE.getContactos());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                default:
                    break;
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
            this.url = propiedades.getProperty("url");
            this.puerto = propiedades.getProperty("puerto");
            this.usuario = propiedades.getProperty("usuario");
            this.nombreBD = propiedades.getProperty("nombreBD");
            this.clave = propiedades.getProperty("clave");
            this.rutaPDF = propiedades.getProperty("RUTA_PDF");
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
        System.out.println("DialogIniciarSesion: Objeto insertado en flujo");
        String IP = new String("192.168.21.17");
        String IP2 = this.socket.getInetAddress().getHostAddress();
        String IP3 = this.socket.getLocalSocketAddress().toString();
        String IP4 = this.socket.getLocalAddress().getHostAddress();
        objFlujoS.writeObject(IP);
        System.out.println("DialogIniciarSesion: IP insertada en flujo");
    }

    void registrarse(Usuario usuario) throws IOException {
        objFlujoS.writeObject(CodigoMetodo.REGISTRARSE);
        System.out.println("DialogIniciarSesion: Código de método insertado en flujo");
        objFlujoS.writeObject(usuario);
        this.login = usuario;
        System.out.println("DialogIniciarSesion: Objeto insertado en flujo");
        //flujo.writeUTF(socketCliente.getLocalSocketAddress().toString());
        String IP = new String("192.168.21.17");
        objFlujoS.writeObject(IP);
        System.out.println("DialogIniciarSesion: IP insertada en flujo");
    }

    final void cerrar() {
        System.exit(0);
    }

    void guardarContacto(Contacto c) {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.INSERTAR_CONTACTO);
            this.objFlujoS.writeObject(c);
        } catch (IOException ex) {
            Logger.getLogger(VentanaContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void actualizarContactos() {
        try {
            this.objFlujoS.writeObject(CodigoMetodo.LISTAR_CONTACTOS);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
