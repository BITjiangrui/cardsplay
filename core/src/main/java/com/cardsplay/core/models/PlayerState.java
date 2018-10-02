package com.cardsplay.core.models;

public enum PlayerState {
	/*
	 * uncheck:尚未下注  每一回合中间状态
	 * check:已下注 每一回合中间状态
	 * discard:弃牌，每一牌局终态
	 * dealed:已发牌
	 * undeal:未发牌
	 * unavaiable:不可用状态
	 * waiting:
	 * */
	Online, Offline, Uncheck, Check, Discard, Dealed, Undeal, Waiting, Ready, UndoReady;
}
