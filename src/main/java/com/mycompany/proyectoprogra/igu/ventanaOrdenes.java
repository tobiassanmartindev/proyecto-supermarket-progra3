
package com.mycompany.proyectoprogra.igu;

import com.mycompany.proyectoprogra.entitys.Clientes;
import com.mycompany.proyectoprogra.entitys.Controladora;
import com.mycompany.proyectoprogra.entitys.Detalleordenes;
import com.mycompany.proyectoprogra.entitys.Productos;
import static java.awt.SystemColor.control;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ventanaOrdenes extends javax.swing.JFrame {
    
    private List<Detalleordenes> detallesTemp = new ArrayList<>();
    Controladora control = new Controladora();
    private long productoSeleccionadoId = -1;
   

   public ventanaOrdenes() {    
        initComponents();
        cargarTablaProductos();
        cargarModosEnvio();
        inicializarTablaDetallesOrden(); // Inicializa la tabla de detalles de la orden
    }

    // --- Métodos de Carga de Datos en la UI ---
    private void cargarTablaProductos() {
        String[] columnas = {"ID", "Nombre", "Categoría", "Precio Unitario"}; // Agrega Precio Unitario
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        List<Productos> listaProductos = control.traerProductos();
        for (Productos p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombreProducto(),
                // Asegúrate que getIdCategoria() y getCategoria()/getSubcategoria() existen en tu entidad Categorias
                p.getIdCategoria() != null ? p.getIdCategoria().getCategoria() + " - " + p.getIdCategoria().getSubcategoria() : "Sin Categoría",
                p.getPrecioUnitario() // Asume que ya tienes este getter en tu entidad Productos
            };
            modelo.addRow(fila);
        }
        tableProductos.setModel(modelo);
    }
   

    private void cargarModosEnvio() {
        // Puedes obtener esto de la base de datos (por ejemplo, de una tabla de 'TiposEnvio')
        // o definirlo estáticamente si son pocos y fijos.
        String[] modosEnvio = {"Estándar", "Express", "Retiro en Sucursal", "Internacional"};
        jComboBox1.setModel(new DefaultComboBoxModel<>(modosEnvio));
    }
    
    // Nueva tabla para los detalles de la orden actual (productos agregados)
    private void inicializarTablaDetallesOrden() {
        String[] columnasDetalles = {"ID Producto", "Producto", "Cantidad", "Precio Unitario", "Total Venta", "Descuento", "Ganancia"};
        DefaultTableModel modeloDetalles = new DefaultTableModel(columnasDetalles, 0);
      //  jTableDetallesOrden.setModel(modeloDetalles); 
    }

    private void actualizarTablaDetallesOrden() {
        String[] columnasDetalles = {"ID Producto", "Producto", "Cantidad", "Precio Unitario", "Total Venta", "Descuento", "Ganancia"};
        DefaultTableModel modeloDetalles = new DefaultTableModel(columnasDetalles, 0);

        for (Detalleordenes detalle : detallesTemp) {
            Object[] fila = {
                detalle.getProductos().getIdProducto(),
                detalle.getProductos().getNombreProducto(),
                detalle.getCantidad(),
                detalle.getProductos().getPrecioUnitario(), // Muestra el precio unitario del producto
                detalle.getTotalVenta(),
                detalle.getDescuento(),
                detalle.getGanancia()
            };
            modeloDetalles.addRow(fila);
        }
       // jTableDetallesOrden.setModel(modeloDetalles);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel1 = new javax.swing.JPanel();
        FechaTXT = new javax.swing.JTextField();
        CPostalTXT = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        CiudadField = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CiudadTXT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        EstadoTXT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        IdClienteTXT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cantidadTXT = new javax.swing.JTextField();
        agregarBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductos = new javax.swing.JTable();
        nombreTXT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        GuardarBTN = new javax.swing.JButton();
        EliminarBTN = new javax.swing.JButton();
        ModificarBTN = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        CiudadField.setText("Ciudad");

        jLabel5.setText("C. postal");

        jLabel6.setText("Modo de envio");

        jLabel4.setText("Estado");

        jLabel7.setText("Fecha de orden");

        jLabel1.setText("Detalle de envio");

        jLabel9.setText("ID cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FechaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IdClienteTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(171, 171, 171))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(CPostalTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CiudadField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CiudadTXT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EstadoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FechaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IdClienteTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(147, 147, 147)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(EstadoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CiudadField)
                    .addComponent(CiudadTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CPostalTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setText("Cantidad");

        cantidadTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadTXTActionPerformed(evt);
            }
        });

        agregarBTN.setText("Agregar producto a la orden");
        agregarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBTNActionPerformed(evt);
            }
        });

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

        nombreTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTXTActionPerformed(evt);
            }
        });

        jLabel8.setText("Producto seleccionado");

        GuardarBTN.setText("Guardar orden");
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

        EliminarBTN.setText("Eliminar");
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ModificarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(EliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GuardarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreTXT)
                    .addComponent(cantidadTXT)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(agregarBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(agregarBTN)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GuardarBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EliminarBTN)
                            .addComponent(ModificarBTN))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


   
    private void GuardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBTNActionPerformed
        // Validar campos de entrada
        if (FechaTXT.getText().isEmpty() || IdClienteTXT.getText().isEmpty() ||
            EstadoTXT.getText().isEmpty() || CiudadTXT.getText().isEmpty() ||
            CPostalTXT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de la orden y envío son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Usa el formato correcto para "2025-06-25"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false); // Para evitar fechas inválidas tipo 2025-02-30

            Date fechaOrden = dateFormat.parse(FechaTXT.getText());

            long idCliente = Long.parseLong(IdClienteTXT.getText());

            // Datos del Envío
            String estadoEnvio = EstadoTXT.getText();
            String ciudadEnvio = CiudadTXT.getText();
            String modoEnvio = (String) jComboBox1.getSelectedItem();
            int codigoPostalEnvio = Integer.parseInt(CPostalTXT.getText());

            if (detallesTemp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe agregar al menos un producto a la orden.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean guardado = control.guardarOrdenCompleta(fechaOrden, idCliente, modoEnvio, ciudadEnvio, estadoEnvio, codigoPostalEnvio, detallesTemp);

            if (guardado) {
                JOptionPane.showMessageDialog(this, "Orden guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCamposOrden();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar la orden. Verifique ID Cliente, datos de envío o la disponibilidad de productos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID Cliente o Código Postal deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al guardar la orden: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_GuardarBTNActionPerformed

    private void GuardarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarBTNMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_GuardarBTNMouseClicked

    private void ModificarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarBTNMouseClicked
        // TODO add your handling code here:
    JOptionPane.showMessageDialog(this, "La función de modificar órdenes completas no está implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_ModificarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
       
   JOptionPane.showMessageDialog(this, "La función de eliminar órdenes completas no está implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void ModificarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarBTNActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ModificarBTNActionPerformed

    private void cantidadTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadTXTActionPerformed

    private void agregarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBTNActionPerformed
     int fila = tableProductos.getSelectedRow();
        if (fila >= 0) {
            try {
                long idProducto = (Long) tableProductos.getValueAt(fila, 0);
                int cantidad = Integer.parseInt(cantidadTXT.getText());

                if (cantidad <= 0) {
                     JOptionPane.showMessageDialog(this, "La cantidad debe ser un número positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                     return;
                }

                Productos producto = control.traerProducto(idProducto); // Trae el producto completo para obtener su precio
                if (producto != null) {
                    Detalleordenes detalle = new Detalleordenes();
                    detalle.setProductos(producto); // Asigna el objeto Producto completo
                    detalle.setCantidad(cantidad);
                    
                    // --- Cálculo de TotalVenta, Descuento y Ganancia ---
                    double precioUnitarioProducto = producto.getPrecioUnitario(); // Obtiene el precio del producto
                    
                    float totalVenta = (float) (precioUnitarioProducto * cantidad);
                    float descuento = 0.0f; // Puedes implementar lógica de descuento aquí (ej: descuento % por cantidad)
                    float ganancia = totalVenta * 0.20f; // Ejemplo: 20% de ganancia fija

                    detalle.setTotalVenta(totalVenta);
                    detalle.setDescuento(descuento);
                    detalle.setGanancia(ganancia);

                    // Verifica si el producto ya está en la lista detallesTemp
                    boolean productoYaAgregado = false;
                    for (Detalleordenes d : detallesTemp) {
                        if (d.getProductos().getIdProducto() == idProducto) {
                            // Si el producto ya existe, actualiza la cantidad y los cálculos
                            d.setCantidad(d.getCantidad() + cantidad);
                            d.setTotalVenta(d.getTotalVenta() + totalVenta);
                            d.setDescuento(d.getDescuento() + descuento); // Suma el descuento si se acumula
                            d.setGanancia(d.getGanancia() + ganancia);
                            productoYaAgregado = true;
                            break;
                        }
                    }

                    if (!productoYaAgregado) {
                        detallesTemp.add(detalle); // Agrega a la lista temporal si es un producto nuevo
                    }
                    
                    actualizarTablaDetallesOrden(); // Actualiza la tabla de detalles de la orden

                    JOptionPane.showMessageDialog(this, "Producto agregado a la orden (temporalmente).");
                    cantidadTXT.setText("");
                    nombreTXT.setText(""); // Limpia el nombre del producto seleccionado
                    productoSeleccionadoId = -1; // Resetear la selección
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Producto no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Cantidad inválida. Por favor, ingrese un número entero.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para agregar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_agregarBTNActionPerformed

    private void tableProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductosMouseClicked
        int filaSeleccionada = tableProductos.getSelectedRow();
        if (filaSeleccionada >= 0) {
            productoSeleccionadoId = (Long) tableProductos.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tableProductos.getValueAt(filaSeleccionada, 1);
            nombreTXT.setText(nombre);
        }  
    }//GEN-LAST:event_tableProductosMouseClicked

    private void nombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreTXTActionPerformed

  
    // --- Métodos de Utilidad ---
    private void limpiarCamposOrden() {
        FechaTXT.setText("");
        IdClienteTXT.setText(""); // ID Cliente
        EstadoTXT.setText("");
        CiudadTXT.setText("");
        jComboBox1.setSelectedIndex(0); // Reinicia el combobox
        CPostalTXT.setText("");
        cantidadTXT.setText("");
        nombreTXT.setText("");
        detallesTemp.clear(); // Limpia la lista de detalles temporales
        actualizarTablaDetallesOrden(); // Refresca la tabla de detalles para mostrarla vacía
    }
    
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CPostalTXT;
    private javax.swing.JLabel CiudadField;
    private javax.swing.JTextField CiudadTXT;
    private javax.swing.JButton EliminarBTN;
    private javax.swing.JTextField EstadoTXT;
    private javax.swing.JTextField FechaTXT;
    private javax.swing.JButton GuardarBTN;
    private javax.swing.JTextField IdClienteTXT;
    private javax.swing.JButton ModificarBTN;
    private javax.swing.JButton agregarBTN;
    private javax.swing.JTextField cantidadTXT;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombreTXT;
    private javax.swing.JTable tableProductos;
    // End of variables declaration//GEN-END:variables
}
