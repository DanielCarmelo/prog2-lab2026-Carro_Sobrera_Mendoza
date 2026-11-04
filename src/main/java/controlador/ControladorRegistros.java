package controlador;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import fachada.FachadaRegistros;
import modelo.Articulo;
import modelo.Seccion;
import vista.VentanaPrincipal;

public class ControladorRegistros {
    private final VentanaPrincipal vista;
    private final FachadaRegistros fachada;

    public ControladorRegistros(VentanaPrincipal vista) {
        this.vista = vista;
        this.fachada = FachadaRegistros.getInstancia();
        inicializarAcciones();
        actualizarTabla(fachada.obtenerArticulos());
    }

    private void inicializarAcciones() {
        // Registrar artículo
        vista.getBtnRegistrar().addActionListener(e -> registrarArticulo());
        
        // Modificar artículo
        vista.getBtnModificar().addActionListener(e -> modificarArticulo());
        
        // Eliminar artículo
        vista.getBtnEliminar().addActionListener(e -> eliminarArticulo());
        
        // Limpiar formulario
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarFormulario());

        // Selección en la tabla para cargar en el formulario
        vista.getTablaArticulos().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarArticuloSeleccionado();
            }
        });

        // Filtrado por RadioButtons o buscador de texto
        vista.getTxtBuscador().addActionListener(e -> aplicarFiltros());
        vista.getRbTodos().addActionListener(e -> aplicarFiltros());
        vista.getRbOferta().addActionListener(e -> aplicarFiltros());
        vista.getRbBajoStock().addActionListener(e -> aplicarFiltros());
    }

    public final void actualizarTabla(List<Articulo> lista) {
        DefaultTableModel modeloTabla = vista.getModeloTabla();
        modeloTabla.setRowCount(0);
        for (Articulo a : lista) {
            Seccion sec = Seccion.obtenerPorCodigo(a.getIdSeccion());
            String nombreSeccion = (sec != null) ? sec.getEtiqueta() : String.valueOf(a.getIdSeccion());
            
            Object[] fila = {
                a.getCodigoBarras(),
                nombreSeccion,
                a.getDescripcion(),
                a.getPrecioCosto(),
                a.getOferta() ? "Sí" : "No",
                a.getStock()
            };
            modeloTabla.addRow(fila);
        }
    }

private void registrarArticulo() {
    // Verificar si hay una fila seleccionada en la tabla
    int filaSeleccionada = vista.getTablaArticulos().getSelectedRow();
    
    if (filaSeleccionada != -1) {
        // Si hay un elemento seleccionado, preguntar al usuario si desea crear uno nuevo
        int confirmacion = JOptionPane.showConfirmDialog(
            vista,
            "Actualmente hay un artículo seleccionado en la tabla.\n¿Desea limpiar el formulario y registrar un artículo NUEVO?",
            "Confirmar Nuevo Registro",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Si acepta, limpiamos la selección, habilitamos el código de barras y detenemos el registro por ahora
            vista.limpiarFormulario();
            vista.getTablaArticulos().clearSelection();
            vista.getTxtCodigoBarras().setEnabled(true);
            vista.getTxtCodigoBarras().requestFocus();
            JOptionPane.showMessageDialog(vista, "Formulario listo para ingresar un nuevo artículo. Ingrese los datos y presione Registrar nuevamente.", "Nuevo Registro", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            // Si cancela, simplemente abortamos la acción de registro
            return;
        }
    }

    // Si NO hay ningún elemento seleccionado, procede con el registro normal
    try {
        String codigo = vista.getTxtCodigoBarras().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();
        double precio = Double.parseDouble(vista.getTxtPrecioCosto().getText().trim().replace(",", "."));
        boolean oferta = vista.getChkOferta().isSelected();
        int stock = (int) vista.getSpinnerStock().getValue();

        fachada.registrarArticulo(codigo, descripcion, precio, oferta, stock);
        actualizarTabla(fachada.obtenerArticulos());
        vista.limpiarFormulario();
        
        JOptionPane.showMessageDialog(vista, "Artículo registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(vista, "Error en el formato numérico del precio o stock.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
    }
}

    private void modificarArticulo() {
        try {
            String codigo = vista.getTxtCodigoBarras().getText().trim();
            String descripcion = vista.getTxtDescripcion().getText().trim();
            double precio = Double.parseDouble(vista.getTxtPrecioCosto().getText().trim().replace(",", "."));
            boolean oferta = vista.getChkOferta().isSelected();
            int stock = (int) vista.getSpinnerStock().getValue();

            fachada.modificarArticulo(codigo, descripcion, precio, oferta, stock);
            actualizarTabla(fachada.obtenerArticulos());
            JOptionPane.showMessageDialog(vista, "Artículo modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarArticulo() {
        int filaSeleccionada = vista.getTablaArticulos().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(vista, "Seleccione un artículo de la tabla para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigo = (String) vista.getTablaArticulos().getValueAt(filaSeleccionada, 0);
        int confirmacion = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar el artículo con EAN: " + codigo + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                fachada.eliminarArticulo(codigo);
                actualizarTabla(fachada.obtenerArticulos());
                vista.limpiarFormulario();
                JOptionPane.showMessageDialog(vista, "Artículo eliminado correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarArticuloSeleccionado() {
        int fila = vista.getTablaArticulos().getSelectedRow();
        if (fila != -1) {
            vista.getTxtCodigoBarras().setText((String) vista.getTablaArticulos().getValueAt(fila, 0));
            vista.getTxtCodigoBarras().setEnabled(false); // Mantener EAN inalterable al modificar[cite: 10]
            vista.getTxtDescripcion().setText((String) vista.getTablaArticulos().getValueAt(fila, 2));
            vista.getTxtPrecioCosto().setText(String.valueOf(vista.getTablaArticulos().getValueAt(fila, 3)));
            vista.getChkOferta().setSelected("Sí".equals(vista.getTablaArticulos().getValueAt(fila, 4)));
            vista.getSpinnerStock().setValue(vista.getTablaArticulos().getValueAt(fila, 5));
        }
    }

    private void aplicarFiltros() {
        String criterio = vista.getTxtBuscador().getText().trim();
        String tipoFiltro = "TODOS";
        if (vista.getRbOferta().isSelected()) {
            tipoFiltro = "OFERTA";
        } else if (vista.getRbBajoStock().isSelected()) {
            tipoFiltro = "BAJO_STOCK";
        }
        List<Articulo> filtrados = fachada.filtrarArticulos(criterio, tipoFiltro);
        actualizarTabla(filtrados);
    }
}
