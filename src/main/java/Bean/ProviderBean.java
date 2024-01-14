package Bean;

import jakarta.ejb.EJBException;
import Entity.Provider;
import java.util.logging.Level;

import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProviderBean {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;


    private static final Logger logger = Logger.getLogger(ProviderBean.class.getName());


    public void addProvider(Provider provider) {
        try {
            entityManager.persist(provider); // Сохранение провайдера в базе данных
        } catch (Exception e) {
            // Логируем исключение при сохранении провайдера
            logger.severe("Ошибка при сохранении провайдера: " + e.getMessage());
            throw new EJBException("Ошибка при сохранении провайдера", e);
        }
    }

    public List<Provider> getAllProviders() {
        List<Provider> providers = entityManager.createQuery("SELECT p FROM Provider p", Provider.class).getResultList();
        return providers;
    }

    public Provider getProviderByName(String name) {
        try {
            TypedQuery<Provider> query = entityManager.createQuery("SELECT p FROM Provider p WHERE p.name = :name", Provider.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Если не найден провайдер с указанным именем, возвращаем null или обрабатываем по вашему усмотрению
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при поиске провайдера по имени: " + e.getMessage(), e);
            throw new EJBException("Ошибка при поиске провайдера по имени", e);
        }
    }

    public Provider getProviderById(Integer id) {
        try {
            TypedQuery<Provider> query = entityManager.createQuery("SELECT p FROM Provider p WHERE p.id = :id", Provider.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Если не найден провайдер с указанным id, возвращаем null или обрабатываем по вашему усмотрению
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при поиске провайдера по id: " + e.getMessage(), e);
            throw new EJBException("Ошибка при поиске провайдера по id", e);
        }
    }



    public Provider updateProvider(Provider provider) {
        try {
            return entityManager.merge(provider); // Обновление провайдера в базе данных
        } catch (Exception e) {
            logger.severe("Ошибка при обновлении провайдера: " + e.getMessage());
            throw new EJBException("Ошибка при обновлении провайдера", e);
        }
    }

    public void deleteProvider(Integer id) {
        try {
            Provider provider = entityManager.find(Provider.class, id);
            if (provider != null) {
                entityManager.remove(provider); // Удаление провайдера из базы данных
            }
        } catch (Exception e) {
            logger.severe("Ошибка при удалении провайдера: " + e.getMessage());
            throw new EJBException("Ошибка при удалении провайдера", e);
        }
    }
}
