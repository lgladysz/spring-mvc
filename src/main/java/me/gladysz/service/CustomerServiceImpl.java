package me.gladysz.service;

import me.gladysz.model.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public Customer getById(Long id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Wick");
        customer1.setEmail("email@email.com");
        customer1.setPhoneNumber("555555555");
        customer1.setAddressLineOne("Some Street 1");
        customer1.setAddressLineTwo("Seccond street");
        customer1.setZipCode("55-555");
        customer1.setCity("NYC");

        domainMap.put(1L, customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Mark");
        customer2.setLastName("Twain");
        customer2.setEmail("emailemail@email.com");
        customer2.setPhoneNumber("555666333");
        customer2.setAddressLineOne("Some Street 4");
        customer2.setAddressLineTwo("Street");
        customer2.setZipCode("55-598");
        customer2.setCity("Warsaw");

        domainMap.put(2L, customer2);

        Customer customer3 = new Customer();
        customer3.setId(3L);
        customer3.setFirstName("Bob");
        customer3.setLastName("Moon");
        customer3.setEmail("email22@email.com");
        customer3.setPhoneNumber("567891456");
        customer3.setAddressLineOne("Street 1");
        customer3.setAddressLineTwo("Seccond");
        customer3.setZipCode("58-955");
        customer3.setCity("Cracow");

        domainMap.put(3L, customer3);
    }
}
