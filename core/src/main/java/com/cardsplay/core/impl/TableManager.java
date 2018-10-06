package com.cardsplay.core.impl;

import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


public class TableManager implements TableService {

    public  final static Logger log = LoggerFactory
            .getLogger(TableManager.class);
    Map<TableId, Table> tableStore;
    Map<TableId, TableStatus> stateStore;

    protected Set<EventListener> eventListener = new CopyOnWriteArraySet<>();

    private static TableService instance = new TableManager();

    private TableManager(){
        tableStore = Maps.newConcurrentMap();
    };
    @Override
    public void activate() {
        log.info("Table Service Activated");
    }

    @Override
    public void deactivate() {
        tableStore.clear();
        log.info("Table Service Deactivated");

    }
    
    public Iterable<Table> getTables() {

        return tableStore.values();
    }

    public Table getTable(TableId tableId) {
        if (tableStore.containsKey(tableId)){
            return tableStore.get(tableId);
        } else {
            log.error("Table {} do not exist", tableId);
            throw new ServiceException(ResponseCode.badRequest, "����������");
        }
    }

    public void addTable(Table table) {
            tableStore.put(table.tableId, table);
    }

    public void removeTable(TableId tableId) {
        if (tableStore.containsKey(tableId)){
            tableStore.remove(tableId);
        } else{
            log.error("Table {} do not exist", tableId);
            throw new ServiceException(ResponseCode.badRequest, "����������");
        }
    }

    public void scheduleTable() {
        //TODO : add schedule algorithm based on max players in waiting state

    }

    @Override
    public void joinTable(TableId tableId, PlayerId playerId) throws ServiceException {
        Table table = tableStore.get(tableId);
        if(table == null){
            log.error("Player {} can not join because Table {}do not exist", playerId, tableId);
            throw new ServiceException(ResponseCode.badRequest, "���Ų�����");
        } else if(table.playerIds.size() >= table.capacity){
            log.error("Player {} can not join Table {} because the num of players reach the upper limit", playerId, tableId);
            throw new ServiceException(ResponseCode.denyAccess, "�����Ѿ��ﵽ����");
        }
        table.playerIds.add(playerId);
    }

    @Override
    public void quitTable(TableId tableId, PlayerId playerId) throws ServiceException {
        Table table = tableStore.get(tableId);
        if(table == null){
            log.error("Player {} can not join because Table {}do not exist", playerId, tableId);
            throw new ServiceException(ResponseCode.badRequest, "���Ų�����");
        }
        table.playerIds.remove(playerId);
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
