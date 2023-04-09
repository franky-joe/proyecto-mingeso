package tingeso.mingeso.pep1.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class EstudianteEntity {
    @Id
    @NotNull
    private Integer ID;
    private String nombre;
    private String apellido;
    private String email;

}
