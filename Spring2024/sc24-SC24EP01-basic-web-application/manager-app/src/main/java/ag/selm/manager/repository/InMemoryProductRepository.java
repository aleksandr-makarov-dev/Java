package ag.selm.manager.repository;

import ag.selm.manager.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    public InMemoryProductRepository() {
        IntStream
                .range(1, 4)
                .forEach(i -> products.add(
                                new Product(i, "Product %d".formatted(i), "Product description %d".formatted(i))
                        )
                );
    }

    @Override
    public List<Product> findAllProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product save(Product product) {
        product.setId(products
                .stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId)
                .orElse(0) + 1
        );

        this.products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return products
                .stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
