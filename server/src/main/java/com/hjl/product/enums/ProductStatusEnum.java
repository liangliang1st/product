package com.hjl.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ProductStatusEnum
 * @AUthor hanjialiang
 * @Date 2019-04-10 12:32
 * @Version 1.0
 * @Description 商品上下架状态
 */
@Getter
@AllArgsConstructor
public enum  ProductStatusEnum {

    UP(0,"在架"),DOWN(1,"下架"),
    ;

    private Integer code;
    private String message;
}
