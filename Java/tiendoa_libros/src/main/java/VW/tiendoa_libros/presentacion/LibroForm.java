package VW.tiendoa_libros.presentacion;

import VW.tiendoa_libros.modelo.Libro;
import VW.tiendoa_libros.servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class LibroForm extends JFrame {
    LibroServicio libroServicio;
    private JPanel panel;
    private JTable tablaLibros;
    private JTextField idTexto;
    private JTextField LibroTexto;
    private JTextField autorTexto;
    private JTextField precioTexto;
    private JTextField existenciTexto;
    private JButton agregarButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private DefaultTableModel tablaModeloLibros;

    @Autowired
    public LibroForm(LibroServicio libroServicio){
        this.libroServicio = libroServicio;
        iniciarForma();
        agregarButton.addActionListener(e -> agrrgsrLibro());
        tablaLibros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarlibroselec();
            }
        });
        modificarButton.addActionListener(e -> modificarlibro());
        eliminarButton.addActionListener(e -> eliminarlibro());
    }
    public void iniciarForma() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = toolkit.getScreenSize();
        int x = (tamanioPantalla.width - getWidth()/2);
        int y = (tamanioPantalla.height - getHeight()/2);
        setLocation(x,y);

    }
    private void agrrgsrLibro(){
        // Leer valores del formulario
        if(LibroTexto.getText().equals("")){
            mostrarMensaje("Proporciona el nombre del libro");
            LibroTexto.requestFocusInWindow();
            return;
        }
        var nombreLibro = LibroTexto.getText();
        var autor = autorTexto.getText();
        var precio = Double.parseDouble(precioTexto.getText());
        var existencia = Integer.parseInt(existenciTexto.getText());
        // Crear el objeto libro
        var libro = new Libro();
        libro.setNlibro(nombreLibro);
        libro.setAutor(autor);
        libro.setPrecio(precio);
        libro.setExistencia(existencia);

        this.libroServicio.guardarLibro(libro);
        mostrarMensaje("Se agrego el libro...");
        Limpiarformu();
        listarlibros();
    }

    private void cargarlibroselec(){
        // Los indices de las columnas inician en 0
        var renglon = tablaLibros.getSelectedRow();
        if (renglon != -1){// Regresa -1 si no se selecciona
            String idLibro = tablaLibros.getModel().getValueAt(renglon, 0).toString();
            idTexto.setText(idLibro);
            String nombre = tablaLibros.getModel().getValueAt(renglon, 1).toString();
            LibroTexto.setText(nombre);
            String autor = tablaLibros.getModel().getValueAt(renglon, 2).toString();
            autorTexto.setText(autor);
            String precio = tablaLibros.getModel().getValueAt(renglon, 3).toString();
            precioTexto.setText(precio);
            String existencia = tablaLibros.getModel().getValueAt(renglon, 4).toString();
            existenciTexto.setText(existencia);
        }
    }

    private void modificarlibro(){
        if(this.idTexto.getText().equals("")){
            mostrarMensaje("Debe seleccionar un registro...");
        }
        else {
            // Verificamos que el libro no sea nulo
            if (LibroTexto.getText().equals("")){
                mostrarMensaje("Proporciona el nombre del libro...");
                LibroTexto.requestFocusInWindow();
                return;
            }
            // Llenamos el objeto libro a actualizar
            int idLibro = Integer.parseInt(idTexto.getText());
            var nombreL = LibroTexto.getText();
            var autor = autorTexto.getText();
            var pre = Double.parseDouble(precioTexto.getText());
            var exist = Integer.parseInt(existenciTexto.getText());
            var Libro = new Libro(idLibro, nombreL,autor,pre,exist);
            libroServicio.guardarLibro(Libro);
            mostrarMensaje("Se modifico el libro");
            Limpiarformu();
            listarlibros();
        }
    }

    private void eliminarlibro(){
        var ren = tablaLibros.getSelectedRow();
        if(ren != -1){
            String idLibro = tablaLibros.getValueAt(ren, 0).toString();
            var lib = new Libro();
            lib.setIdLibro(Integer.parseInt(idLibro));
            libroServicio.eliminar(lib);
            mostrarMensaje("Libro " + idLibro + " eliminado");
            Limpiarformu();
            listarlibros();
        }
        else {
            mostrarMensaje("No se ha seleccionado ningun libro...");
        }

    }

    private void Limpiarformu() {
        LibroTexto.setText("");
        autorTexto.setText("");
        precioTexto.setText("");
        existenciTexto.setText("");
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje);
    }


    private void createUIComponents() {
        // Creamos el idTexto oculto
        idTexto =  new JTextField("");
        idTexto.setVisible(false);
        // TODO: place custom component creation code here
        this.tablaModeloLibros = new DefaultTableModel(0,5){
            @Override
            public boolean isCellEditable(int row, int column){return false;}
        };
        String[] cabeceros = {"Id","Libro","Autor","Precio","Existencia"};
        this.tablaModeloLibros.setColumnIdentifiers(cabeceros);
        // Instanciar el objeto Table
        this.tablaLibros = new JTable(tablaModeloLibros);
        // Evitar que se seleccionen varios registros
        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarlibros();
    }

    private void listarlibros(){
        // Limpiar la tabla
        tablaModeloLibros.setRowCount(0);
        // Obtener los libros
        var libros = libroServicio.ListarLibros();
        libros.forEach(libro -> {
            Object[] renglonLibro = {
                    libro.getIdLibro(),
                    libro.getNlibro(),
                    libro.getAutor(),
                    libro.getPrecio(),
                    libro.getExistencia()
            };
            this.tablaModeloLibros.addRow(renglonLibro);
        });
    }
}
