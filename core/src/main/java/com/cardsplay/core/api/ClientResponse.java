package com.cardsplay.core.api;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.cardsplay.core.exception.ResponseCode;


public class ClientResponse<T> {
 
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
        return new ClientResponse<T>(ResponseCode.SUCCESS_CODE, respDate);
    }
 
    public static <T> ClientResponse<T> respFail(String code, String msg) {
        return new ClientResponse<T>(ResponseCode.FIAL_CODE, code, msg);
    }
 
    public static <T> ClientResponse<T> respLoginFail() {
        return new ClientResponse<T>(ResponseCode.NOT_LOGIN_CODE);
    }
 
    public static <T> ClientResponse<T> respRealNameFail(T respDate) {
        return new ClientResponse<T>(ResponseCode.NOT_REALNAME_CODE, respDate);
    }
 

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
