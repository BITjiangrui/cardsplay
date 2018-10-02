package com.cardsplay.util;

public class ResponseCode {
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
