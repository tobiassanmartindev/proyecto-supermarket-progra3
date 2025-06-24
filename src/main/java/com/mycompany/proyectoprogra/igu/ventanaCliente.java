/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaCliente extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long clienteSeleccionadoId = -1;

    public ventanaCliente() {
        initComponents();
        cargarTabla();
    }
    
    private void cargarTabla() {
        String[] columnas = {"ID", "Nombre"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Clientes> listaClientes = control.traerClientes();
        for (Clientes c : listaClientes) {
            Object[] fila = {c.getIdCliente(), c.getNombreCliente()};
            modelo.addRow(fila);
        }

        tableClientes.setModel(modelo);
    }
    
    private void limpiarCampos() {
        NombreTXT.setText("");
        clienteSeleccionadoId = -1;
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        GuardarBTN = new javax.swing.JButton();
        ModificarBTN = new javax.swing.JButton();
        EliminarBTN = new javax.swing.JButton();
        NombreTXT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("NOMBRE");

        GuardarBTN.setText("Guardar");
        GuardarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GuardarBTNMouseClicked(evt);
            }
        });
        GuardarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarBTNActionPerformed(evt);
            }
        });

        ModificarBTN.setText("Modificar");
        ModificarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarBTNMouseClicked(evt);
            }
        });
        ModificarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarBTNActionPerformed(evt);
            }
        });

        EliminarBTN.setText("Eliminar");
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(NombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ModificarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(GuardarBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(154, 154, 154)
                .addComponent(GuardarBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EliminarBTN)
                    .addComponent(ModificarBTN))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableClientes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCliente() {
        String nombre = NombreTXT.getText();
        if (!nombre.isBlank()) {
            try {
                control.agregarCliente(nombre);
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
                cargarTabla();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
        }
    }
    
     private void eliminarCliente() {
        if (clienteSeleccionadoId != -1) {
            control.eliminarCliente(clienteSeleccionadoId);
            JOptionPane.showMessageDialog(this, "Cliente eliminado.");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla.");
        }
    }
    
    private void modificarCliente() {
    if (clienteSeleccionadoId != -1) { // Si hay un cliente seleccionado
        String nuevoNombre = NombreTXT.getText();

        if (!nuevoNombre.isBlank()) { // Si el nuevo nombre no está vacío
            try { // <<< INICIA EL BLOQUE TRY-CATCH AQUÍ
                control.modificarCliente(clienteSeleccionadoId, nuevoNombre); // Llama a tu Controladora
                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla(); // Recarga la tabla para ver el cambio
                limpiarCampos(); // Limpia los campos de entrada
            } catch (Exception e) { // <<< CAPTURA CUALQUIER EXCEPCIÓN
                // Muestra un mensaje de error al usuario
                JOptionPane.showMessageDialog(this, "Error al modificar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                // Muy importante para depuración: imprime la pila de la excepción en la consola
                e.printStackTrace(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "El nuevo nombre no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla para modificarlo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
}
    
    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed
        guardarCliente();
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void tableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClientesMouseClicked
       int filaSeleccionada = tableClientes.getSelectedRow();
        if (filaSeleccionada >= 0) {
            clienteSeleccionadoId = (Long) tableClientes.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tableClientes.getValueAt(filaSeleccionada, 1);
            NombreTXT.setText(nombre);
        }
    }//GEN-LAST:event_tableClientesMouseClicked

    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void ModificarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarBTNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ModificarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
        eliminarCliente();
        
    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void ModificarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBTNActionPerformed
        // TODO add your handling code here:
        modificarCliente();
    }//GEN-LAST:event_ModificarBTNActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JButton ModificarBTN;
    private javax.swing.JTextField NombreTXT;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableClientes;
    // End of variables declaration//GEN-END:variables
}
