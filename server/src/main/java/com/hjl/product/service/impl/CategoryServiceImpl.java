package com.hjl.product.service.impl;

import com.hjl.product.dataobject.ProductCategory;
import com.hjl.product.repository.ProductCategoryRepository;
import com.hjl.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryServiceImpl
 * @AUthor hanjialiang
 * @Date 2019-04-10 13:35
 * @Version 1.0
 * @Description
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public List<ProductCategory> findByCategoryTypeUpIn(List<Integer> type) {
        return productCategoryRepository.findByCategoryTypeIn(type);
    }
}
