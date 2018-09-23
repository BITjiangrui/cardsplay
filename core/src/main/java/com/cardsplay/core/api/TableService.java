package com.cardsplay.core.api;

import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

public interface TableService {
	boolean joinTable(TableId table, Player player);
	
	boolean quitTable(TableId table, Player player);

	Iterable<Table>  getTables();
	
	Table getTable(TableId tableId);
	
	void addTable(Table table);
	
	void removeTable(TableId tableId);
	
	Table scheduleTable();

}
