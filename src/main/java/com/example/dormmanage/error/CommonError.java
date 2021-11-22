package com.example.dormmanage.error;

/**
 * @author LL
 * @date 2021/11/16 15:28
 */
public interface CommonError {
    /**
     * 获取错误信息
     * @return
     */
    String getErrMsg();

    /**
     * 获取错误编号
     * @return
     */
    Integer getErrCode();

    /**
     * 设置错误信息
     * @param errMsg
     * @return
     */
    CommonError setErrMsg(String errMsg);
}
