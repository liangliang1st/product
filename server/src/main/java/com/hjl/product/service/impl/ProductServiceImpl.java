package com.hjl.product.service.impl;

import com.hjl.product.common.DecresseStockInput;
import com.hjl.product.common.ProductInfoOutput;
import com.hjl.product.dataobject.ProductInfo;
import com.hjl.product.enums.ProductStatusEnum;
import com.hjl.product.enums.ResultEnum;
import com.hjl.product.exception.ProductException;
import com.hjl.product.repository.ProductCategoryRepository;
import com.hjl.product.repository.ProductInfoRepository;
import com.hjl.product.service.ProductService;
import com.hjl.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName ProductServiceImpl
 * @AUthor hanjialiang
 * @Date 2019-04-10 12:28
 * @Version 1.0
 * @Description
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductInfo> finUpAll() {
        return productInfoRepository.findAllByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoOutput> findProductList(List<String> productInfoIds) {
        return productInfoRepository.findByProductIdIn(productInfoIds).stream()
                .map(e->{
                    ProductInfoOutput output=new ProductInfoOutput();
                    BeanUtils.copyProperties(e,output);
                    return output;
                }).collect(Collectors.toList());
    }

    @Override
    public void descreaseStock(List<DecresseStockInput> decresseStockInputListtockInput) {
        List<ProductInfo> productInfoList=descreaseStockProcess(decresseStockInputListtockInput);
        //发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput output = new ProductInfoOutput();
            BeanUtils.copyProperties(e, output);
            return output;
        }).collect(Collectors.toList());
        ProductInfoOutput productInfoOutput = new ProductInfoOutput();
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));

        //储存到redis中


    }
    @Transactional
    public List<ProductInfo> descreaseStockProcess(List<DecresseStockInput> decresseStockInputListtockInput) {
        List<ProductInfo> productInfoList=new ArrayList<>();
        for (DecresseStockInput decresseStockInput:decresseStockInputListtockInput){
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decresseStockInput.getProductId());
            //判断商品存在
            if(!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            ProductInfo productInfo = productInfoOptional.get();
            //判断库存是否足够
            Integer result = productInfo.getProductStock() - decresseStockInput.getProductQuantity();
            if (result<0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }


}
