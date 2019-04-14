package com.hjl.product.exception;

import com.hjl.product.enums.ResultEnum;

/**
 * @ClassName ProductException
 * @AUthor hanjialiang
 * @Date 2019-04-11 13:49
 * @Version 1.0
 * @Description
 */
public class ProductException extends RuntimeException {
    private Integer code;
    public ProductException(Integer code,String message){
        super(message);
        this.code=code;
    }

    public ProductException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }
}
