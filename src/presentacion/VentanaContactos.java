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

import clases.Contacto;
import clases.Grupo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adry
 */
public class VentanaContactos extends javax.swing.JInternalFrame{

    private VentanaPrincipal principal;
    private DefaultComboBoxModel<Grupo> mdlGrupos;
    private DefaultListModel<Contacto> mdlContactos;
    private ArrayList<Grupo> arrayGruposContacto;
    
    VentanaContactos(VentanaPrincipal principal) {
        initComponents();
        this.principal = principal;
        this.arrayGruposContacto = new ArrayList<>();
        this.mdlGrupos = new DefaultComboBoxModel<>();
        this.cmbGrupo.setModel(mdlGrupos);
        this.lstGrupos.setModel(mdlGrupos);
        this.mdlContactos = new DefaultListModel<>();
        this.lstContactos.setModel(mdlContactos);
        this.principal.actualizarContactos();
        this.principal.actualizarGrupos();
        //this.limpiarCampos();
        
        java.net.URL helpURL = this.getClass().getResource("/ayudas/ayuda.hs");
        try {
            HelpSet helpset = new HelpSet(null, helpURL);
            HelpBroker broker = helpset.createHelpBroker();
            broker.enableHelpKey(this, "pagContactos", helpset);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cmbGrupo = new javax.swing.JComboBox();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstContactos = new javax.swing.JList();
        btnEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtAlias = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGrupos = new javax.swing.JList();

        setClosable(true);
        setResizable(true);
        setTitle("Contactos");
        setPreferredSize(new java.awt.Dimension(400, 300));
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel2, gridBagConstraints);

        jLabel3.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel3, gridBagConstraints);

        jLabel4.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel4, gridBagConstraints);

        jLabel5.setText("Grupo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel5, gridBagConstraints);

        txtNombre.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtNombre, gridBagConstraints);

        txtTelefono.setText("jTextField2");
        txtTelefono.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                return txtTelefono.getText().length() <= 15;
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtTelefono, gridBagConstraints);

        txtDireccion.setText("jTextField3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtDireccion, gridBagConstraints);

        txtEmail.setText("jTextField4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtEmail, gridBagConstraints);

        cmbGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(cmbGrupo, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(btnGuardar, gridBagConstraints);

        lstContactos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstContactos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstContactosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstContactos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        getContentPane().add(jScrollPane1, gridBagConstraints);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(btnEliminar, gridBagConstraints);

        jLabel6.setText("Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jLabel6, gridBagConstraints);

        txtAlias.setText("jTextField5");
        txtAlias.setInputVerifier(new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                return txtAlias.getText().compareToIgnoreCase(principal.getLogin().getAlias()) != 0;
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(txtAlias, gridBagConstraints);

        lstGrupos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstGrupos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
    GestorVentanas.setContactosAbierta(false);
}//GEN-LAST:event_formInternalFrameClosed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(this.txtAlias.getText().isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Introduzca un alias");
        return;
    }
    if(this.txtNombre.getText().isEmpty())
    {
        JOptionPane.showMessageDialog(this, "Introduzca un nombre");
        return;
    }
/*
    if(this.cmbGrupo.getSelectedIndex() == -1)
    {
        JOptionPane.showMessageDialog(this, "Seleccione un grupo");
        return;
    }*/
    
    Contacto c = new Contacto();
    c.setAlias(this.txtAlias.getText());
    c.setNombre(this.txtNombre.getText());
    c.setDireccion(this.txtDireccion.getText());
    c.setTelefono(this.txtTelefono.getText());
    c.setEmail(this.txtEmail.getText());
    /*if(this.cmbGrupo.getSelectedIndex() != -1)
    {
        c.setIdGrupo(((Grupo) this.mdlGrupos.getElementAt(this.cmbGrupo.getSelectedIndex())).getId());
    }*/
    if(this.lstContactos.isSelectionEmpty())
    {
        this.principal.guardarContacto(c);
    }
    else
    {
        this.principal.modificarContacto(c);
    }
    this.principal.actualizarContactos();
    //this.limpiarCampos();
}//GEN-LAST:event_btnGuardarActionPerformed

private void lstContactosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstContactosMouseClicked
    int seleccionado;
    
    if(evt.getClickCount() == 1)
    {
        this.lstContactos.clearSelection();
        this.limpiarCampos();
        return;
    }
    if((seleccionado=this.lstContactos.getSelectedIndex()) == -1)
    {
        this.limpiarCampos();
        return;
    }
    this.txtAlias.setEnabled(false);
    
    Contacto c = this.mdlContactos.elementAt(seleccionado);
    this.txtAlias.setText(c.getAlias());
    this.txtNombre.setText(c.getNombre());
    this.txtDireccion.setText(c.getDireccion());
    this.txtTelefono.setText(c.getTelefono());
    this.txtEmail.setText(c.getEmail());
    // mostrar en la lista los grupos a los que pertenece
/*    for(int i=0; i<this.mdlGrupos.getSize(); i++)
    {
        if(this.mdlGrupos.getElementAt(i).getId() == c.getIdGrupo())
        {
            this.cmbGrupo.setSelectedIndex(i);
            break;
        } else
        {
            this.cmbGrupo.setSelectedIndex(-1);
        }
    }*/
}//GEN-LAST:event_lstContactosMouseClicked

private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    if(this.lstContactos.isSelectionEmpty())
    {
        JOptionPane.showMessageDialog(this, "Seleccione un contacto");
        return;
    }
    
    this.principal.eliminarContacto((Contacto)this.lstContactos.getSelectedValue());
    //this.limpiarCampos();
    this.principal.actualizarContactos();
}//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lstContactos;
    private javax.swing.JList lstGrupos;
    private javax.swing.JTextField txtAlias;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        this.txtAlias.setText(null);
        this.txtNombre.setText(null);
        this.txtDireccion.setText(null);
        this.txtTelefono.setText(null);
        this.txtEmail.setText(null);
        this.cmbGrupo.setSelectedIndex(-1);
        this.lstContactos.clearSelection();
        this.txtAlias.setEnabled(true);
    }
   
    /*private boolean existeNombreContacto(String nombreContacto)
    {
        return 
    }*/
    
    protected void actualizarMdlContactos(ArrayList contactos)
    {
        this.mdlContactos.clear();
        this.limpiarCampos();
        if(contactos != null && !contactos.isEmpty())
        {
            contactos.stream().forEach((c) -> {
                this.mdlContactos.addElement((Contacto)c);
            });
        }
        this.lstContactos.repaint();
    }

    void actualizarMdlGrupos(ArrayList<Grupo> grupos) {
        this.mdlGrupos.removeAllElements();
        this.limpiarCampos();
        if(grupos != null && !grupos.isEmpty())
        {
            grupos.stream().forEach((g) -> {
                this.mdlGrupos.addElement((Grupo) g);
            });
        }
    }
}
