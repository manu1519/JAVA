package VW.RHumanos.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RNEE extends RuntimeException{
    public RNEE(String mensaje){
        super(mensaje);
    }
}
