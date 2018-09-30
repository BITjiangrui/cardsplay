package com.cardsplay.core.api;

import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

public interface TableService extends LifeCycleService{
	boolean joinTable(TableId table, PlayerId player);
	
	boolean quitTable(TableId table, PlayerId player);

	Iterable<Table>  getTables();
	
	Table getTable(TableId tableId);
	
	void addTable(Table table);
	
	void removeTable(TableId tableId);
	
	Table scheduleTable();

}
