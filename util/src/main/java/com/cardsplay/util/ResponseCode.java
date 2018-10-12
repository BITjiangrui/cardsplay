package com.cardsplay.util;

public class ResponseCode {

    public static final int SUCCESS_CODE = 0;
    /** 异常编号 */
    public static final int FIAL_CODE = -1;
    /** 未登录 */
    public static final int NOT_LOGIN_CODE = -2;
    /** 未授权 */
    public static final int NOT_REALNAME_CODE = -3;


    /*
    *  400 请求错误（验证不通过，输入错误）
    * */
    public final static String badRequest = "400";

    /*
    *  401 操作未授权
    * */
    public final static String unAuthorized = "401";

    /*
    *  403 服务器拒绝请求
    * */
    public final static String denyAccess = "403";
}
