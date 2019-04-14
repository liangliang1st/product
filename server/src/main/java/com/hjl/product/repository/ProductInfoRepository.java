package com.hjl.product.repository;

import com.hjl.product.common.DecresseStockInput;
import com.hjl.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductInfoRepository
 * @AUthor hanjialiang
 * @Date 2019-04-10 09:31
 * @Version 1.0
 * @Description
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findAllByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productInfoIds);
}
