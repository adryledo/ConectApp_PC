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

import clases.EnvioPrivado;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JFileChooser;

/**
 *
 * @author Adry
 */
public class VentanaConver extends javax.swing.JInternalFrame {

    private VentanaPrincipal principal;
    //private String IP;
//    private Contacto contacto;
    private String alias;
    private String nombre;
    

    VentanaConver(VentanaPrincipal principal, String alias, String nombre) {
        initComponents();
        this.principal = principal;
        //this.IP = c.getIp();
        this.setTitle(nombre==null ? alias : nombre);
        this.alias = alias;
        this.nombre = nombre;
    //    this.contacto = c;
        
        java.net.URL helpURL = this.getClass().getResource("/ayudas/ayuda.hs");
        try {
            HelpSet helpset = new HelpSet(null, helpURL);
            HelpBroker broker = helpset.createHelpBroker();
            broker.enableHelpKey(this, "pagConver", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        this.jFileChooser1 = new JFileChooser();
//        Hacer envio y recepcion archivos
//        ENVIAR IP DESDE DONDE SE ENVÍA PARA SABER LA VENTANA DONDE MOSTRAR EL MENSAJE
//        SINO SIEMPRE COGE LA IP (NO EL HOSTNAME)
    }

    public String getAlias() {
        return alias;
    }

    public String getNombre() {
        return nombre;
    }

    /*Contacto getContacto() {
        return contacto;
    }

    public String getIP() {
        return IP;
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConversacion = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtArchivo = new javax.swing.JTextField();
        btnEnviarArchivo = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setResizable(true);
        setTitle("Nombre de contacto");
        setPreferredSize(new java.awt.Dimension(400, 260));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Mensajes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        txtConversacion.setColumns(20);
        txtConversacion.setRows(5);
        jScrollPane1.setViewportView(txtConversacion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        txtMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMensajeKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtMensaje, gridBagConstraints);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(btnEnviar, gridBagConstraints);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 130));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel1.add(btnBuscar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        jPanel1.add(txtArchivo, gridBagConstraints);

        btnEnviarArchivo.setText("Enviar");
        btnEnviarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarArchivoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel1.add(btnEnviarArchivo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(jProgressBar1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(jPanel1, gridBagConstraints);

        jLabel2.setText("Enviar archivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
    this.principal.enviarMensajeP(this.txtMensaje.getText(), this.alias);
    this.mostrarMensaje(this.txtMensaje.getText());
    this.txtMensaje.setText(null);
}//GEN-LAST:event_btnEnviarActionPerformed

private void txtMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensajeKeyPressed
    if(evt.getKeyCode() == KeyEvent.VK_ENTER)
    {
        this.btnEnviar.doClick();
    }
}//GEN-LAST:event_txtMensajeKeyPressed

private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
    /*if(thClienteEnviar != null && thClienteEnviar.isAlive())
    {
        thClienteEnviar.interrupt();
    }
    if(thEnviarArchivo != null && thEnviarArchivo.isAlive())
    {
        thEnviarArchivo.interrupt();
    }*/
}//GEN-LAST:event_formInternalFrameClosed

private void btnEnviarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarArchivoActionPerformed
/*    EnviarArchivo enviarArchivo = new EnviarArchivo(c, this.txtArchivo.getText());
    jProgressBar1.setMaximum(0);
    thEnviarArchivo = new Thread(enviarArchivo);
    enviarArchivo.registerObserver(this);
    thEnviarArchivo.start();*/
}//GEN-LAST:event_btnEnviarArchivoActionPerformed

private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    JFileChooser jfcElegirArchivo = 
            new JFileChooser("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer");
        
//    java.io.File f=new java.io.File("");
//    java.io.File f2[]={f};
//    this.jFileChooser1.setSelectedFile(f);
//    this.jFileChooser1.setSelectedFiles(f2);
        
    int resultado = jfcElegirArchivo.showOpenDialog(this);
    switch(resultado){
        case javax.swing.JFileChooser.CANCEL_OPTION:
            break;
        case javax.swing.JFileChooser.APPROVE_OPTION: 
            File fichero = jfcElegirArchivo.getSelectedFile();
            this.txtArchivo.setText(fichero.getPath());
        break;
        case javax.swing.JFileChooser.ERROR_OPTION:
        break;
    }
}//GEN-LAST:event_btnBuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnEnviarArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtArchivo;
    private javax.swing.JTextArea txtConversacion;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables

/*    @Override
    public void update(Subject subject) {
        if(subject instanceof ClienteEnviar)
        {
            ClienteEnviar c = (ClienteEnviar) subject;
            if(!c.isEnviado())
            {
                JOptionPane.showMessageDialog(this, "No se pudo enviar el mensaje");
            }/* else
            {
                GestionMensajes.insertarMensaje(new Mensaje(
                        c.getContacto().getId(), c.getMensaje(), false));
            }*/
/*        }
        if(subject instanceof EnviarArchivo)
        {
            EnviarArchivo e = (EnviarArchivo) subject;
            if(!e.isIniciado())
            {
                iniciaBarraProgreso(e.getNumIter());
            } else
            {
                actualizaBarraProgreso(e.getNumIter());
            }
            
            if(e.isEnviado())
            {
                /*GestionArchivos.insertarArchivo(new Archivo(e.getContacto().getId(), 
                        e.getRutaFich(), false));*/
/*                JOptionPane.showMessageDialog(this, "Archivo enviado");
            }
        }
    }*/
    
    public void mostrarMensaje(String mensaje)
    {
        this.txtConversacion.append(mensaje+"\n");
    }
    
    public void iniciaBarraProgreso(int maximo){
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(maximo);
    }
    public void actualizaBarraProgreso(int cantidad){
        jProgressBar1.setValue(jProgressBar1.getMaximum()-cantidad);
    }
    
    public void recibirMensajeP(EnvioPrivado envP)
    {
        this.txtConversacion.append((this.nombre==null ? this.alias : this.nombre)+": "+envP.getContenido()+"\n");
    }
}
