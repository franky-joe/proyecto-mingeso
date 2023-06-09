package tingeso.mingeso.pep1.controllers;

import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.services.CalcularReporte;
import tingeso.mingeso.pep1.services.SubirDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping
public class SubirDataController {

    @Autowired
    private SubirDataService subirData;
    @Autowired
    private CalcularReporte calcularReporte;

    @GetMapping("/fileUpload")
    public String main() {
        return "fileUpload";
    }

    @PostMapping("/fileUpload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        ArrayList<SubirDataEntity> arrayAcopio = new ArrayList<>();
        subirData.guardar(file);
        String nombreArchivo = file.getOriginalFilename();
        redirectAttributes.addFlashAttribute("mensaje", "¡Archivo cargado correctamente!");
        arrayAcopio =  subirData.leerCsv(nombreArchivo);

        for(SubirDataEntity data : arrayAcopio){
            System.out.println(data);
        }

        calcularReporte.calcularReporte(arrayAcopio);


        return "redirect:/fileUpload";
    }

    @GetMapping("/fileInformation")
    public String listar(Model model) {
        ArrayList<SubirDataEntity> datas = subirData.obtenerData();
        model.addAttribute("datas", datas);
        return "fileInformation";
    }
}
