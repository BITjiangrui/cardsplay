package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class TableManager implements TableService {

    Map<TableId, Table> tableStore;
    Map<TableId, TableStatus> stateStore;

    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static TableService instance = new TableManager();

    private TableManager(){};
    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub

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
    public Table joinTable(TableId table, PlayerId player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean quitTable(TableId table, PlayerId player) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void addEventListener(EventListener listener) {
        if (!eventListener.contains(listener)) {
            this.eventListener.add(listener);
        }
    }

    @Override
    public void removeEventListener(EventListener listener) {
        this.eventListener.remove(listener);
    }

    public static TableService getInstance(){
        return instance;
    }
}
