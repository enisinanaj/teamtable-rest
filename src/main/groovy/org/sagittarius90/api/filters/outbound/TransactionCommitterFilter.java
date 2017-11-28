package org.sagittarius90.api.filters.outbound;

import org.sagittarius90.database.adapter.utils.PersistenceUtil;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Priority(Priorities.USER)
@Provider
public class TransactionCommitterFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        try {
            PersistenceUtil.commitTransaction();
        } catch(Exception e) {
            if ( PersistenceUtil.getEntityManager() != null
                    && PersistenceUtil.getEntityManager().isOpen())
                PersistenceUtil.rollback();
            throw e;
        } finally {
            PersistenceUtil.closeEntityManager();
        }
    }
}
