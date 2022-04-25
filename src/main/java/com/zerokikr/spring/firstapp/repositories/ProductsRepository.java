package com.zerokikr.spring.firstapp.repositories;

import com.zerokikr.spring.firstapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT * FROM products ORDER BY views DESC LIMIT 3", nativeQuery = true)
    public List<Product> getTop3Products();

    // PagingAndSortingRepository<Product, Long> позволит использовать пагинацию
    // JpaSpecificationExecutor<Product> позволит использовать спецификации (ProductSpecs)
    // CrudRepository <Product, Long> если пагинация и спецификации не требуются
}
