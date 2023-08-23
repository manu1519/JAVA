package VW.empleados.controlador;

import VW.empleados.modelo.Empleado;
import VW.empleados.servicio.EmpleadoServ;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class indexControl {
    private static final Logger logger=
            getLogger(indexControl.class);

    @Autowired
    EmpleadoServ empleadoServ;


    @RequestMapping(value="/",method = RequestMethod.GET)
    public String iniciar(ModelMap modelo){
        List<Empleado> empleados = empleadoServ.listaremp();
        empleados.forEach((empleado) -> logger.info(empleado.toString()));
        // Compartimos el modelo con la vista
        modelo.put("empleados", empleados);
        return "index"; // index.jsp
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.GET)
    public String mostrarAgregar(){
        return "agregar";
    }

    @RequestMapping(value = "/agregar", method = RequestMethod.POST)
    public String agregar(@ModelAttribute("empleadoForma") Empleado empleado, HttpServletRequest request){
        logger.info("Empleado a agregar: " + empleado);
        empleadoServ.guardarEmp(empleado);
        return "redirect:/";
    }

    @RequestMapping(value = "/editar", method = RequestMethod.GET)
    public String mostrarEditar(@RequestParam int idEmpleado, ModelMap modelo){
        Empleado empleado = empleadoServ.buscarEmpleadoID(idEmpleado);
        logger.info("Empleado a editar: "+empleado);
        modelo.put("empleados", empleado);
        return "editar"; //mostrar editar.jsp
    }

    @RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String editar(@ModelAttribute("empleadoForma") Empleado empleado){
        logger.info("Empleado a guardar (editar): " + empleado);
        empleadoServ.guardarEmp(empleado);
        return "redirect:/";
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public String eliminar(@RequestParam int idEmpleado){
        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleadoServ.eliminarEmp(empleado);
        return "redirect:/";
    }
}
