import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tingeso.mingeso.pep1.entities.ReporteEntity;
import tingeso.mingeso.pep1.repositories.ReporteRepository;
import tingeso.mingeso.pep1.services.ReporteService;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarReporte() {
        // Mock de los datos de entrada
        ReporteEntity reporte = new ReporteEntity();

        // Llamada al método a probar
        int result = reporteService.guardarReporte(reporte);

        // Verificación de que se llamó al repositorio
        verify(reporteRepository).save(reporte);

        // Verificación del resultado
        assertEquals(0, result);
    }

    @Test
    public void testObtenerData() {
        // Simulación de datos de prueba
        List<ReporteEntity> reportes = new ArrayList<>();
        reportes.add(new ReporteEntity());
        reportes.add(new ReporteEntity());

        // Simulación de la respuesta del repositorio
        when(reporteRepository.findAll()).thenReturn(reportes);

        // Llamada al método a probar
        ArrayList<ReporteEntity> result = reporteService.obtenerData();

        // Verificación de que se llamó al repositorio
        verify(reporteRepository).findAll();

        // Verificación del resultado
        assertEquals(reportes, result);
    }
}