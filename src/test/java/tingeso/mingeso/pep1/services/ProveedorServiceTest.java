package tingeso.mingeso.pep1.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tingeso.mingeso.pep1.entities.ProveedorEntity;
import tingeso.mingeso.pep1.repositories.ProveedorRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProveedorServiceTest {
    @Mock
    private ProveedorRepository repo;
    @InjectMocks
    private ProveedorService service;
    private ProveedorEntity proveedor;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        proveedor = new ProveedorEntity();
        proveedor.setCategoria("A");
        proveedor.setNombre("Felipe");
        proveedor.setRetencion("Si");
        proveedor.setCodigo("1");
    }


    @Test
    void obtenerProveedores() {
        when(repo.findAll()).thenReturn(Collections.singletonList(proveedor));
        assertNotNull(repo.findAll());
    }
    @Test
    public void testObtenerCategoria() {
        // Mock de los datos de entrada
        String codigo = "123";
        String categoria = "Categoria 1";
        when(repo.findCategory(codigo)).thenReturn(categoria);
        String result = service.obtenerCategoria(codigo);
        verify(repo).findCategory(codigo);
        assertEquals(categoria, result);
    }
    @Test
    public void testFindByCodigo() {
        // Mock de los datos de entrada
        String codigo = "123";
        ProveedorEntity proveedor = new ProveedorEntity();
        proveedor.setCodigo(codigo);

        when(repo.findByCodigo(codigo)).thenReturn(proveedor);

        // Llamada al método a probar
        ProveedorEntity result = repo.findByCodigo(codigo);

        // Verificación de que se llamó al repositorio con el código correcto
        verify(repo).findByCodigo(codigo);

        assertEquals(proveedor, result);
    }

    @Test
    void findByCodigo() {
    }
}