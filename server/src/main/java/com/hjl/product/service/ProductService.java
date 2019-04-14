package com.hjl.product.service;

import com.hjl.product.common.DecresseStockInput;
import com.hjl.product.common.ProductInfoOutput;
import com.hjl.product.dataobject.ProductInfo;

import java.util.List;

/**
 * @ClassName ProductService
 * @AUthor hanjialiang
 * @Date 2019-04-10 12:27
 * @Version 1.0
 * @Description
 */
public interface ProductService {

    List<ProductInfo> finUpAll();

    List<ProductInfoOutput> findProductList(List<String> productInfoIds);
//    扣库存
    void descreaseStock(List<DecresseStockInput> cartDTOList);
}
