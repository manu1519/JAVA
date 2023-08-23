package VW.contactos.Control;

import VW.contactos.modelo.Contacto;
import VW.contactos.servicio.ContactoServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Contactocontrol {

    private static final Logger logger = LoggerFactory.getLogger(Contactocontrol.class);
    @Autowired
    ContactoServ contactoServ;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServ.listarContactos();
        contactos.forEach((contacto) -> logger.info(contacto.toString()));
        modelo.put("contactos", contactos);
        return "index"; //index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoforma") Contacto contacto){
        logger.info("Contacto a agregar: " + contacto);
        contactoServ.guardar(contacto);
        return "redirect:/"; // se redirige al control path "/"
    }

    @GetMapping("/editar/{id}")
    public String mostrareditar(@PathVariable(value="id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServ.buscarId(idContacto);
        logger.info("Contacto a editar (mostrar): "+ contacto);
        modelo.put("contacto", contacto);
        return "editar"; //editar.html
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto") Contacto contacto){
        logger.info("Contacto a guardar (editar):" + contacto);
        contactoServ.guardar(contacto);
        return "redirect:/";// Redirijimos al controlador path "/"
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idContacto){
        Contacto contacto= new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServ.eliminar(contacto);
        return "redirect:/";
    }

}
