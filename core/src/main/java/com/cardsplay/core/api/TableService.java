package com.cardsplay.core.api;

import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;

public interface TableService extends LifeCycleService, EventRegistryService{
	void joinTable(TableId table, PlayerId player);
	
	void quitTable(TableId table, PlayerId player);

	Iterable<Table>  getTables();

	void setDealer(TableId tableId, Dealer dealer);

	Table getTable(TableId tableId);
	
	void addTable(Table table);
	
	void removeTable(TableId tableId);
	
	void scheduleTable();

	Table getTableByPlayer(PlayerId playerId);
}
