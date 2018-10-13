package com.cardsplay.core.exception;

import static com.google.common.base.MoreObjects.toStringHelper;


public class ServiceException extends RuntimeException {
        private static final long serialVersionUID = -4742832112872227456L;
        private String code;
        private String msg;
        
        public ServiceException() {
                super();
        }
        public ServiceException(Throwable t) {
                super(t);
        }
        
        public ServiceException(String code, String msg) {
                super(code + '|' + msg);
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

