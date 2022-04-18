package com.zerokikr.spring.firstapp.controllers;


import com.zerokikr.spring.firstapp.entities.Product;
import com.zerokikr.spring.firstapp.repositories.specifications.ProductSpecs;
import com.zerokikr.spring.firstapp.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController  {

private ProductsService productsService;

    @Autowired              // внедрение ProductService через сеттер
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping               // localhost:8189/firstapp/products
    public String showProductsList(Model model,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                   @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {
        Product product = new Product();
        Specification<Product> spec = Specification.where(null);
        if (keyword != null) {
            spec = spec.and(ProductSpecs.titleContains(keyword));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEqual(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEqual(maxPrice));
        }
        model.addAttribute("products",
                productsService.getProductsWithPagingAndFiltering(spec, PageRequest.of(0, 100)).getContent());  // у ProductService запрашиваем список всех продуктов и пробрасывем в модель под названием "products";
        model.addAttribute("keyword", keyword);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        //  теперь можно работать со списком продуктов на фронтенд странице products.html
        return "products";
    }


    @GetMapping("/add")            // localhost:8189/firstapp/products/add
    public String showAddProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "edit-page";
    }

    @GetMapping("/show/{id}")    // localhost:8189/firstapp/products/id - аргумент id будет передан из строки запроса в метод showOneProduct
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/edit/{id}")       // localhost:8189/firstapp/products/id/edit - аргумент id метода edit() получаем из строки GET-запроса, после чего в модель помещаем объект класса Product с соответсвующим id
    public String showEditProductForm(Model model, @PathVariable("id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "edit-page";        // возвращаем на фронтенд страницу "edit-page", при этом в модели содержится объект класса Product
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("product") Product product) {   // модель содержит объект класса Product с полями, значения которых равны введенным в форму на фронтенде,
                                                                                                        // id - аргумент из строки запроса, который указывает на "бэкендный" объект класса Product, поля которого требуется обновить значениями из модели.
        productsService.addOrUpdate(product); //  передаем в метод id, который укажет на объект, поля которого нужно обновить, и product из модели, згачение полей которого будут использованы для обновления
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products";
    }




}
