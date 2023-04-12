package tingeso.mingeso.pep1.controllers;

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
    private ReporteService reporteService;
    /*
    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<SubirDataEntity> datas = subirData.obtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
      @GetMapping("/fileUpload")
    public String main() {
        return "fileUpload";
    }

     */
    @GetMapping("/reporte")
    public String main() {
        return "reporte";
    }

    @GetMapping("/listarreporte")
    public String listar(Model model){
       ArrayList<ReporteEntity> dataReporte = reporteService.obtenerData();
       model.addAttribute("dataReporte", dataReporte);
       return "redirect:/reporte";
    }


}
