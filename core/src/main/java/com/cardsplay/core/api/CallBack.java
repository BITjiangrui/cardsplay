package com.cardsplay.core.api;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

public class CallBack {
    private final String method;
    private final List<Object> params;

    /**
     * Callback Constructor.
     * @param method the method node of request information
     * @param params the params node of request information
     */
    public CallBack(String method, List<Object> params) {
        checkNotNull(method, "method cannot be null");
        checkNotNull(params, "params cannot be null");
        this.method = method;
        this.params = params;
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
        return Objects.hash(method, params);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CallBack) {
            final CallBack other = (CallBack) obj;
            return Objects.equals(this.method, other.method)
                    && Objects.equals(this.params, other.params);
        }
        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("method", method)
                .add("params", params).toString();
    }
}
