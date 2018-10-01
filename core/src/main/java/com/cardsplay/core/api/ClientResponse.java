package com.cardsplay.core.api;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * @Description. �ͻ�����Ϣ��������
 * @author jiangrui
 * @version 1.0.0
 * @param <T>
 */
public class ClientResponse<T> {
 
    private static final int SUCCESS_CODE = 0;
    /** �쳣��� */
    private static final int FIAL_CODE = -1;
    /** δ��¼ */
    private static final int NOT_LOGIN_CODE = -2;
    /** δ��Ȩ */
    private static final int NOT_REALNAME_CODE = -3;
 
    private int resultCode;
 
    private Result resultMsg;
 
    private T resultData;
 
    public ClientResponse() {
    }
 
    public ClientResponse(int resultCode) {
        this.resultCode = resultCode;
    }
 
    public ClientResponse(int resultCode, String code, String msg) {
        this.resultCode = resultCode;
        this.resultMsg = new Result(code, msg);
    }
 
    public ClientResponse(int resultCode, T resultData) {
        this.resultCode = resultCode;
        this.resultData = resultData;
    }
 
    public int getResultCode() {
        return resultCode;
    }
 
    public Result getResultMsg() {
        return resultMsg;
    }
 
    public T getResultData() {
        return resultData;
    }
 
    @Override
    public String toString() {
        return toStringHelper(this).add("code", resultCode).add("result", resultMsg)
                .add("resultData", resultData).toString();    
    }
 
    public static <T> ClientResponse<T> respSuccess(T respDate) {
        return new ClientResponse<T>(SUCCESS_CODE, respDate);
    }
 
    public static <T> ClientResponse<T> respFail() {
        return new ClientResponse<T>(FIAL_CODE, "-1", "ϵͳ��ά�У����Ժ����ԡ�");
    }
 
    public static <T> ClientResponse<T> respFail(String code, String msg) {
        return new ClientResponse<T>(FIAL_CODE, code, msg);
    }
 
    public static <T> ClientResponse<T> respLoginFail() {
        return new ClientResponse<T>(NOT_LOGIN_CODE);
    }
 
    public static <T> ClientResponse<T> respRealNameFail(T respDate) {
        return new ClientResponse<T>(NOT_REALNAME_CODE, respDate);
    }
 
    /**
     * ���������
     * 
     */
    public class Result {
 
        private String code;
 
        private String msg;
 
        public Result() {
 
        }
 
        public Result(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
 
        public String getCode() {
            return code;
        }
 
        public void setCode(String code) {
            this.code = code;
        }
 
        public String getMsg() {
            return msg;
        }
 
        public void setMsg(String msg) {
            this.msg = msg;
        }
 
        @Override
        public String toString() {
            return toStringHelper(this).add("code", code).add("msg", msg).toString();    
        }
    }
}