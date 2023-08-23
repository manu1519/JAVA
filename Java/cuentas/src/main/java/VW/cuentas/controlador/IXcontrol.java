package VW.cuentas.controlador;

import VW.cuentas.modelo.Cuenta;
import VW.cuentas.servicio.Cuentaserv;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IXcontrol {
    @Autowired
    Cuentaserv cuentaserv;
    private List<Cuenta> cuentas;
    private Cuenta cuentaselec;

    private static final Logger logger = LoggerFactory.getLogger(IXcontrol.class);

    @PostConstruct
    public void init(){
        cargar();
    }

    public void cargar(){
        this.cuentas = cuentaserv.listCuenta();
        cuentas.forEach((cuenta) -> logger.info(cuenta.toString()));
    }

    public void agregar(){
        logger.info("Se crea el objeto cuenta seleccionada");
        this.cuentaselec = new Cuenta();
    }

    public void eliminar(){
        logger.info("Cuenta a eliminar: " + this.cuentaselec);
        this.cuentaserv.eliminar(this.cuentaselec);

        // Eliminar el registro de la lista de cuentas
        this.cuentas.remove(this.cuentaselec);

        // Reset del objeto seleccionado
        this.cuentaselec = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta eliminada"));
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes", "forma-cuentas:cuentas-tabla");
    }

    public void guardar(){
        logger.info("Cuenta a guardar: "+this.cuentaselec);
        if(this.cuentaselec.getIdCuenta() == null){
            this.cuentaserv.guardar(this.cuentaselec);
            this.cuentas.add(this.cuentaselec);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cuenta agregada"));
        }
        else{
            this.cuentaserv.guardar(this.cuentaselec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cuenta actualizada"));
        }
        // Ocultamos la ventana
        PrimeFaces.current().executeScript("PF('ventanaModalCuenta').hide()");
        // Actualizamos la tabla
        PrimeFaces.current().ajax().update("forma-cuentas:mensajes",
                "forma-cuentas:cuentas-tabla");

        // Rerset
        this.cuentaselec = null;
    }

}
