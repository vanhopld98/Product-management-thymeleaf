package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1, "IPhone X", 799, "red"));
        productList.add(new Product(2, "IPhone 11 Pro Max", 899, "blue"));
        productList.add(new Product(3, "IPhone 12 Pro Max", 999, "white"));
        productList.add(new Product(4, "IPhone 13 Pro Max", 1099, "gold"));
        productList.add(new Product(5, "IPhone 6s Plus", 699, "pink"));
        productList.add(new Product(6, "IPhone 6", 599, "yellow"));
    }

    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {
        int index = findByIdIndex(id);
        return productList.get(index);
    }

    @Override
    public void update(int id, Product product) {
        int index = findByIdIndex(id);
        productList.set(index, product);
    }

    @Override
    public void remove(int id) {
        int index = findByIdIndex(id);
        productList.remove(index);
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().contains(name)){
                products.add(productList.get(i));
            }
        }
        return products;
    }

    private int findByIdIndex(int id) {
        int index = -1;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
}
