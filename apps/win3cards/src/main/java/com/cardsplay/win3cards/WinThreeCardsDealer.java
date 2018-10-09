package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.Rule;
import com.cardsplay.core.models.TableStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WinThreeCardsDealer extends Dealer {

    public final Rule rule;
    public List<Card> cards;

    ServiceRegistry serviceMap = ServiceRegistry.getInstance();
    RoomService roomService = (RoomService) serviceMap.getService(RoomService.class);
    CardsPlayController controller = (CardsPlayController) serviceMap.getService(CardsPlayController.class);
    TableService tableService = (TableService) serviceMap.getService(TableService.class);
    PlayerService playerService = (PlayerService) serviceMap.getService(PlayerService.class);

    public WinThreeCardsDealer(Rule rule) {
        super(DealType.WinThreeCards);
        this.rule = rule;
    }

    @Override
    public void startGamble() {
        for (PlayerId playerId : tableService.getTable(this.tableId).playerIds){
            this.playerCards.put(playerId, new ArrayList<Card>(3));
            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
            client.startGamble(roomService.getRoomByTable(this.tableId), this.tableId);
        }
        tableService.setTableState(tableId, TableStatus.Running);

        cards = generateCards();

        shuffle(cards);

        for(int i = 0;i < cards.size();i++){
            System.out.println("Cards" + i + "£º" + cards.get(i));
        }
    }

    @Override
    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
    
    @Override
    public int askStartLocation() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void askForBet(PlayerId player, int round) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Player calcWinner(List<Player> players) {
        // TODO Need to consider exception situation of all players offline
        return null;
    }

    @Override
    public void balance() {
        // TODO Auto-generated method stub
        
    }

    private List<Card> generateCards(){
        List<Card> cards = new ArrayList<Card> (52);
        for (int i= 1; i<=13; i++){
           switch (i){
               case 1:
                   cards.add(new Card("FA"));
                   cards.add(new Card("MA"));
                   cards.add(new Card("XA"));
                   cards.add(new Card("HA"));
                   break;
               case 10:
                   cards.add(new Card("FT"));
                   cards.add(new Card("MT"));
                   cards.add(new Card("XT"));
                   cards.add(new Card("HT"));
                   break;
               case 11:
                   cards.add(new Card("FJ"));
                   cards.add(new Card("MJ"));
                   cards.add(new Card("XJ"));
                   cards.add(new Card("HJ"));
                   break;
               case 12:
                   cards.add(new Card("FQ"));
                   cards.add(new Card("MQ"));
                   cards.add(new Card("XQ"));
                   cards.add(new Card("HQ"));
                   break;
               case 13:
                   cards.add(new Card("FK"));
                   cards.add(new Card("MK"));
                   cards.add(new Card("XK"));
                   cards.add(new Card("HK"));
                   break;
               default:
                   cards.add(new Card("F" + i));
                   cards.add(new Card("M" + i));
                   cards.add(new Card("X" + i));
                   cards.add(new Card("H" + i));
           }
        }
        return cards;
    }
}
