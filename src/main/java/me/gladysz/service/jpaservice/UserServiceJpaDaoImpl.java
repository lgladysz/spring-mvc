package me.gladysz.service.jpaservice;

import me.gladysz.model.Customer;
import me.gladysz.model.User;
import me.gladysz.service.UserService;
import me.gladysz.service.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    private final EncryptionService encryptionService;

    @Autowired
    public UserServiceJpaDaoImpl(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<User> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        if (domainObject.getPassword() != null) {
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }

        User savedUser = em.merge(domainObject);
        em.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();

        User user = em.find(User.class, id);
        em.getTransaction().begin();
        Customer customer = user.getCustomer();
        if (customer != null) {
            customer.setUser(null);
            user.setCustomer(null);
        }
        em.remove(user);
        em.getTransaction().commit();
    }
}
