package com.hjl.product.utils;

import com.hjl.product.enums.ResultEnum;
import com.hjl.product.viewobject.ResultVO;

/**
 * @ClassName ResultVOUtil
 * @AUthor hanjialiang
 * @Date 2019-04-10 14:35
 * @Version 1.0
 * @Description
 */
public class ResultVOUtil {

    public static ResultVO success(Object o){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultEnum.SUCCESS.getMsg());
        resultVO.setData(o);
        return resultVO;
    }
}
