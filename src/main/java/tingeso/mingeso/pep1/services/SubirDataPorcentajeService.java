package tingeso.mingeso.pep1.services;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import tingeso.mingeso.pep1.repositories.SubirDataProcentajeRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class SubirDataPorcentajeService {

    @Autowired
    private SubirDataProcentajeRepository repository;

    private final Logger logg = LoggerFactory.getLogger(SubirDataService.class);

    public ArrayList<SubirDataPorcentajeEntity> obtenerData(){
        return (ArrayList <SubirDataPorcentajeEntity>) repository.findAll();
    }
    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }
    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        //repository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB( bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
            File archivo = new File(direccion);

            if (archivo.delete()) {
                System.out.println("El archivo fue eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }
    public void guardarData(SubirDataPorcentajeEntity data){
        repository.save(data);
    }
    public int guardarDataDB(String codigoProveedor, String porcentajeGrasa, String porcentajeSolido ){
        SubirDataPorcentajeEntity newData = new SubirDataPorcentajeEntity();
        newData.setCodigoProveedor(codigoProveedor);
        newData.setPorcentajeGrasa(porcentajeGrasa);
        newData.setPorcentajeSolido(porcentajeSolido);
        guardarData(newData);
        System.out.println("Archivo guardado exitosamente");
        return 0;
    }


}
