package com.cardsplay.core.impl;

import com.cardsplay.core.api.Event;
import com.cardsplay.core.api.EventListener;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.TableEvent;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.exception.ServiceException;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TableStatus;
import com.cardsplay.util.ResponseCode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


public class TableManager implements TableService {

    public  final static Logger log = LoggerFactory
            .getLogger(TableManager.class);
    ServiceRegistry serviceMap = ServiceRegistry.getInstance();
    PlayerService playerService = (PlayerService) serviceMap.getService(PlayerService.class);
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

    @Override
    public void setDealer(TableId tableId, Dealer dealer) {

        Table table = tableStore.get(tableId);
        if(table == null) {
            log.error("Table {}do not exist", tableId);
            throw new ServiceException(ResponseCode.badRequest, "桌号不存在");
        }
        dealer.setTableId(tableId);
        table.setDealer(dealer);

    }

    @Override
    public Boolean isTableReady(TableId tableId) {
        Boolean ready = true;
        for(PlayerId playerId : tableStore.get(tableId).playerIds){
            if (playerService.getPlayer(playerId).state != PlayerState.Ready){
                ready = false;
            }
        }
        return ready;
    }

    public Table getTable(TableId tableId) {
        if (tableStore.containsKey(tableId)){
            return tableStore.get(tableId);
        } else {
            log.error("Table {} do not exist", tableId);
            throw new ServiceException(ResponseCode.badRequest, "牌桌不存在");
        }
    }

    @Override
    public TableStatus getTableState(TableId tableId) {
        return stateStore.get(tableId);
    }

    @Override
    public void setTableState(TableId tableId, TableStatus state) {
        stateStore.put(tableId, state);
    }

    public void addTable(Table table) {
            tableStore.put(table.tableId, table);
    }

    public void removeTable(TableId tableId) {
        if (tableStore.containsKey(tableId)){
            tableStore.remove(tableId);
        } else{
            log.error("Table {} do not exist", tableId);
            throw new ServiceException(ResponseCode.badRequest, "牌桌不存在");
        }
    }

    public void scheduleTable() {
        //TODO : add schedule algorithm based on max players in waiting state

    }

    @Override
    public Table getTableByPlayer(PlayerId playerId) throws ServiceException{
        for (Table table : tableStore.values()){
            if (table.playerIds.contains(playerId)){
                return table;
            }
        }
        log.error("Can not find Table for Player {} ", playerId);
        throw new ServiceException(ResponseCode.badRequest, "牌桌不存在");    }

    @Override
    public Dealer getDealer(TableId tableId) {
        return tableStore.get(tableId).getDealer();
    }

    @Override
    public void joinTable(TableId tableId, PlayerId playerId) throws ServiceException {
        Table table = tableStore.get(tableId);
        if(table == null){
            log.error("Player {} can not join because Table {}do not exist", playerId, tableId);
            throw new ServiceException(ResponseCode.badRequest, "桌号不存在");
        } else if(table.playerIds.size() >= table.capacity){
            log.error("Player {} can not join Table {} because the num of players reach the upper limit", playerId, tableId);
            throw new ServiceException(ResponseCode.denyAccess, "人数已经达到上限");
        }
        List players = Lists.newArrayList();
        players.addAll(table.playerIds);
        Table preTable = new Table(table.tableId, table.seq, table.capacity, players);
        table.playerIds.add(playerId);
        TableEvent event = new TableEvent(TableEvent.Type.TABLE_JOIN, preTable, table);
        post(event);

    }

    @Override
    public void quitTable(TableId tableId, PlayerId playerId) throws ServiceException {
        Table table = tableStore.get(tableId);
        if(table == null){
            log.error("Player {} can not join because Table {}do not exist", playerId, tableId);
            throw new ServiceException(ResponseCode.badRequest, "桌号不存在");
        }
        List players = Lists.newArrayList();
        players.addAll(table.playerIds);
        Table preTable = new Table(table.tableId, table.seq, table.capacity, players);
        table.playerIds.remove(playerId);
        TableEvent event = new TableEvent(TableEvent.Type.TABLE_QUIT, preTable, table);
        post(event);
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

    private void  post(Event playerEvenr){
        for(EventListener listener : eventListener){
            listener.event(playerEvenr);
        }
    }
    public static TableService getInstance(){
        return instance;
    }
}
