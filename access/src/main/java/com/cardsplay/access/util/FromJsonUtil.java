
package com.cardsplay.access.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardsplay.access.exception.AbnormalJsonNodeException;
import com.cardsplay.access.exception.UnsupportedException;
import com.cardsplay.access.jsonrpc.JsonRpcResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonNode utility class. convert JsonNode into Object.
 */
public final class FromJsonUtil {

    private static final Logger log = LoggerFactory.getLogger(FromJsonUtil.class);

    /**
     * Constructs a FromJsonUtil object. Utility classes should not have a
     * public or default constructor, otherwise IDE will compile unsuccessfully.
     * This class should not be instantiated.
     */
    private FromJsonUtil() {
    }

    /**
     * Verify whether the jsonNode is normal.
     * @param jsonNode JsonNode
     * @param nodeStr the node name of JsonNode
     */
    private static void validateJsonNode(JsonNode jsonNode, String nodeStr) {
        if (!jsonNode.isObject() || !jsonNode.has(nodeStr)) {
            String message = "Abnormal DatabaseSchema JsonNode, it should contain " + nodeStr
                    + " node but was not found";
            throw new AbnormalJsonNodeException(message);
        }
    }


    /**
     * convert JsonNode into the returnType of methods in OvsdbRPC class.
     * @param resultJsonNode the result JsonNode
     * @param methodName the method name of methods in OvsdbRPC class
     * @param objectMapper ObjectMapper entity
     * @return Object
     * @throws UnsupportedException this is an unsupported exception
     */
    private static Object convertResultType(JsonNode resultJsonNode, String methodName,
                                            ObjectMapper objectMapper) {
        switch (methodName) {
        case "getSchema":
        case "monitor":
            return resultJsonNode;
        case "echo":
        case "listDbs":
            return objectMapper.convertValue(resultJsonNode, objectMapper.getTypeFactory()
                    .constructParametricType(List.class, String.class));
        case "transact":
            return objectMapper.convertValue(resultJsonNode, objectMapper.getTypeFactory()
                    .constructParametricType(List.class, JsonNode.class));
        default:
            throw new UnsupportedException("does not support this rpc method" + methodName);
        }
    }

    /**
     * convert JsonNode into the returnType of methods in OvsdbRPC class.
     * @param jsonNode the result JsonNode
     * @param methodName the method name of methods in OvsdbRPC class
     * @return Object
     */
    public static Object jsonResultParser(JsonNode jsonNode, String methodName) {
        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        JsonNode error = jsonNode.get("error");
        if (error != null && !error.isNull()) {
            log.error("jsonRpcResponse error : {}", error.toString());
        }
        JsonNode resultJsonNode = jsonNode.get("result");
        Object result = convertResultType(resultJsonNode, methodName, objectMapper);
        return result;
    }


    /**
     * Ovs send echo request to keep the heart, need we return echo result.
     * @param jsonNode the result JsonNode
     * @return JsonRpcResponse String
     */
    public static String getEchoRequestStr(JsonNode jsonNode) {
        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        String str = null;
        if (jsonNode.get("method").asText().equals("echo")) {
            JsonRpcResponse response = new JsonRpcResponse(jsonNode.get("id").asText());
            try {
                str = objectMapper.writeValueAsString(response);
            } catch (JsonProcessingException e) {
                log.error("JsonProcessingException while converting JsonNode into string: ", e);
            }
        }
        return str;
    }

    /**
     * Convert the List of Operation result into List of OperationResult .
     * @param input the List of JsonNode
     * @param operations the List of Operation
     * @return the List of OperationResult
     */
/*    public static List<OperationResult> jsonNodeToOperationResult(List<JsonNode> input,
                                                                  List<Operation> operations) {
        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper(false);
        List<OperationResult> operationResults = new ArrayList<OperationResult>();
        for (int i = 0; i < input.size(); i++) {
            JsonNode jsonNode = input.get(i);
            Operation operation = operations.get(i);
            if (jsonNode != null && jsonNode.size() > 0) {
                if (i >= operations.size() || !operation.getOp().equals("select")) {
                    OperationResult or = objectMapper.convertValue(jsonNode, OperationResult.class);
                    operationResults.add(or);
                } else {
                    List<Row> rows = createRows(operation.getTableSchema(), jsonNode);
                    OperationResult or = new OperationResult(rows);
                    operationResults.add(or);
                }
            }
        }
        return operationResults;
    }*/

}
