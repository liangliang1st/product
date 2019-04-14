package com.hjl.product.client;

import com.hjl.product.common.DecresseStockInput;
import com.hjl.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName ProductClient
 * @AUthor hanjialiang
 * @Date 2019-04-11 17:02
 * @Version 1.0
 * @Description
 */
@FeignClient(name = "product")
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecresseStockInput> decreaseStockInputList);


}
