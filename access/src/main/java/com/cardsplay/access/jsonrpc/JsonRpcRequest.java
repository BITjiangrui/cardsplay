
package com.cardsplay.access.jsonrpc;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

/**
 * Json Rpc Request information that include id,method,params.
 */
public class JsonRpcRequest {

    private final String id;
    private final String method;
    private final List<Object> params;

    /**
     * JsonRpcRequest Constructor.
     * @param id the id node of request information
     * @param method the method node of request information
     */
    public JsonRpcRequest(String id, String method) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(method, "method cannot be null");
        this.id = id;
        this.method = method;
        this.params = Lists.newArrayList();
    }

    /**
     * JsonRpcRequest Constructor.
     * @param id the id node of request information
     * @param method the method node of request information
     * @param params the params node of request information
     */
    public JsonRpcRequest(String id, String method, List<Object> params) {
        checkNotNull(id, "id cannot be null");
        checkNotNull(method, "method cannot be null");
        checkNotNull(params, "params cannot be null");
        this.id = id;
        this.method = method;
        this.params = params;
    }

    /**
     * Returns id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns method.
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Returns params.
     * @return params
     */
    public List<Object> getParams() {
        return params;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method, params);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof JsonRpcRequest) {
            final JsonRpcRequest other = (JsonRpcRequest) obj;
            return Objects.equals(this.id, other.id)
                    && Objects.equals(this.method, other.method)
                    && Objects.equals(this.params, other.params);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("id", id).add("method", method)
                .add("params", params).toString();
    }
}
