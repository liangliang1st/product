package com.hjl.product.repository;

import com.hjl.product.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductCategoryRepository
 * @AUthor hanjialiang
 * @Date 2019-04-10 12:15
 * @Version 1.0
 * @Description
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
