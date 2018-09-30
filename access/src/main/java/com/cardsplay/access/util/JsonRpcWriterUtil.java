
package com.cardsplay.access.util;

import java.util.List;

import com.cardsplay.access.jsonrpc.JsonRpcRequest;


/**
 * RPC Methods request utility class.use to call client 's method
 */
public final class JsonRpcWriterUtil {

    /**
     * Constructs a JsonRpcWriterUtil object. Utility classes should not have a
     * public or default constructor, otherwise IDE will compile unsuccessfully.
     * This class should not be instantiated.
     */
    private JsonRpcWriterUtil() {
    }

    /**
     * Returns string of RPC request.
     * @param uuid id of request object
     * @param methodName method of request object
     * @param params params of request object
     * @return RPC Request String
     */
    private static String getRequestStr(String uuid, String methodName,
                                        List params) {
        JsonRpcRequest request;
        if (params != null) {
            request = new JsonRpcRequest(uuid, methodName, params);
        } else {
            request = new JsonRpcRequest(uuid, methodName);
        }
        String str = ObjectMapperUtil.convertToString(request);
        return str;
    }

    /**
     * notify client player join the table.
     * @param uuid id of join_game request
     * @param parms params of join_game request
     * @return exit_game Request String
     */
    public static String joinGameStr(String uuid, List parms) {
        String methodName = "join_game";
        return getRequestStr(uuid, methodName, parms);
    }

    /**
     * notify client player leave the table.
     * @param uuid id of exit_game request
     * @param parms params of exit_game request
     * @return exit_game Request String
     */
    public static String leaveGameStr(String uuid, List parms) {
        String methodName = "exit_game";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client the player is ready in the table.
     * @param uuid id of ready request
     * @param parms params of ready request
     * @return ready Request String
     */
    public static String readyStr(String uuid, List parms) {
        String methodName = "ready";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client player is undo ready in the table.
     * @param uuid id of undo_ready request
     * @param parms params of undo_ready request
     * @return undo_ready Request String
     */
    public static String undoReadyStr(String uuid, List parms) {
        String methodName = "undo_ready";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client player is the game is start in the table.
     * @param uuid id of start_game request
     * @param parms params of start_game request
     * @return start_game Request String
     */
    public static String startGameStr(String uuid, List parms) {
        String methodName = "start_game";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client player is the game is over in the table.
     * @param uuid id of game_over request
     * @param parms params of game_over request
     * @return game_over Request String
     */
    public static String gameOverStr(String uuid, List parms) {
        String methodName = "game_over";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * assign cards to client player.
     * @param uuid id of assign_cards request
     * @param parms params of assign_cards request
     * @return assign_cards Request String
     */
    public static String assignCardsStr(String uuid, List parms) {
        String methodName = "assign_cards";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client player who wins.
     * @param uuid id of calc_winner request
     * @param parms params of calc_winner request
     * @return calc_winner Request String
     */
    public static String calcWinnerStr(String uuid, List parms) {
        String methodName = "calc_winner";
        return getRequestStr(uuid, methodName, parms);
    }
    
    /**
     * notify client player who should get money.
     * @param uuid id of balance request
     * @param parms params of balance request
     * @return balance Request String
     */
    public static String balanceStr(String uuid, List parms) {
        String methodName = "balance";
        return getRequestStr(uuid, methodName, parms);
    }
    /**
     * Returns string of echo request.
     * @param uuid id of echo request
     * @return echo Request String
     */
    public static String echoStr(String uuid) {
        String methodName = "echo";
        return getRequestStr(uuid, methodName, null);
    }

}
