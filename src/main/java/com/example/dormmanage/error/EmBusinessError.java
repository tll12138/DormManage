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
    USER_NOT_EXIST(10004,"用户不存在"),
    PASSWORD_NOT_TRUE(10005,"密码不正确"),
    CODE_NOT_COMPLETE(10006,"稍等，代码还未完成"),

    /**
     * 20000开头，用户错误
     */
    STUDENT_NOT_EXIST(20001,"该学生不存在"),
    STUDENTS_SELECT_NOT_SUCCESS(20002,"所有学生信息查找失败"),
    STUDENT_EXIST(20003,"该学生已存在"),
    DORM_MANAGER_NOT_EXIST(20004,"该寝室管理员不存在"),
    DORM_MANAGER_IS_EXIST(20005,"该寝室管理员已存在"),
    LOGISTICS_MANAGER_IS_EXIST(20006,"该后勤管理员已存在"),
    LOGISTICS_MANAGER_NOT_EXIST(20007,"该后勤管理员不存在"),
    SERVICE_MAN_NOT_EXIST(20008,"该维修员不存在"),
    SERVICE_MAN_IS_EXIST(20009,"该维修员已存在"),

    /**
     * 30000开头，数据库错误
     */
    DATABASE_NOT_SUCCESS(30001,"数据库数据不正确"),

    /**
     * 40000开头，楼栋、寝室错误
     */
    BUILDING_IS_EXIST(40001,"该楼栋已存在"),
    BUILD_ID_NOT_EXIST(40002,"该楼栋ID不存在，请重输"),
    DORM_IS_EXIST(40003,"寝室已存在"),
    DORM_NOT_EXIST(40004,"寝室不存在"),
    BUILDING_NOT_EXIST(40005,"该楼栋不存在"),
    BED_IS_HAVE(40006,"该床位已有人"),
    BED_NOT_HAVE(40007,"该床位没有人"),

    /**
     * 50000开头，报修错误
     */
    REPAIR_ALREADY_FINISH(50001,"报修已完成")
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
