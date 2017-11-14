package org.sagittarius90.database.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="client")
@NamedQueries({
        @NamedQuery(name = Client.ALL_CLIENTS, query = "from Client"),
        @NamedQuery(name = Client.CLIENT_BY_API_KEY,
                query = "from Client c where c.apiKey = :apiKey")
})
public class Client implements Serializable {
    public static final String ALL_CLIENTS = "Client.allClients";
    public static final String CLIENT_BY_API_KEY = "Client.clientByApiKey";

    @Id
    @Column(name="client_id")
    @GeneratedValue
    private Integer id;

    @Column(name="client")
    private String client;

    @Column(name="api_key")
    private String apiKey;

    @Column(name="secret_key")
    private String secretKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
