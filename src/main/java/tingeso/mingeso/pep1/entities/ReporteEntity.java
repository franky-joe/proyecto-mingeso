package tingeso.mingeso.pep1.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reporte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReporteEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String quincena;
    private String codigoProveedor;
    private String NombreProveedor;
    private Integer totalKilosLeche;
    private Integer numeroDiasEnvioLeche;
    private Float promedioDiarioKilosLeche;
    private Float porcentajeVariacionLeche;
    private Float porcentajeVariacionGrasa;
    private Float porcentajeSolidosTotales;
    private Float porcentajeVariacionSolidosTotales;
    private Float pagoPorLeche;
    private Float pagoPorGrasa;
    private Float pagoPorSolidosTotales;
    private Float bonificacionPorFrecuencia;
    private Float descuentoVariacionLeche;
    private Float descuentoVariacionGrasa;
    private Float descuentoVariacionSolidosTotales;
    private Float pagoTotal;
    private Float montoRetencion;
    private Float montoFinal;

}
