package com.hjl.product.controller;


import com.hjl.product.common.DecresseStockInput;
import com.hjl.product.common.ProductInfoOutput;
import com.hjl.product.dataobject.ProductCategory;
import com.hjl.product.dataobject.ProductInfo;
import com.hjl.product.service.CategoryService;
import com.hjl.product.service.ProductService;
import com.hjl.product.utils.ResultVOUtil;
import com.hjl.product.viewobject.ProductInfoVO;
import com.hjl.product.viewobject.ProductVO;
import com.hjl.product.viewobject.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName ProductController
 * @AUthor hanjialiang
 * @Date 2019-04-10 13:53
 * @Version 1.0
 * @Description
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    /**
     * 1查询所有在架商品
     * 2、获取类目type列表
     * 3、查询类目
     * 4、构造数据
     */
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<ProductVO> list(){
        //1
        List<ProductInfo> productInfos = productService.finUpAll();
        //2
        List<Integer> categoryTypes = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        //3
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeUpIn(categoryTypes);
        //4
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:categoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for (ProductInfo productInfo:productInfos){
                ProductInfoVO productInfoVO=new ProductInfoVO();
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                     /*  productInfoVO.setProductName(productInfo.getProductName());
                productInfoVO.setProductPrice(productInfo.getProductPrice());
                productInfoVO.setProductDescription(productInfo.getProductDescription());
                productInfoVO.setProductIcon(productInfo.getProductIcon());
                productInfoVO.setProductId(productInfo.getProductId());*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }

            }
            productVO.setProductInfoVos(productInfoVOList);
            productVOList.add(productVO);

        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品类表（给订单服务用的）
     * @param productInfoIds
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> ListForOrder(@RequestBody List<String> productInfoIds){
        log.info("productInfoIds={}",productInfoIds);
        return productService.findProductList(productInfoIds);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecresseStockInput> cartDTOList){
        productService.descreaseStock(cartDTOList);
    }

}
