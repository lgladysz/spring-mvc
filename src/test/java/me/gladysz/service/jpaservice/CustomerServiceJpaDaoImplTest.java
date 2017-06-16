package me.gladysz.service.jpaservice;

import me.gladysz.model.Customer;
import me.gladysz.model.User;
import me.gladysz.service.CustomerService;
import me.gladysz.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService;

    private UserService userService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void shouldListAllCustomers() throws Exception {
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assertThat(customers.size()).isEqualTo(3);
    }

    @Test
    public void shouldGetOneById() throws Exception {
        Customer customer = customerService.getById(1L);

        assertThat(customer).isNotNull();
        assertThat(customer).hasFieldOrPropertyWithValue("firstName", "John");
        assertThat(customer).hasFieldOrPropertyWithValue("lastName", "Wick");
        assertThat(customer).hasFieldOrPropertyWithValue("email", "email@email.com");
        assertThat(customer).hasFieldOrPropertyWithValue("phoneNumber", "555555555");
        assertThat(customer).hasFieldOrPropertyWithValue("addressLineOne", "Some Street 1");
        assertThat(customer).hasFieldOrPropertyWithValue("addressLineTwo", "Seccond street");
        assertThat(customer).hasFieldOrPropertyWithValue("zipCode", "55-555");
        assertThat(customer).hasFieldOrPropertyWithValue("city", "NYC");
        assertThat(customer).hasFieldOrPropertyWithValue("version", 1);
        assertThat(customer.getUser()).isNotNull();
    }

    @Test
    @DirtiesContext
    public void shouldModifyExistingCustomer() throws Exception {
        List<Customer> customerListBeforeModification = (List<Customer>) customerService.listAll();
        assertThat(customerListBeforeModification.size()).isEqualTo(3);

        Customer modifiedCustomer = customerListBeforeModification.get(0);
        assertThat(modifiedCustomer).isNotNull();
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("firstName", "John");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("lastName", "Wick");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("email", "email@email.com");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("phoneNumber", "555555555");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("addressLineOne", "Some Street 1");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("addressLineTwo", "Seccond street");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("zipCode", "55-555");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("city", "NYC");
        assertThat(modifiedCustomer).hasFieldOrPropertyWithValue("version", 1);

        modifiedCustomer.setFirstName("Jane");
        modifiedCustomer.setLastName("Moon");
        modifiedCustomer.setEmail("newexample@newemail.com");
        modifiedCustomer.setPhoneNumber("123456785");
        modifiedCustomer.setAddressLineOne("One New Street 8");
        modifiedCustomer.setAddressLineTwo("Corner street");
        modifiedCustomer.setZipCode("12-345");
        modifiedCustomer.setCity("London");

        customerService.saveOrUpdate(modifiedCustomer);

        List<Customer> customerListAfterModification = (List<Customer>) customerService.listAll();
        assertThat(customerListAfterModification.size()).isEqualTo(3);

        Customer returnedCustomer = customerListAfterModification.get(0);
        assertThat(returnedCustomer).isNotNull();
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("firstName", "Jane");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("lastName", "Moon");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("email", "newexample@newemail.com");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("phoneNumber", "123456785");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("addressLineOne", "One New Street 8");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("addressLineTwo", "Corner street");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("city", "London");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("zipCode", "12-345");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("version", 2);
    }

    @Test
    @DirtiesContext
    public void shouldAddNewCustomer() throws Exception {
        List<Customer> customerListBeforeModification = (List<Customer>) customerService.listAll();
        assertThat(customerListBeforeModification.size()).isEqualTo(3);

        Customer customerToAdd = new Customer();

        customerToAdd.setFirstName("Jane");
        customerToAdd.setLastName("Moon");
        customerToAdd.setEmail("newexample@newemail.com");
        customerToAdd.setPhoneNumber("123456785");
        customerToAdd.setAddressLineOne("One New Street 8");
        customerToAdd.setAddressLineTwo("Corner street");
        customerToAdd.setZipCode("12-345");
        customerToAdd.setCity("London");

        customerService.saveOrUpdate(customerToAdd);

        List<Customer> customerListAfterModification = (List<Customer>) customerService.listAll();
        assertThat(customerListAfterModification.size()).isEqualTo(4);

        Customer returnedCustomer = customerListAfterModification.get(3);
        assertThat(returnedCustomer).isNotNull();
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("firstName", "Jane");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("lastName", "Moon");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("email", "newexample@newemail.com");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("phoneNumber", "123456785");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("addressLineOne", "One New Street 8");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("addressLineTwo", "Corner street");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("city", "London");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("zipCode", "12-345");
        assertThat(returnedCustomer).hasFieldOrPropertyWithValue("version", 0);
    }

    @Test
    @DirtiesContext
    public void shouldSaveCustomerWithUser() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");

        User user = new User();
        user.setUsername("username1");
        user.setPassword("password1");

        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assertThat(savedCustomer.getUser().getId()).isNotNull();
        assertThat(savedCustomer.getUser().getEncryptedPassword()).isNotNull();
        assertThat(savedCustomer.getUser().getPassword()).isNull();
    }

    @Test
    @DirtiesContext
    public void shouldDeleteOne() throws Exception {
        List<Customer> customerListBeforeDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListBeforeDelete.size()).isEqualTo(3);

        customerService.delete(1L);

        List<Customer> customerListAfterDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListAfterDelete.size()).isEqualTo(2);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteOneAndKeepUser() throws Exception {
        List<Customer> customerListBeforeDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListBeforeDelete.size()).isEqualTo(3);

        List<User> userListBeforeDelete = (List<User>) userService.listAll();
        assertThat(userListBeforeDelete.size()).isEqualTo(3);

        customerService.delete(1L);

        List<Customer> customerListAfterDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListAfterDelete.size()).isEqualTo(2);

        List<User> userListAfterDelete = (List<User>) userService.listAll();
        assertThat(userListAfterDelete.size()).isEqualTo(3);
    }

    @Test
    @DirtiesContext
    public void shouldDeleteOneWithoutUser() throws Exception {
        List<Customer> customerListBeforeDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListBeforeDelete.size()).isEqualTo(3);

        customerService.delete(3L);

        List<Customer> customerListAfterDelete = (List<Customer>) customerService.listAll();
        assertThat(customerListAfterDelete.size()).isEqualTo(2);
    }
}