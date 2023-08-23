package VW.cuentas.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCuenta;
    String nombre;
    String TCuenta;
    Double saldo;
}
