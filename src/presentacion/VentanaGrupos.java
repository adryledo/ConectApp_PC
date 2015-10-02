package presentacion;

import clases.Contacto;
import clases.Grupo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adry
 */
public class VentanaGrupos extends javax.swing.JInternalFrame
{
    private VentanaPrincipal principal;
    private DefaultListModel<Grupo> mdlGrupos;
    private DefaultListModel<Contacto> mdlContactos;
    
    VentanaGrupos(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
        this.mdlGrupos = new DefaultListModel<>();
        this.mdlContactos = new DefaultListModel<>();
        this.lstGrupos.setModel(this.mdlGrupos);
        this.lstContactos.setModel(mdlContactos);
        this.principal.actualizarGrupos();
        this.limpiarCampos();
        
        java.net.URL helpURL = this.getClass().getResource("/ayudas/ayuda.hs");
        try {
            HelpSet helpset = new HelpSet(null, helpURL);
            HelpBroker broker = helpset.createHelpBroker();
            broker.enableHelpKey(this, "pagGrupos", helpset);
        } catch (HelpSetException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstGrupos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstContactos = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        setTitle("Grupos");
        setPreferredSize(new java.awt.Dimension(300, 300));
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

        jLabel1.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        txtNombre.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtNombre, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(btnGuardar, gridBagConstraints);

        lstGrupos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstGruposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstGrupos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        lstContactos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstContactos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jLabel2.setText("Grupos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Contactos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(btnEliminar, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
    GestorVentanas.setGruposAbierta(false);
}//GEN-LAST:event_formInternalFrameClosed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(this.txtNombre.getText().isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Introduzca un nombre para el grupo");
        return;
    }
    
    if(this.lstGrupos.isSelectionEmpty())
    {
        this.principal.guardarGrupo(new Grupo(this.txtNombre.getText()));
    }
    else
    {
        //this.principal.modificarGrupo(new Grupo(this.txtNombre.getText()));
    }
    /*Grupo g = new Grupo(this.txtNombre.getText());
    EjecutarMetodoServ insGrupo = new EjecutarMetodoServ(CodigoMetodo.INSERTAR_GRUPO, g);
    Thread t = new Thread(insGrupo);
    t.start();*/
    this.principal.actualizarGrupos();
    this.limpiarCampos();
}//GEN-LAST:event_btnGuardarActionPerformed

private void lstGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstGruposMouseClicked
    mostrarContactos();
}//GEN-LAST:event_lstGruposMouseClicked

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if(this.lstGrupos.isSelectionEmpty())
    {
        JOptionPane.showMessageDialog(this, "Seleccione un grupo");
        return;
    }
    
    /*Grupo g = this.mdlGrupos.getElementAt(this.lstGrupos.getSelectedIndex());
    EjecutarMetodoServ elimGrupo = new EjecutarMetodoServ(CodigoMetodo.ELIMINAR_GRUPO, g);
    Thread t = new Thread(elimGrupo);
    t.start();*/
    this.principal.actualizarGrupos();
    this.limpiarCampos();
}//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstContactos;
    private javax.swing.JList lstGrupos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    private void mostrarGrupos()
    {
        this.mdlGrupos.clear();
        /*EjecutarMetodoServ listarGrupos = new EjecutarMetodoServ(CodigoMetodo.LISTAR_GRUPOS);
        Thread t = new Thread(listarGrupos);
        t.start();*/
    }

    private void limpiarCampos()
    {
        this.txtNombre.setText(null);
        this.lstGrupos.clearSelection();
        this.lstContactos.clearSelection();
    }
    
    public void mostrarContactos()
    {
        if(this.lstGrupos.isSelectionEmpty())
        {
            return;
        }
        
        int idGrupo = this.mdlGrupos.getElementAt(this.lstGrupos.getSelectedIndex()).getId();
        this.mdlContactos.clear();
        /*EjecutarMetodoServ listarContGrupo = new EjecutarMetodoServ(CodigoMetodo.LISTAR_CONTACTOS_GRUPO, idGrupo);
        Thread t = new Thread(listarContGrupo);
        t.start();*/
    }

    protected void actualizarMdlGrupos(ArrayList grupos)
    {
        this.mdlGrupos.clear();
        grupos.stream().forEach((g) -> {
            this.mdlGrupos.addElement((Grupo)g);
        });
        this.lstGrupos.repaint();
    }
    /*@Override
    public void update(Subject subject) {
        if(subject instanceof RecibirMetodoServ)
        {
            RecibirMetodoServ metodo = (RecibirMetodoServ) subject;
            int codMetodo = metodo.getCodigo();
            switch(codMetodo)
            {
                case CodigoMetodo.INSERTAR_GRUPO:
                    switch(metodo.getResultado())
                    {
                        case 0:
                            JOptionPane.showMessageDialog(this, "Grupo insertado");
                            mostrarGrupos();
                            break;
                        case -1:
                            JOptionPane.showMessageDialog(this, "Fallo al insertar el grupo");
                            break;
                    }
                break;
                case CodigoMetodo.ELIMINAR_GRUPO:
                    switch(metodo.getResultado())
                    {
                        case 0:
                            JOptionPane.showMessageDialog(this, "Grupo eliminado");
                            mostrarGrupos();
                            break;
                        case -1:
                            JOptionPane.showMessageDialog(this, "No se pudo eliminar el grupo");
                            break;
                    }
                break;
                case CodigoMetodo.LISTAR_GRUPOS:
                    ArrayList<Object> grupos = metodo.getLista();
                    for(Object grupo : grupos)
                    {
                        this.mdlGrupos.addElement((Grupo)grupo);
                    }
                    this.lstGrupos.repaint();
                    limpiarCampos();
                    this.principal.mostrarGrupos();
                break;
                case CodigoMetodo.LISTAR_CONTACTOS_GRUPO:
                    ArrayList<Object> contactos = metodo.getLista();
                    for(Object c : contactos)
                    {
                        this.mdlContactos.addElement((Contacto)c);
                    }
                    this.lstContactos.repaint();
                break;
            }
        }
    }*/
}