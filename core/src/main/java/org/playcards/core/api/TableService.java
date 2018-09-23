package org.playcards.core.api;

import org.playcards.core.models.Player;
import org.playcards.core.models.Table;
import org.playcards.core.models.TableId;

public interface TableService {
	boolean joinTable(TableId table, Player player);
	
	boolean quitTable(TableId table, Player player);

	Iterable<Table>  getTables();
	
	Table getTable(TableId tableId);
	
	void addTable(Table table);
	
	void removeTable(TableId tableId);
	
	Table scheduleTable();

}
