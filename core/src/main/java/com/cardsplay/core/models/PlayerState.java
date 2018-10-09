package com.cardsplay.core.models;

public enum PlayerState {
	/*
	 * Online:在线上
	 * Offline:离线的状态
	 * Ready:进入牌桌已准备
	 * Playing:游戏进行中
	 * Discard:弃牌状态
	 * */
	Online, Offline, Ready, UndoReady, Playing, Discard;
}
