package com.cardsplay.access.api;

import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.api.LifeCycleService;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.TableId;

// represent the server side of the cards play server
public interface CardsPlayServerService extends LifeCycleService{

    ClientResponse getRoomsInfo();

    ClientResponse getRoomInfo(RoomId roomId);

    ClientResponse joinRoom(CardsPlayNodeId nodeId, RoomId roomId);

    ClientResponse getTableInfo(TableId tableId);

    ClientResponse joinTable(CardsPlayNodeId nodeId, TableId tableId);

    ClientResponse exitRoom(CardsPlayNodeId nodeId, RoomId roomId);

    ClientResponse exitTable(CardsPlayNodeId nodeId, TableId tableId);

    ClientResponse beReady(CardsPlayNodeId nodeId, TableId tableId);

    ClientResponse undoReady(CardsPlayNodeId nodeId, TableId tableId);

}
