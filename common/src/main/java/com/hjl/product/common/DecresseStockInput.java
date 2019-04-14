package com.hjl.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName DecresseStockInput
 * @AUthor hanjialiang
 * @Date 2019-04-11 17:05
 * @Version 1.0
 * @Description
 */
@Data
@AllArgsConstructor
public class DecresseStockInput {
    private String productId;
    private Integer productQuantity;
}
