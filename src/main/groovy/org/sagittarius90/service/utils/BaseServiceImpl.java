package org.sagittarius90.service.utils;

import org.sagittarius90.io.utils.IdUtils;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected String id;
    protected long realId;

    protected boolean correctId() {
        return realId > 0;
    }

    protected void resolveId(String id) {
        this.id = id;
        this.realId = getIdUtils().decodeId(id);
    }

    public IdUtils getIdUtils() {
        return IdUtils.getInstance();
    }
}
