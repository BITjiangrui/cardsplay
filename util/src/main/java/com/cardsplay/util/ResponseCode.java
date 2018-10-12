package com.cardsplay.util;

public class ResponseCode {

    public static final int SUCCESS_CODE = 0;
    /** �쳣��� */
    public static final int FIAL_CODE = -1;
    /** δ��¼ */
    public static final int NOT_LOGIN_CODE = -2;
    /** δ��Ȩ */
    public static final int NOT_REALNAME_CODE = -3;


    /*
    *  400 ���������֤��ͨ�����������
    * */
    public final static String badRequest = "400";

    /*
    *  401 ����δ��Ȩ
    * */
    public final static String unAuthorized = "401";

    /*
    *  403 �������ܾ�����
    * */
    public final static String denyAccess = "403";
}
