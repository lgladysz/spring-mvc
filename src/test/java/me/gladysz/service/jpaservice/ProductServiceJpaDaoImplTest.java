package me.gladysz.service.jpaservice;

import me.gladysz.model.Product;
import me.gladysz.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void shouldListAllProducts() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assertThat(products.size()).isEqualTo(5);
    }

    @Test
    public void shouldGetOneById() throws Exception {
        Product product = productService.getById(1L);

        assertThat(product).isNotNull();
        assertThat(product).hasFieldOrPropertyWithValue("description", "Product 1");
        assertThat(product).hasFieldOrPropertyWithValue("price", new BigDecimal("12.99"));
        assertThat(product).hasFieldOrPropertyWithValue("imageUrl", "http://lorempixel.com/640/480/technics/1/");
    }

    @Test
    @DirtiesContext
    public void shouldModifyExistingProduct() throws Exception {
        List<Product> productListBeforeModification = (List<Product>) productService.listAll();
        assertThat(productListBeforeModification.size()).isEqualTo(5);

        Product modifiedProduct = productListBeforeModification.get(0);
        assertThat(modifiedProduct).isNotNull();
        assertThat(modifiedProduct).hasFieldOrPropertyWithValue("description", "Product 1");
        assertThat(modifiedProduct).hasFieldOrPropertyWithValue("price", new BigDecimal("12.99"));
        assertThat(modifiedProduct).hasFieldOrPropertyWithValue("imageUrl", "http://lorempixel.com/640/480/technics/1/");
        assertThat(modifiedProduct).hasFieldOrPropertyWithValue("version", 0);

        modifiedProduct.setDescription("New Product Description");
        modifiedProduct.setPrice(new BigDecimal("13.88"));
        modifiedProduct.setImageUrl("http://lorempixel.com/640/480/technics/8/");

        productService.saveOrUpdate(modifiedProduct);

        List<Product> productListAfterModification = (List<Product>) productService.listAll();
        assertThat(productListAfterModification.size()).isEqualTo(5);

        Product returnedProduct = productListAfterModification.get(0);
        assertThat(returnedProduct).isNotNull();
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("description", "New Product Description");
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("price", new BigDecimal("13.88"));
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("imageUrl", "http://lorempixel.com/640/480/technics/8/");
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("version", 1);
    }

    @Test
    @DirtiesContext
    public void shouldAddNewProduct() throws Exception {
        List<Product> productListBeforeModification = (List<Product>) productService.listAll();
        assertThat(productListBeforeModification.size()).isEqualTo(5);

        Product productToAdd = new Product();

        productToAdd.setDescription("New Product Description");
        productToAdd.setPrice(new BigDecimal("13.88"));
        productToAdd.setImageUrl("http://lorempixel.com/640/480/technics/8/");

        productService.saveOrUpdate(productToAdd);

        List<Product> productListAfterModification = (List<Product>) productService.listAll();
        assertThat(productListAfterModification.size()).isEqualTo(6);

        Product returnedProduct = productListAfterModification.get(5);
        assertThat(returnedProduct).isNotNull();
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("description", "New Product Description");
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("price", new BigDecimal("13.88"));
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("imageUrl", "http://lorempixel.com/640/480/technics/8/");
        assertThat(returnedProduct).hasFieldOrPropertyWithValue("version", 0);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteOne() throws Exception {
        List<Product> productListBeforeDelete = (List<Product>) productService.listAll();
        assertThat(productListBeforeDelete.size()).isEqualTo(5);

        productService.delete(1L);

        List<Product> productListAfterDelete = (List<Product>) productService.listAll();
        assertThat(productListAfterDelete.size()).isEqualTo(4);
    }
}