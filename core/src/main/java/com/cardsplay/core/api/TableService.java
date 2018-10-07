package com.cardsplay.core.api;

import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;

public interface TableService extends LifeCycleService, EventRegistryService{
	void joinTable(TableId table, PlayerId player);
	
	void quitTable(TableId table, PlayerId player);

	Iterable<Table>  getTables();

	void setDealer(TableId tableId, Dealer dealer);

	Boolean isTableReady(TableId tableId);

	Table getTable(TableId tableId);

	TableStatus getTableState(TableId tableId);

	void setTableState(TableId tableId, TableStatus state);


	void addTable(Table table);
	
	void removeTable(TableId tableId);
	
	void scheduleTable();

	Table getTableByPlayer(PlayerId playerId);

	Dealer getDealer(TableId tableId);
}
