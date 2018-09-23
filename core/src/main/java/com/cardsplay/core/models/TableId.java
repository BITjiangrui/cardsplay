package com.cardsplay.core.models;

import java.util.UUID;

public class TableId {
	public final UUID tableId;
	
	public final int tableNo;
	
	public TableId(UUID tableId, int tableNo) {
		this.tableNo = tableNo;
		this.tableId = tableId;
	}
	
	public UUID getTableId() {
		return tableId;
	}
	
	public int gettableNo() {
		return tableNo;
	}
}
