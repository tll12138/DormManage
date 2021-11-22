package com.example.dormmanage.response;

import lombok.Data;

/**
 * @author LL
 * @date 2021/11/16 15:34
 */
@Data
public class CommonReturn {

    /**
     * status:success或fail
     * data:返回的信息
     * count:数据条数
     */
    private String status;
    private Object data;
    private Integer count;

    public static CommonReturn create(Object data){
        return CommonReturn.create(data,"success");
    }
    public static CommonReturn create(Object data,String status){
        CommonReturn commonReturn = new CommonReturn();
        commonReturn.data=data;
        commonReturn.status=status;
        return commonReturn;
    }
    public static CommonReturn create(Object data,Integer count,String status){
        CommonReturn commonReturn = new CommonReturn();
        commonReturn.data=data;
        commonReturn.count=count;
        commonReturn.status=status;
        return commonReturn;
    }
}
