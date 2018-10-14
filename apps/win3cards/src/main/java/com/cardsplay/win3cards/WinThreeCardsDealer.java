package com.cardsplay.win3cards;

import com.cardsplay.access.api.CardsPlayClientService;
import com.cardsplay.access.api.CardsPlayController;
import com.cardsplay.access.api.CardsPlayNodeId;
import com.cardsplay.core.api.CallBack;
import com.cardsplay.core.api.ClientResponse;
import com.cardsplay.core.api.PlayerService;
import com.cardsplay.core.api.RoomService;
import com.cardsplay.core.api.TableService;
import com.cardsplay.core.exception.ResponseCode;
import com.cardsplay.core.impl.ServiceRegistry;
import com.cardsplay.core.models.Bet;
import com.cardsplay.core.models.Card;
import com.cardsplay.core.models.DealType;
import com.cardsplay.core.models.Dealer;
import com.cardsplay.core.models.Player;
import com.cardsplay.core.models.PlayerId;
import com.cardsplay.core.models.PlayerState;
import com.cardsplay.core.models.Rule;
import com.cardsplay.core.models.TableStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WinThreeCardsDealer extends Dealer {

    public final static Logger log = LoggerFactory
            .getLogger(WinThreeCardsDealer.class);
    public final Rule rule;
    public List<Card> cards;
    protected ExecutorService executor =
            Executors.newSingleThreadExecutor();
    List<PlayerId> players;
    ServiceRegistry serviceMap;
    RoomService roomService;
    CardsPlayController controller;
    TableService tableService;
    PlayerService playerService;
    private Bet singleBet;

    public WinThreeCardsDealer(Rule rule) {
        super(DealType.WinThreeCards);
        this.rule = rule;
    }

    @Override
    public void init() {
        serviceMap = ServiceRegistry.getInstance();
        roomService = (RoomService) serviceMap.getService(RoomService.class);
        controller = (CardsPlayController) serviceMap.getService(CardsPlayController.class);
        tableService = (TableService) serviceMap.getService(TableService.class);
        playerService = (PlayerService) serviceMap.getService(PlayerService.class);
        
        
        round = 1;
        singleBet = rule.getSingleBet();
        players = tableService.getTable(this.tableId).playerIds;
        
        executor.submit(() -> {
            startGamble();
        });
    }

    private synchronized void startGamble() {

        for (PlayerId playerId : players) {
            this.playerCards.put(playerId, new ArrayList<Card>(3));
            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
            client.startGamble(roomService.getRoomByTable(this.tableId), this.tableId);
        }
        tableService.setTableState(tableId, TableStatus.Running);


        cards = generateCards();

        shuffle(cards);


        askStartLocation();

        for (int i = 1; i <= 3; i++) {
            for (PlayerId playerId : players) {
                playerCards.get(playerId).add(cards.get(i * players.indexOf(playerId)));
            }
            System.out.println("Cards" + i + ":" + cards.get(i));
        }


        for (; ; round++) {
            for (PlayerId playerId : players) {
                if (playerService.getPlayerState(playerId) != PlayerState.Playing) {
                    askForBet(playerId, round);
                    if (satisfyEnd()){
                        break;
                    }
                }
            }
            break;
        }

        exit();
    }


    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }


    private void askStartLocation() {
        Random random = new Random();
        PlayerId luckyDog = players.get(random.nextInt(5));
        Integer location = 1;
        for (PlayerId playerId : players) {
            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
            try {
                location = client.askForStartLocation(luckyDog).get(15, TimeUnit.SECONDS);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        swap(luckyDog, location - 1);
        // TODO: Confirm location to all players
    }


    private void askForBet(PlayerId player, int round) {
        double amount = singleBet.getAmount() * playerService.getPlayerState(player).getTimes();
        for (PlayerId playerId : players) {
            CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(playerId.playerId));
            if (!player.equals(playerId)) {
                client.askForBet(roomService.getRoomByTable(tableId), tableId, player, amount, round);
            }
        }
        CardsPlayClientService client = controller.getCardsPlayClient(new CardsPlayNodeId(player.playerId));
        ClientResponse response = null;
        try {
            response = client.askForBet(roomService.getRoomByTable(tableId), tableId,
                                        player, amount, round).get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            playerService.getPlayer(player).setState(PlayerState.Discard);
            // TODO: Add discard cards notification
        }
        if (response.getResultCode() == ResponseCode.SUCCESS_CODE) {
            if (response.getResultData().getClass().equals(Double.class)) {
                singleBet = new Bet((Double) response.getResultData()
                                            / playerService.getPlayerState(player).getTimes());
                // TODO: Add how many bets notification
            } else if(response.getResultData().getClass().equals(CallBack.class)){
                CallBack callBack = (CallBack) response.getResultData();
                switch (callBack.getMethod()){
                    case "compare":
                        break;
                    case "watchCards":
                        break;
                    default:
                        log.error("Unknown callback {} not supported yet.", callBack.getMethod());
                }
            }
        }
    }


    private Player calcWinner(List<Player> players) {
        // TODO Need to consider exception situation of all players offline
        return null;
    }


    private void balance() {

    }

    private void exit() {

    }

    private boolean satisfyEnd(){
        return  true;
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<Card>(52);
        for (int i = 1; i <= 13; i++) {
            switch (i) {
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

    private void swap(PlayerId playerId, int location) {
        int current = players.indexOf(playerId);
        if (location > current) {
            for (int i = 0; i < location - current; i++) {
                moveRight(players);
            }
        } else if (location < current) {
            for (int i = 0; i < current - location; i++) {
                moveLeft(players);
            }
        }
    }

    private void moveRight(List<PlayerId> array) {
        PlayerId temp;
        temp = array.get(array.size() - 1);
        for (int i = array.size() - 1; i > 0; i--) {
            array.set(i, array.get(i - 1));
        }
        array.set(0, temp);
    }

    private void moveLeft(List<PlayerId> array) {
        PlayerId temp;
        temp = array.get(0);
        for (int i = 0; i < array.size() - 1; i++) {
            array.set(i, array.get(i + 1));
        }
        array.set(array.size() - 1, temp);
    }
}
