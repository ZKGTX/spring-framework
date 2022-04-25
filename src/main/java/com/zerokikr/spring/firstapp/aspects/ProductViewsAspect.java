package com.zerokikr.spring.firstapp.aspects;


import com.zerokikr.spring.firstapp.controllers.ProductsController;
import com.zerokikr.spring.firstapp.entities.Product;
import com.zerokikr.spring.firstapp.services.ProductsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductViewsAspect {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @After("execution(public String com.zerokikr.spring.firstapp.controllers.ProductsController.showOneProduct(..))")
    public void increaseProductViews (JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Product product = productsService.getById((Long) args[1]);
        productsService.incrementProductViews(product);

    }
}
