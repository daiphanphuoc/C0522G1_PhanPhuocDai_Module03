package service.impl;

import model.Product;
import repository.IProductRepository;
import repository.impl.ProductRepository;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductService implements IProductService<Product> {
    IProductRepository<Product> products = new ProductRepository();

    @Override
    public List<Product> searchByName(String name) {
        List<Product> temp = findByAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : temp) {
            if (product.getProductName().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public List<Product> findByAll() {
        return products.findByAll();
    }

    @Override
    public void add(Product element) {
        products.add(element);
    }

    @Override
    public void edit(String id, Product element) {
        delete(id);
        products.add(element);
    }

    @Override
    public void delete(String id) {
        products.delete(id);
    }

    public Product findId(String id) {
        return products.findId(id);
    }
}
