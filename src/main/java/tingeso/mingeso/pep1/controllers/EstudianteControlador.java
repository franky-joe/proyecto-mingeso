package tingeso.mingeso.pep1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tingeso.mingeso.pep1.repositories.EstudianteRepository;
import tingeso.mingeso.pep1.services.EstudianteServicio;

@Controller
public class EstudianteControlador {
    @Autowired
    private EstudianteServicio servicio;

    @GetMapping({"/Estudiantes", "/jojojo"})
    public String listarEstudiantes(Model modelo){
        modelo.addAttribute("estudiantes",servicio.listarTodosLosEstudiantes());
        return "estudiantes"; // nos retorna al archivo estudiantes .html
    }
}
