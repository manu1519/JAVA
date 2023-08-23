package VW.Tareas.controlador;

import VW.Tareas.modelo.tarea;
import VW.Tareas.servicio.TareaSer;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.Property;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;
@Component
public class indexcontrol implements Initializable {

    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(indexcontrol.class);

    @Autowired
    private TareaSer tareaSer;
    @FXML
    private TableView<tarea> tareaTabla;
    @FXML
    private TableColumn<tarea, Integer> idTareaCol;
    @FXML
    private TableColumn<tarea,String> NombreTareaCol;
    @FXML
    private TableColumn<tarea, String> responsableCol;
    @FXML
    private TableColumn<tarea, String> estatusCol;

    private final ObservableList<tarea> tareasLista =
            FXCollections.observableArrayList();

    @FXML
    private TextField tareatexto;
    @FXML
    private TextField responsabletexto;
    @FXML
    private TextField estatustexto;
    private Integer idtarIn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumna();
        listartareas();
    }

    private void configurarColumna(){
        idTareaCol.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        NombreTareaCol.setCellValueFactory(new PropertyValueFactory<>("nTarea"));
        responsableCol.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusCol.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listartareas(){
        logger.info("Ejecutando listado de tareas");
        tareasLista.clear();
        tareasLista.addAll(tareaSer.listarTareas());
        tareaTabla.setItems(tareasLista);
    }

    public void agregarTarea(){
        if(tareatexto.getText().isEmpty()){
            mostrarMensaje("Error validación","Debe proporcionar una tarea");
            tareatexto.requestFocus();
            return;

        }
        else {
            var tar = new tarea();
            recolectarDatosForm(tar);
            tar.setIdTarea(null);
            tareaSer.guardar(tar);
            mostrarMensaje("Información","Tarea agregada");
            limpiarForm();
            listartareas();
        }
    }

    public void cargarTareaF(){
        var tar = tareaTabla.getSelectionModel().getSelectedItem();
        if(tar != null){
            idtarIn = tar.getIdTarea();
            tareatexto.setText(tar.getNTarea());
            responsabletexto.setText(tar.getResponsable());
            estatustexto.setText(tar.getEstatus());
        }
    }

    public void modificarTarea(){
        if(idtarIn  == null){
            mostrarMensaje("Informacion","Debe seleccionar una tarea");
            return;
        }
        if(tareatexto.getText().isEmpty()) {
            mostrarMensaje("Error","Debe proporcionar una tarea");
            tareatexto.requestFocus();
            return;
        }
        var tar = new tarea();
        recolectarDatosForm(tar);
        tareaSer.guardar(tar);
        mostrarMensaje("Informacion","Tarea modificada");
        limpiarForm();
        listartareas();
    }

    public void eliminarTarea(){
        var tar = tareaTabla.getSelectionModel().getSelectedItem();
        if(tar  != null){
            logger.info("Registro a eliminar: " + tar.toString());
            tareaSer.eliminar(tar);
            mostrarMensaje("Informacion","Tarea eliminada " + tar.getIdTarea());
            limpiarForm();
            listartareas();
        }
        else {
            mostrarMensaje("Error","No se ha seleccionado ninguna tarea");
        }
    }


    private void recolectarDatosForm(tarea tarea){
        if(idtarIn != null){
            tarea.setIdTarea(idtarIn);
        }
        tarea.setNTarea(tareatexto.getText());
        tarea.setResponsable(responsabletexto.getText());
        tarea.setEstatus(estatustexto.getText());
    }

    public void limpiarForm(){
        idtarIn=null;
        tareatexto.clear();
        responsabletexto.clear();
        estatustexto.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}


