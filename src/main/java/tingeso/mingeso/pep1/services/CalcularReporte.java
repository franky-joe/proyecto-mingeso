package tingeso.mingeso.pep1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.repositories.ProveedorRepository;
import tingeso.mingeso.pep1.repositories.ReporteRepository;
import tingeso.mingeso.pep1.repositories.SubirDataRepository;

import java.util.ArrayList;

@Service
public class CalcularReporte {

    @Autowired
    private ReporteRepository reporteRepository;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private SubirDataService acopioService;
    @Autowired
    private SubirDataPorcentajeService porcentajeService;
    @Autowired
    private SubirDataRepository subirDataRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

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


                if(datoAcopio.getProveedor() == data.getProveedor()){
                    if("M".equals(data.getTurno())){
                        manana++;
                    }
                    if("T".equals(data.getTurno())){
                        tarde++;
                    }
                    if(diaAnterior != diaActual){
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
            reporteRepository.save(reporteNuevo);

        }


        return "0";
    }
}

