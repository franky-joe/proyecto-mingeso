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
    private String nombreProveedor;
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

    public Void dejarEnCero(){
        setId(1);
        setQuincena("12");
        setCodigoProveedor("12345");
        setNombreProveedor("franco");
        setTotalKilosLeche(0);
        setNumeroDiasEnvioLeche(0);
        setPromedioDiarioKilosLeche(0F);
        setPorcentajeVariacionLeche(0F);
        setPorcentajeVariacionGrasa(0F);
        setPorcentajeSolidosTotales(0F);
        setPorcentajeVariacionSolidosTotales(0F);
        setPagoPorLeche(0F);
        setPagoPorGrasa(0F);
        setPagoPorSolidosTotales(0F);
        setBonificacionPorFrecuencia(0F);
        setDescuentoVariacionLeche(0F);
        setDescuentoVariacionGrasa(0F);
        setDescuentoVariacionSolidosTotales(0F);
        setPagoTotal(0F);
        setMontoRetencion(0F);
        setMontoFinal(0F);
        return null;
    }
    //	VALUES (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
}
