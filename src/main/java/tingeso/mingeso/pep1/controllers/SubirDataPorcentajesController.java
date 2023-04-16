package tingeso.mingeso.pep1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import tingeso.mingeso.pep1.services.SubirDataPorcentajeService;
import tingeso.mingeso.pep1.services.SubirDataService;

import java.util.ArrayList;

@Controller
@RequestMapping
public class SubirDataPorcentajesController {
    @Autowired
    private SubirDataPorcentajeService subirData;

    @GetMapping("/fileUploadPorcentaje")
    public String main() {
        return "fileUploadPorcentaje";
    }
    @PostMapping("/fileUploadPorcentaje")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        subirData.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        String nombreArchivo = file.getOriginalFilename();
        subirData.leerCsv(nombreArchivo);
        return "redirect:/fileUploadPorcentaje";
    }

    @GetMapping("/fileInformationPorcentaje")
    public String listar(Model model) {
        ArrayList<SubirDataPorcentajeEntity> datasPorcentaje = subirData.obtenerData();
        model.addAttribute("datasPorcentaje", datasPorcentaje);
        return "fileInformationPorcentaje";
    }
}
