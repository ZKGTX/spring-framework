package com.zerokikr.spring.firstapp.services;

import com.zerokikr.spring.firstapp.entities.Product;
import com.zerokikr.spring.firstapp.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productsRepository.findAll();
    }

    public Product getById(Long id) {
        return productsRepository.findById(id).get();
    }

    public List<Product> getFilteredProducts (String keyword) {
        List<Product> fullList = (List<Product>) productsRepository.findAll();
        if (keyword == null) {
            return fullList;
        }
        return fullList.stream().filter(p -> p.getTitle().contains(keyword)).collect(Collectors.toList());
    }

    public Page<Product> getProductsWithPagingAndFiltering (Specification<Product> spec, Pageable pageable) {
        return productsRepository.findAll(spec, pageable);
    }

    public void addOrUpdate (Product product) {
        productsRepository.save(product);
    }

    public void incrementProductViews (Product product) {
        product.setViews(product.getViews() + 1);
        productsRepository.save(product);
    }

    public List<Product> getTop3ProductsList() {
        return productsRepository.getTop3Products();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }


}
