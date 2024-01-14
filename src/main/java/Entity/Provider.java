package Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import Entity.Client;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provider")
@NamedQuery(name = "Provider.getAll", query = "SELECT e from Provider e")
public class Provider{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprovider")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Client> clients = new ArrayList<>();

    public Provider (){}

    public Provider(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    // Геттер и сеттер для списка клиентов
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    // Методы для добавления и удаления клиентов
    public void addClient(Client client) {
        clients.add(client);
        client.setProvider(this);
    }

    public void removeClient(Client client) {
        clients.remove(client);
        client.setProvider(null);
    }

}
