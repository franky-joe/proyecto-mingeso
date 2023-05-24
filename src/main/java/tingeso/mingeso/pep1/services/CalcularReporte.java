package tingeso.mingeso.pep1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.mingeso.pep1.entities.ProveedorEntity;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import tingeso.mingeso.pep1.repositories.ProveedorRepository;
import tingeso.mingeso.pep1.repositories.ReporteRepository;
import tingeso.mingeso.pep1.repositories.SubirDataProcentajeRepository;
import tingeso.mingeso.pep1.repositories.SubirDataRepository;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static java.lang.Math.*;

@Service
public class CalcularReporte {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private SubirDataProcentajeRepository subirDataProcentajeRepository;


    public String calcularReporte( ArrayList<SubirDataEntity> arrayAcopio){

        while ( arrayAcopio.size() != 0){
            ReporteEntity reporteNuevo = new ReporteEntity();
            SubirDataEntity datoAcopio = new SubirDataEntity();
            datoAcopio = arrayAcopio.get(0);
            //conseguir fecha
            String fechaStrin = datoAcopio.getFecha();
            String[] partes = fechaStrin.split("/");
            Integer ano = Integer.parseInt(partes[0]);
            Integer mes = Integer.parseInt(partes[1]);
            Integer dia = Integer.parseInt(partes[2]);

            Integer klsLecheTotales = 0;
            Integer diasEnviaLecheQuincena = 0;
            Integer tarde = 0;
            Integer manana = 0;
            Integer diaAnterior = -1;

            ArrayList<SubirDataEntity> elementosAEliminar = new ArrayList<>();

            for(SubirDataEntity data : arrayAcopio){
                //optener dia
                String fechaStr = data.getFecha();
                String[] partes1 = fechaStr.split("/");
                Integer diaActual = Integer.parseInt(partes1[2]);


                if(datoAcopio.getProveedor().equals(data.getProveedor()) ){
                    if("M".equals(data.getTurno())){
                        manana++;
                    }
                    if("T".equals(data.getTurno())){
                        tarde++;
                    }
                    if(!diaAnterior.equals(diaActual))
                    {
                        diasEnviaLecheQuincena++;
                    }
                    klsLecheTotales = klsLecheTotales + Integer.valueOf(data.getKls_leche());
                    diaAnterior = diaActual;
                    elementosAEliminar.add(data);
                }
            }
            arrayAcopio.removeAll(elementosAEliminar);
            //completamos Reporte
            if(dia > 15){
                reporteNuevo.setQuincena(ano + "/" + mes + "/" + 2);
            }else {
                reporteNuevo.setQuincena(ano + "/" + mes + "/" + 1);
            }
            reporteNuevo.setTotalKilosLeche(klsLecheTotales);
            reporteNuevo.setNombreProveedor(proveedorRepository.findByCodigo(datoAcopio.getProveedor()).getNombre());
            reporteNuevo.setNumeroDiasEnvioLeche(diasEnviaLecheQuincena);
            if (diasEnviaLecheQuincena != 0){
                reporteNuevo.setPromedioDiarioKilosLeche((float) klsLecheTotales/diasEnviaLecheQuincena);

            }
            reporteNuevo.setCodigoProveedor(datoAcopio.getProveedor());


            // Porcentajes
            ArrayList<SubirDataPorcentajeEntity> listaPorcentajes = (ArrayList<SubirDataPorcentajeEntity>) subirDataProcentajeRepository.findAll();
            SubirDataPorcentajeEntity porcentajes = new SubirDataPorcentajeEntity();
            Float porcentajeGrasa = null;
            Float porcentajeST = null;
            for(SubirDataPorcentajeEntity por : listaPorcentajes){

                Float codigoPor = Float.parseFloat(por.getCodigoProveedor()) ;
                Float codigoComparar = Float.parseFloat(datoAcopio.getProveedor());
                if(codigoPor.equals(codigoComparar)){
                    System.out.println(por);
                    System.out.println("por");
                    System.out.println("joooooooo");
                    porcentajeGrasa = Float.parseFloat(por.getPorcentajeGrasa());
                    porcentajeST = Float.parseFloat(por.getPorcentajeSolido());
                }
            }
            reporteNuevo.setPorcentajeGrasa(porcentajeGrasa);
            reporteNuevo.setPorcentajeSolidosTotales(porcentajeST);

            //Pago por leche
            ProveedorEntity proveedor = new ProveedorEntity();
            proveedor = proveedorRepository.findByCodigo(datoAcopio.getProveedor());

            Float pagoPorLeche=0.0F;
            if(proveedor.getCategoria().equals("A")){
                pagoPorLeche = (float) (700* klsLecheTotales);
                reporteNuevo.setPagoPorLeche(pagoPorLeche);
            }
            if(proveedor.getCategoria().equals("B")){
                pagoPorLeche = (float) (550* klsLecheTotales);
                reporteNuevo.setPagoPorLeche(pagoPorLeche);
            }
            if(proveedor.getCategoria().equals("C")){
                pagoPorLeche = (float) (400* klsLecheTotales);
                reporteNuevo.setPagoPorLeche(pagoPorLeche);
            }
            if(proveedor.getCategoria().equals("D")){
                pagoPorLeche = (float) (200* klsLecheTotales);
                reporteNuevo.setPagoPorLeche(pagoPorLeche);
            }
            // Pago por Grasa
            Float pagoPorGrasa = 0.0F;
            if(porcentajeGrasa < 21.0000F){
                pagoPorGrasa = porcentajeGrasa * 30;
                reporteNuevo.setPagoPorGrasa(pagoPorGrasa);
            } else if (porcentajeGrasa < 45.00000F) {
                pagoPorGrasa = porcentajeGrasa * 80;
                reporteNuevo.setPagoPorGrasa(pagoPorGrasa);

            }else{
                pagoPorGrasa = porcentajeGrasa * 120;
                reporteNuevo.setPagoPorGrasa(pagoPorGrasa);
            }
            // Pago por ST
            Float pagoPorST=0.0F;
            if(porcentajeST < 8 ){
                pagoPorST = porcentajeST * -130;
                reporteNuevo.setPagoPorSolidosTotales(pagoPorST);
            } else if (porcentajeST < 19) {
                pagoPorST = porcentajeST * -90;
                reporteNuevo.setPagoPorSolidosTotales(pagoPorST);
            } else if (porcentajeST < 36) {
                pagoPorST = porcentajeST * 95;
                reporteNuevo.setPagoPorSolidosTotales(pagoPorST);
            }else {
                pagoPorST = porcentajeST * 150;
                reporteNuevo.setPagoPorSolidosTotales(pagoPorST);
            }
            // Bonificacion frecuencia
            int restaDias = tarde - manana;
            Float pagoBonifica = 0.0F;
            if (Math.abs(restaDias) > 10 ) {
                pagoBonifica = (float) (pagoPorLeche * 0.2);
                reporteNuevo.setBonificacionPorFrecuencia(pagoBonifica);
            } else if ( manana > 10) {
                pagoBonifica = (float) (pagoPorLeche * 0.12);
                reporteNuevo.setBonificacionPorFrecuencia(pagoBonifica);

            } else if ( tarde > 10) {
                pagoBonifica = (float) (pagoPorLeche * 0.08);
                reporteNuevo.setBonificacionPorFrecuencia(pagoBonifica);

            }else{
                reporteNuevo.setBonificacionPorFrecuencia(pagoBonifica);
            }
            // Pago acopio leche

            float pagoTotal;
            pagoTotal = pagoPorLeche + pagoPorGrasa + pagoPorST + pagoBonifica;
            reporteNuevo.setPagoTotal(pagoTotal);
            // Retencion
            double retencion = 0;

            if(proveedor.getRetencion().equals("Si") && pagoTotal > 950000){

                retencion = pagoTotal * 0.13;
                reporteNuevo.setMontoRetencion((float) retencion);
            }else {
                reporteNuevo.setMontoRetencion(0.0F);
            }
            //montoTotal
            reporteNuevo.setMontoFinal((float) (pagoTotal - retencion));


            reporteRepository.save(reporteNuevo);
        }
        return "0";
    }

}

