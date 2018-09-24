package com.cardsplay.core.impl;

import java.util.Map;

import com.cardsplay.core.api.TableService;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;

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

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

    }

}
