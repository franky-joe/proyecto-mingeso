package tingeso.mingeso.pep1.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import tingeso.mingeso.pep1.repositories.SubirDataProcentajeRepository;
import tingeso.mingeso.pep1.services.SubirDataPorcentajeService;

@SpringBootTest

public class SubirDataPorcentajeServiceTest {
    @Mock
    private SubirDataProcentajeRepository repository;

    @InjectMocks
    private SubirDataPorcentajeService service;

    public SubirDataPorcentajeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarDataDB() {
        // Datos de prueba
        String codigoProveedor = "123";
        String porcentajeGrasa = "10";
        String porcentajeSolido = "20";

        // Crear una nueva entidad
        SubirDataPorcentajeEntity newData = new SubirDataPorcentajeEntity();
        newData.setCodigoProveedor(codigoProveedor);
        newData.setPorcentajeGrasa(porcentajeGrasa);
        newData.setPorcentajeSolido(porcentajeSolido);

        // Simular el método save() del repositorio
        Mockito.when(repository.save(Mockito.any(SubirDataPorcentajeEntity.class))).thenReturn(newData);

        // Llamar al método a probar
        int result = service.guardarDataDB(codigoProveedor, porcentajeGrasa, porcentajeSolido);

        // Verificar si el método save() del repositorio fue llamado correctamente
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(SubirDataPorcentajeEntity.class));

        // Verificar el resultado
        Assertions.assertEquals(0, result);
    }
}