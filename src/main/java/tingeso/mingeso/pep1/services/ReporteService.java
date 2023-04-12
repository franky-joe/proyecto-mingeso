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

import java.util.ArrayList;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;
    public ArrayList<ReporteEntity> obtenerData(){
        return (ArrayList<ReporteEntity>) reporteRepository.findAll();
    }

    /*
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private SubirDataService subirDataService;
    @Autowired
    private SubirDataPorcentajeService subirDataPorcentajeService;

    private ArrayList<ProveedorEntity> listaProveedores = proveedorService.obtenerProveedores();
    private ArrayList<SubirDataEntity> listaData = subirDataService.obtenerData();
    private ArrayList<SubirDataPorcentajeEntity> listaDataPorcentajes = subirDataPorcentajeService.obtenerData();
    */

}
