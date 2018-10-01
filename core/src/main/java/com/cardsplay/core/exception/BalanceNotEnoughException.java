package com.cardsplay.core.exception;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * @ClassName BalanceNotEnoughException
 * @Description �����쳣
 * @author jiangrui
 * @version 1.0.0
 */
public class BalanceNotEnoughException extends ServiceException {
    private static final long serialVersionUID = -647562559025214578L;
    
    // ʵ�����
    private int balance;
    // ������
    private int wanted;

    public BalanceNotEnoughException() {
            super();
    }

    public BalanceNotEnoughException(String code, String msg, int balance, int wanted) {
            super(code, msg);
            this.balance = balance;
            this.wanted = wanted;
    }

    public int getBalance() {
            return balance;
    }

    public void setBalance(int  balance) {
            this.balance = balance;
    }

    public int getWanted() {
        return wanted;
    }

    public void setWanted(int  balance) {
        this.wanted = balance;
    }
    @Override   
    public String toString() {
        return toStringHelper(this).add("balance", balance).add("wanted", wanted).toString();    
    }
}
