package com.hjl.product.service;

import com.hjl.product.dataobject.ProductCategory;

import java.util.List;

/**
 * @ClassName CategoryService
 * @AUthor hanjialiang
 * @Date 2019-04-10 13:32
 * @Version 1.0
 * @Description
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeUpIn(List<Integer> type);

}
