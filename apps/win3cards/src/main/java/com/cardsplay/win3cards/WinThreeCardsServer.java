package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.access.api.CardsPlayNodeListener;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Room;
import com.cardsplay.core.models.RoomId;
import com.cardsplay.core.models.Table;
import com.cardsplay.core.models.TableId;
import com.cardsplay.core.models.TokenType;
import com.cardsplay.core.models.TokenWallet;


import java.lang.management.PlatformLoggingMXBean;
import java.util.Map;
import java.util.UUID;

import static com.cardsplay.core.impl.RoomManager.roomCapacity;
import static com.cardsplay.win3cards.Main.serviceMap;

public class WinThreeCardsServer {
    RoomService roomService = (RoomService) serviceMap.get(RoomService.class);
    TableService tableService = (TableService) serviceMap.get(TableService.class);
    PlayerService playerService = (PlayerService) serviceMap.get(PlayerService.class);
    private static int playersIntaleCapacity = 5;
    public WinThreeCardsServer(){

    }

    public void init(){
        // init Room
        RoomId roomId = new RoomId(UUID.randomUUID(), 1, "≤‚ ‘");
        Room room = new Room(roomId, roomCapacity, DealType.WinThreeCards);
        roomService.addRoom(room);

        // init Table
        for(int i=1; i<250; i++){
            TableId tableId = new TableId(UUID.randomUUID(), i);
            Dealer dealer = new WinThreeCardsDealer();
            Table table = new Table(tableId, dealer, playersIntaleCapacity);
            tableService.addTable(table);
            roomService.addTableToRoom(roomId, tableId);
        }

        // add listener
        CardsPlayController controller = (CardsPlayController) serviceMap.get(CardsPlayController.class);
        controller.addNodeListener(new playerInternalHadler());

    }
    public class playerInternalHadler implements CardsPlayNodeListener{

        @Override
        public void nodeAdded(CardsPlayNodeId nodeId) {
            PlayerId playerId = new PlayerId(nodeId.nodeId());
            TokenWallet tokenWallet = new TokenWallet(playerId, TokenType.EOS);
            Player player = new Player(playerId, tokenWallet);
            playerService.playerOnline(player);
        }

        @Override
        public void nodeRemoved(CardsPlayNodeId nodeId) {
            PlayerId playerId = new PlayerId(nodeId.nodeId());
            Player player = playerService.getPlayer(playerId);
            playerService.playerOffline(player);
        }
    }
}
