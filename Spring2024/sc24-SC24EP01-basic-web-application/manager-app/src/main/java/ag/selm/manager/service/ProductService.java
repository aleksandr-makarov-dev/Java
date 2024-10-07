package ag.selm.manager.service;


import ag.selm.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProduct();

    Product createProduct(String title, String details);

    Optional<Product> findProductById(Integer id);

    void updateProduct(Integer id, String title, String details);

    void deleteProduct(Integer id);
}
