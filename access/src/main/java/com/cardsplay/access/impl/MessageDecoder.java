
package com.cardsplay.access.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardsplay.access.jsonrpc.JsonReadContext;
import com.cardsplay.access.util.JsonRpcReaderUtil;

/**
 * Decoder for inbound messages.
 */
public class MessageDecoder extends ByteToMessageDecoder {

    private final Logger log = LoggerFactory.getLogger(MessageDecoder.class);
    private final JsonReadContext context = new JsonReadContext();

    /**
     * Default constructor.
     */
    public MessageDecoder() {
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf,
                          List<Object> out) throws Exception {
        log.debug("Message decoder");
        JsonRpcReaderUtil.readToJsonNode(buf, out, context);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        log.error("Exception inside channel handling pipeline.", cause);
        context.close();
    }
}
