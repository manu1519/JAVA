package VW.contactos.servicio;

import VW.contactos.modelo.Contacto;
import VW.contactos.repo.ContactoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoServ implements IContactoserv{

    @Autowired
    private ContactoRepo contactoRepo;

    @Override
    public List<Contacto> listarContactos() {
        return contactoRepo.findAll();
    }

    @Override
    public Contacto buscarId(Integer idContacto) {
        Contacto contacto = contactoRepo.findById(idContacto).orElse(null);
        return contacto;
    }

    @Override
    public void guardar(Contacto contacto) {
        contactoRepo.save(contacto);
    }

    @Override
    public void eliminar(Contacto contacto) {
        contactoRepo.delete(contacto);
    }
}
