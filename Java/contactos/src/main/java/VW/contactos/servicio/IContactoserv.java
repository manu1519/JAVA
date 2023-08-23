package VW.contactos.servicio;

import VW.contactos.modelo.Contacto;
import java.util.List;

public interface IContactoserv {
    public List<Contacto> listarContactos();
    public Contacto buscarId(Integer idContacto);
    public void guardar(Contacto contacto);
    public void eliminar(Contacto contacto);
}
