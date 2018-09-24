
package com.cardsplay.access.jsonrpc;

import java.util.Stack;

/**
 * Context for decode parameters.
 */
public class JsonReadContext {
    private Stack<Byte> bufStack;
    private boolean isStartMatch;
    private int lastReadBytes;

    /**
     * Constructs a JsonReadContext object. This class only need initial
     * parameter value for the readToJsonNode method of JsonRpcReaderUtil
     * entity.
     */
    public JsonReadContext() {
        bufStack = new Stack<Byte>();
        isStartMatch = false;
        lastReadBytes = 0;
    }

    /**
     * Return bufStack.
     * @return bufStack
     */
    public Stack<Byte> getBufStack() {
        return bufStack;
    }

    /**
     * Set bufStack, used for match the braces and double quotes.
     * @param bufStack Stack of Byte
     */
    public void setBufStack(Stack<Byte> bufStack) {
        this.bufStack = bufStack;
    }

    /**
     * Return isStartMatch.
     * @return isStartMatch
     */
    public boolean isStartMatch() {
        return isStartMatch;
    }

    /**
     * Set isStartMatch.
     * @param isStartMatch mark whether the matching has started
     */
    public void setStartMatch(boolean isStartMatch) {
        this.isStartMatch = isStartMatch;
    }

    /**
     * Return lastReadBytes.
     * @return lastReadBytes
     */
    public int getLastReadBytes() {
        return lastReadBytes;
    }

    /**
     * Set lastReadBytes.
     * @param lastReadBytes the bytes for last decoding incomplete record
     */
    public void setLastReadBytes(int lastReadBytes) {
        this.lastReadBytes = lastReadBytes;
    }
}
