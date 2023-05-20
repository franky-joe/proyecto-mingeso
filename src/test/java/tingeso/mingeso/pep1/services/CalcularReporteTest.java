package tingeso.mingeso.pep1.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tingeso.mingeso.pep1.entities.ProveedorEntity;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.entities.SubirDataEntity;
import tingeso.mingeso.pep1.entities.SubirDataPorcentajeEntity;
import tingeso.mingeso.pep1.repositories.ProveedorRepository;
import tingeso.mingeso.pep1.repositories.ReporteRepository;
import tingeso.mingeso.pep1.repositories.SubirDataProcentajeRepository;
import tingeso.mingeso.pep1.services.CalcularReporte;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
public class CalcularReporteTest {

    @Mock
    private ReporteRepository reporteRepository;

    @Mock
    private ProveedorRepository proveedorRepository;

    @Mock
    private SubirDataProcentajeRepository subirDataProcentajeRepository;

    @InjectMocks
    private CalcularReporte calcularReporte;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalcularReporte() {
        // Datos de prueba
        ArrayList<SubirDataEntity> arrayAcopio = new ArrayList<>();

        // Crear datos de prueba
        SubirDataEntity subirDataEntity1 = new SubirDataEntity();
        subirDataEntity1.setProveedor("123");
        subirDataEntity1.setFecha("2023/05/01");
        subirDataEntity1.setTurno("M");
        subirDataEntity1.setKls_leche("100");

        SubirDataEntity subirDataEntity2 = new SubirDataEntity();
        subirDataEntity2.setProveedor("123");
        subirDataEntity2.setFecha("2023/05/01");
        subirDataEntity2.setTurno("T");
        subirDataEntity2.setKls_leche("200");

        arrayAcopio.add(subirDataEntity1);
        arrayAcopio.add(subirDataEntity2);

        // Simular el método findByCodigo() del repositorio de proveedores
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setCodigo("123");
        proveedorEntity.setCategoria("A");
        proveedorEntity.setRetencion("No");
        Mockito.when(proveedorRepository.findByCodigo(Mockito.anyString())).thenReturn(proveedorEntity);

        // Simular el método findAll() del repositorio de porcentajes
        List<SubirDataPorcentajeEntity> porcentajes = new ArrayList<>();
        SubirDataPorcentajeEntity porcentajeEntity = new SubirDataPorcentajeEntity();
        porcentajeEntity.setCodigoProveedor("123");
        porcentajeEntity.setPorcentajeGrasa("20");
        porcentajeEntity.setPorcentajeSolido("10");
        porcentajes.add(porcentajeEntity);
        Mockito.when(subirDataProcentajeRepository.findAll()).thenReturn(porcentajes);

        // Llamar al método a probar
        String result = calcularReporte.calcularReporte(arrayAcopio);

        // Verificar si el método save() del repositorio de reportes fue llamado correctamente
        Mockito.verify(reporteRepository, Mockito.times(1)).save(Mockito.any(ReporteEntity.class));

        // Verificar el resultado
        Assertions.assertEquals("0", result);

        // Verificar si el método save() del repositorio de reportes fue llamado correctamente
        Mockito.verify(reporteRepository, Mockito.times(1)).save(Mockito.any(ReporteEntity.class));

        // Verificar el resultado
        Assertions.assertEquals("0", result);
    }
}