package VW.Inventarios.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Recursonoencontrado extends RuntimeException{
    public Recursonoencontrado(String mensaje){
        super(mensaje);
    }
}
