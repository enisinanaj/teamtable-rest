package org.sagittarius90.api.filters.inbound;

import org.sagittarius90.database.adapter.utils.PersistenceUtil;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.AUTHORIZATION)
@Provider
public class TransactionInitializerFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            PersistenceUtil.beginTransaction();
        } catch(Exception e) {
            PersistenceUtil.rollback();
            PersistenceUtil.closeEntityManager();
        }
    }
}
