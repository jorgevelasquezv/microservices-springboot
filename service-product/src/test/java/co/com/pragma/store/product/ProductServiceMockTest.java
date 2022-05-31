package co.com.pragma.store.product;

import co.com.pragma.store.product.entity.Product;
import co.com.pragma.store.product.repository.ProductRepository;
import co.com.pragma.store.product.service.ProductService;
import co.com.pragma.store.product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1250.99"))
                .status("CREATED")
                .createAt(new Date())
                .build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer))
                .thenReturn(computer);
    }

    @Test
    public void whenValidGetID_thenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidaUpdateStock_thenReturnStock(){
        Product newStock = productService.updateStock(1l, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(18);
    }

}
