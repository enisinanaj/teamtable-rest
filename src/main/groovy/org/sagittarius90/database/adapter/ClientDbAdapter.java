package org.sagittarius90.database.adapter;

import org.sagittarius90.database.adapter.utils.BaseDbAdapter;
import org.sagittarius90.database.entity.Activity;
import org.sagittarius90.database.entity.Client;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.service.activity.ActivityFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.List;

public class ClientDbAdapter extends BaseDbAdapter {

    private static Logger logger = LoggerFactory.getLogger(ClientDbAdapter.class);

    protected ClientDbAdapter() {

    }

    private static ClientDbAdapter dbAdapterInstance;

    public static ClientDbAdapter getInstance() {

        if (dbAdapterInstance == null) {
            dbAdapterInstance = new ClientDbAdapter();
        }

        return dbAdapterInstance;
    }

    public List<Client> getAllClients() {
        return (List<Client>) getEm().createNamedQuery(Client.ALL_CLIENTS).getResultList();
    }

    public Client getClientByApiKey(String apiKey) {
        Client client = null;
        List<Client> clients = (List<Client>) getEm().createNamedQuery(Client.CLIENT_BY_API_KEY).setParameter("apiKey", apiKey)
                .getResultList();

        if (!clients.isEmpty()) {
            client = clients.get(0);
        }

       return client;
    }
}
