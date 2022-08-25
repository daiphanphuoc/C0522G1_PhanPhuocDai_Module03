package repository.impl;

import model.Product;
import repository.IProductRepository;

import java.util.*;

public class ProductRepository implements IProductRepository<Product> {
    //private static List<Product> products=new ArrayList<>();
    private static Map<String, Product> products = new TreeMap<>();

    static {
        products.put("1111", new Product("1111", "Trung Thuyên", 1000, "chuyên gia đâm thọt", "code gym"));
        products.put("1112", new Product("1112", "Thủy Tiên", 1000, "chuyên gia thời trang", "code gym"));
        products.put("1113", new Product("1113", "Bùi Hùng", 1000, "giáo sư ngôn từ", "code gym"));
        products.put("1114", new Product("1114", "Tấn Huân", 1000, "Chuyên gia khảo cổ", "code gym"));
        products.put("1115", new Product("1115", "Đại Lội", 1000, "chuyên gia khoe vợ", "code gym"));
    }

    @Override
    public List<Product> findByAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void add(Product element) {
        products.put(element.getId(), element);
    }

    @Override
    public void edit(String id, Product element) {
        products.put(id, element);
    }


    @Override
    public void delete(String id) {
        products.remove(id);
    }

    public Product findId(String id) {
        Set<String> productSet = products.keySet();
        for (String key : productSet) {
            if (products.get(key).getId().equals(id)) {
                return products.get(key);
            }
        }
        return null;
    }
}
