package org.playcards.core.impl;

import java.util.Map;

import org.playcards.core.api.TableService;
import org.playcards.core.models.Player;
import org.playcards.core.models.Table;
import org.playcards.core.models.TableId;
import org.playcards.core.models.TableStatus;

public class TableManager implements TableService {

	Map<TableId, Table> tableStore;
	Map<TableId, TableStatus> stateStore;
	public boolean joinTable(TableId table, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean quitTable(TableId table, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Table> getTables() {
		// TODO Auto-generated method stub
		return null;
	}

	public Table getTable(TableId tableId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTable(Table table) {
		// TODO Auto-generated method stub

	}

	public void removeTable(TableId tableId) {
		// TODO Auto-generated method stub

	}

	public Table scheduleTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
