package tingeso.mingeso.pep1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.mingeso.pep1.entities.EstudianteEntity;
import tingeso.mingeso.pep1.entities.ProveedorEntity;
import tingeso.mingeso.pep1.repositories.EstudianteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServicio {
    @Autowired
    private EstudianteRepository repositorio;

    public ArrayList<EstudianteEntity>listarTodosLosEstudiantes(){
        return (ArrayList<EstudianteEntity>) repositorio.findAll();
    }

}
