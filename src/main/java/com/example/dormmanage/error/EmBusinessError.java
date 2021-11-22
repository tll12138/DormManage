package com.example.dormmanage.error;

/**
 * @author LL
 * @date 2021/11/16 16:20
 */
public enum EmBusinessError implements CommonError{
    /**
     * 10000开头，通用错误
     */
    PARAMETER_NOT_LEGITIMATE(10001,"参数不正确"),
    USER_NOT_LOGIN(10002,"用户未登录"),
    FRONT_PARAMETER_NOT_LEGITIMATE(10003,"前端参数不正确"),

    /**
     * 20000开头，用户错误
     */
    STUDENT_NOT_EXIST(20001,"该学生不存在"),
    STUDENTS_SELECT_NOT_SUCCESS(20002,"所有学生信息查找失败"),
    STUDENT_EXIST(20003,"该学生已存在"),

    /**
     * 30000开头，数据库错误
     */
    DATABASE_NOT_SUCCESS(30001,"数据库数据不正确")
    ;

    private Integer errCode;
    private String errMsg;

    EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public Integer getErrCode() {
        return this.errCode;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
