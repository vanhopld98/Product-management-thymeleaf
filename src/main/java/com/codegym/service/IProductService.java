package com.codegym.service;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Product findById(int id);

    void update(int id,Product product);

    void remove(int id);

    void save(Product product);

    List<Product> findByName(String name);
}
