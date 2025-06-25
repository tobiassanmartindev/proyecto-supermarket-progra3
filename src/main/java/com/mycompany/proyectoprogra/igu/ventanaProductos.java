
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Categorias;
import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Productos;
import static java.awt.SystemColor.control;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaProductos extends javax.swing.JFrame {
    
    Controladora control = new Controladora();
    private long productoSeleccionadoId = -1;

    public ventanaProductos() {
        initComponents();
        cargarCategoriasEnCombo();
        cargarTabla();
    }
    
    
    private void cargarCategoriasEnCombo() {
    List<Categorias> categorias = control.traerCategorias();
    categoriaCombo.removeAllItems();
    for (Categorias cat : categorias) {
        categoriaCombo.addItem(cat.toString()); // Aquí agregas el String
    }
}
    
    
    
    
   private void cargarTabla() {
        String[] columnas = {"ID", "Nombre", "Categoría"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Productos> listaProductos = control.traerProductos();
        for (Productos p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombreProducto(),
                p.getIdCategoria().getCategoria() + " - " + p.getIdCategoria().getSubcategoria()
            };
            modelo.addRow(fila);
        }

        tableProductos.setModel(modelo);
    }
    
    private void limpiarCampos() {
        NombreTXT.setText("");
        categoriaCombo.setSelectedIndex(-1);
        productoSeleccionadoId = -1;
    }

    /*
    private void guardarProducto() {
        System.out.println("ENTRO EN GUARDAR PRODCTO \n\n\n");
        String nombre = NombreTXT.getText();
        Categorias categoriaSeleccionada = (Categorias) categoriaCombo.getSelectedItem();

        if (!nombre.isBlank() && categoriaSeleccionada != null) {
            try {
                control.agregarProducto(nombre, categoriaSeleccionada);
                JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");
                cargarTabla();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
        }
    }

   private void modificarProducto() {
        String nuevoNombre = NombreTXT.getText();
        Categorias nuevaCategoria = (Categorias) categoriaCombo.getSelectedItem();

        if (productoSeleccionadoId != -1 && !nuevoNombre.isBlank() && nuevaCategoria != null) {
            try {
                control.modificarProducto(productoSeleccionadoId, nuevoNombre, nuevaCategoria);
                JOptionPane.showMessageDialog(this, "Producto modificado correctamente.");
                cargarTabla();
                limpiarCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto y complete los campos.");
        }
    }
    */
    
    private void guardarProducto() {
    System.out.println("ENTRO EN GUARDAR PRODCTO \n\n\n");
    String nombre = NombreTXT.getText();

    // Obtener el String seleccionado del JComboBox
    String categoriaStringSeleccionada = (String) categoriaCombo.getSelectedItem();
    Categorias categoriaSeleccionada = null; // Inicializar a null

    if (categoriaStringSeleccionada != null) {
        
        List<Categorias> todasLasCategorias = control.traerCategorias(); // Obtener la lista completa
        for (Categorias cat : todasLasCategorias) {
            if (cat.toString().equals(categoriaStringSeleccionada)) {
                categoriaSeleccionada = cat;
                break;
            }
        }
    }

    if (!nombre.isBlank() && categoriaSeleccionada != null) { // Asegúrate de que categoriaSeleccionada no sea null
        try {
            control.agregarProducto(nombre, categoriaSeleccionada);
            JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Complete todos los campos y seleccione una categoría válida.");
    }
}

private void modificarProducto() {
    String nuevoNombre = NombreTXT.getText();
    String categoriaStringSeleccionada = (String) categoriaCombo.getSelectedItem();
    Categorias nuevaCategoria = null; // Inicializar a null

    if (categoriaStringSeleccionada != null) {
        // Lógica similar a guardarProducto() para buscar la categoría
        List<Categorias> todasLasCategorias = control.traerCategorias();
        for (Categorias cat : todasLasCategorias) {
            if (cat.toString().equals(categoriaStringSeleccionada)) {
                nuevaCategoria = cat;
                break;
            }
        }
    }

    if (productoSeleccionadoId != -1 && !nuevoNombre.isBlank() && nuevaCategoria != null) {
        try {
            control.modificarProducto(productoSeleccionadoId, nuevoNombre, nuevaCategoria);
            JOptionPane.showMessageDialog(this, "Producto modificado correctamente.");
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione un producto, complete los campos y seleccione una categoría válida.");
    }
}
    
    
    private void eliminarProducto() {
        if (productoSeleccionadoId != -1) {
            control.eliminarProducto(productoSeleccionadoId);
            JOptionPane.showMessageDialog(this, "Producto eliminado.");
            cargarTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla.");
        }
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
        jLabel4 = new javax.swing.JLabel();
        categoriaCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();

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
        EliminarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarBTNActionPerformed(evt);
            }
        });

        jLabel4.setText("Categoria");

        categoriaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18)
                            .addComponent(categoriaCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(ModificarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                    .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(GuardarBTN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(categoriaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(217, 217, 217)
                .addComponent(GuardarBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EliminarBTN)
                    .addComponent(ModificarBTN))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProductos);

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed

        guardarProducto();
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        int filaSeleccionada = tableProductos.getSelectedRow();
    if (filaSeleccionada >= 0) {
        productoSeleccionadoId = (Long) tableProductos.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tableProductos.getValueAt(filaSeleccionada, 1);
        String categoriaNombreEnTabla = (String) tableProductos.getValueAt(filaSeleccionada, 2); // Asumo que columna 2 es la categoría

        NombreTXT.setText(nombre);

        // Intentar seleccionar el ítem correcto en el JComboBox
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) categoriaCombo.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equals(categoriaNombreEnTabla)) {
                categoriaCombo.setSelectedIndex(i);
                break;
            }
        }
    }
    }//GEN-LAST:event_tableProductosMouseClicked

    //NO TOCAR ESTO
    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void ModificarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarBTNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ModificarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
      
        
    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void ModificarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBTNActionPerformed
        modificarProducto();
    }//GEN-LAST:event_ModificarBTNActionPerformed

    private void EliminarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarBTNActionPerformed
        eliminarProducto();
    }//GEN-LAST:event_EliminarBTNActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JButton ModificarBTN;
    private javax.swing.JTextField NombreTXT;
    private javax.swing.JComboBox<String> categoriaCombo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProductos;
    // End of variables declaration//GEN-END:variables
}
