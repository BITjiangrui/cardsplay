package com.cardsplay.core.models;

public enum PlayerState {
	/*
	 * uncheck:��δ��ע  ÿһ�غ��м�״̬
	 * check:����ע ÿһ�غ��м�״̬
	 * discard:���ƣ�ÿһ�ƾ���̬
	 * dealed:�ѷ���
	 * undeal:δ����
	 * unavaiable:������״̬
	 * waiting:
	 * */
	Online, Offline, Uncheck, Check, Discard, Dealed, Undeal, Waiting, Ready, UndoReady;
}
