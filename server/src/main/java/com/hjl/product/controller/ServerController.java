package com.hjl.product.controller;

import com.hjl.product.dataobject.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerController
 * @AUthor hanjialiang
 * @Date 2019-04-11 08:43
 * @Version 1.0
 * @Description
 */
@RestController
public class ServerController {

    @GetMapping("/msg")
    private String msg(){
        return "this is product msg";
    }
}
