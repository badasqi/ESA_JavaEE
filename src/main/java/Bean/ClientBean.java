package Bean;

import jakarta.ejb.EJBException;
import Entity.Client;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ClientBean {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    private static final Logger logger = Logger.getLogger(ProviderBean.class.getName());


    public void addClient(Client client) {
        try {
            entityManager.persist(client); // Сохранение провайдера в базе данных
        } catch (Exception e) {
            // Логируем исключение при сохранении провайдера
            logger.severe("Ошибка при сохранении клиента: " + e.getMessage());
            throw new EJBException("Ошибка при сохранении клиента", e);
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients = entityManager.createQuery("SELECT c FROM client c", Client.class).getResultList();
        return clients;
    }

    public Client updateClient(Client updatedClient) {
        try {
            entityManager.merge(updatedClient); // Обновление данных клиента в базе данных
        } catch (Exception e) {
            // Логирование исключения при обновлении клиента
            logger.severe("Ошибка при обновлении клиента: " + e.getMessage());
            throw new EJBException("Ошибка при обновлении клиента", e);
        }
        return updatedClient;
    }

    public void deleteClient(Integer clientId) {
        try {
            Client client = entityManager.find(Client.class, clientId);
            if (client != null) {
                entityManager.remove(client); // Удаление клиента из базы данных
            }else{
                logger.severe("Клиент не найден по ID");
            }
        } catch (Exception e) {
            // Логирование исключения при удалении клиента
            logger.severe("Ошибка при удалении клиента: " + e.getMessage());
            throw new EJBException("Ошибка при удалении клиента", e);
        }
    }
}
