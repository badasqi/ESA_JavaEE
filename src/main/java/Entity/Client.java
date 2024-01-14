package Entity;

import jakarta.persistence.*;

@Entity(name = "client")
@NamedQuery(name = "Client.getAll", query = "SELECT e from client e")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclient")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private String contact;

    @Column(name = "tariff")
    private String tariff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprovider")
    private Provider provider;

    public Client (){}

    public Client(String name, String contact, String tariff, Provider provider){
        this.name = name;
        this.tariff = tariff;
        this.contact = contact;
        this.provider = provider;
    }

    public String getName(){
        return this.name;
    }
    public String getContact(){
        return this.contact;
    }
    public String getTariff(){
        return this.tariff;
    }
    public String getProvider() {return provider.getName();}
    public String getAddress() {return this.address;}


    public void setName(String name){
        this.name = name;
    }
    public void setTariff(String tariff){
        this.tariff = tariff;
    }
    public void setContact(String contact){
        this.contact = contact;
    }
    public void setProvider(Provider provider){ this.provider= provider;}
    public void setAddress(String address) {this.address = address;}

    public String getProviderName() {
        return provider.getName();
    }

    public int getProviderId() {
        return provider.getId();
    }
}
