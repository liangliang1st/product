package com.hjl.product.common;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ProductInfoOutput
 * @AUthor hanjialiang
 * @Date 2019-04-11 17:07
 * @Version 1.0
 * @Description
 */
@Data
public class ProductInfoOutput {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
    private Integer productStatus;

}
