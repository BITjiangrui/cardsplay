package com.cardsplay.core.models;

import java.util.UUID;

public class TableId {
	public final UUID tableId;

	public TableId(UUID tableId) {
		this.tableId = tableId;
	}
	
	public UUID getTableId() {
		return tableId;
	}
}
