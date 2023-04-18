package tingeso.mingeso.pep1.services;

import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.repositories.SubirDataRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class SubirDataService {

    @Autowired
    private SubirDataRepository dataRepository;

    private final Logger logg = LoggerFactory.getLogger(SubirDataService.class);

    public ArrayList<SubirDataEntity> obtenerData(){
        return (ArrayList<SubirDataEntity>) dataRepository.findAll();
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
    public ArrayList<SubirDataEntity> leerCsv(String direccion){
        ArrayList<SubirDataEntity> arregloAcopio = new ArrayList<>();

        String texto = "";
        BufferedReader bf = null;
        //dataRepository.deleteAll();
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
                    SubirDataEntity acopio = new SubirDataEntity();
                    acopio.setFecha(bfRead.split(";")[0]);
                    acopio.setTurno(bfRead.split(";")[1]);
                    acopio.setProveedor(bfRead.split(";")[2]);
                    acopio.setKls_leche(bfRead.split(";")[3]);
                    arregloAcopio.add(acopio);
                    guardarData(acopio);
                    //guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], bfRead.split(";")[3]);
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
        return arregloAcopio;
    }

    public void guardarData(SubirDataEntity data){
        dataRepository.save(data);
    }

    public void guardarDataDB(String fecha, String turno, String proveedor, String kls_leche){
         SubirDataEntity newData = new SubirDataEntity();
         newData.setFecha(fecha);
         newData.setTurno(turno);
         newData.setProveedor(proveedor);
         newData.setKls_leche(kls_leche);
         guardarData(newData);
    }
    public void eliminarData(ArrayList<SubirDataEntity> datas){
        dataRepository.deleteAll(datas);
    }
}
