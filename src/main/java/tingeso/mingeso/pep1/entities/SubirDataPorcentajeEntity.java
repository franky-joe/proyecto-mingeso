package tingeso.mingeso.pep1.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "dataPorcentajes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubirDataPorcentajeEntity {
    @Id
    @NotNull
    private String codigoProveedor;
    private String porcentajeGrasa;
    private String porcentajeSolido;
}
