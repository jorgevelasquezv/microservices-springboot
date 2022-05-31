package co.com.pragma.store.product;

import co.com.pragma.store.product.entity.Category;
import co.com.pragma.store.product.entity.Product;
import co.com.pragma.store.product.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class productRepositorieMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .createAt(new Date()).build();

        productRepository.save(product01);

        List<Product> found = productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(found.size()).isEqualTo(3);
    }
}
