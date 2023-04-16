package tingeso.mingeso.pep1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.services.ReporteService;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ReportController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reporte")
    public String main() {
        return "reporte";
    }

    @GetMapping("/listarreporte")
    public String listar(Model model){
       ArrayList<ReporteEntity> dataReporte = reporteService.obtenerData();
       model.addAttribute("dataReporte", dataReporte);
       return "reporte";
    }


}
