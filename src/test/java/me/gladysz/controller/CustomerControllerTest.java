package me.gladysz.controller;

import me.gladysz.model.Address;
import me.gladysz.model.Customer;
import me.gladysz.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void listCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        //specific Mockito interaction, tell stub to return list of products
        when(customerService.listAll()).thenReturn((List) customers); //need to strip generics to keep Mockito happy :)

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void getCustomer() throws Exception {
        Long id = 1L;

        //Tell Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/show/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/show"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void editCustomer() throws Exception {
        Long id = 1L;

        //Tell Mockito stub to return new product for ID 1
        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/form"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void newCustomer() throws Exception {
        //should not call service
        verifyZeroInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/form"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void saveOrUpdateCustomer() throws Exception {
        Long id = 1L;

        String firstName = "John";
        String lastName = "Whick";
        String email = "example@example.com";
        String phoneNumber = "555888666";
        String addressLineOne = "Street 1";
        String addressLineTwo = "";
        String city = "London";
        String zipCode = "55-444";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setFirstName(firstName);
        returnCustomer.setLastName(lastName);
        returnCustomer.setEmail(email);
        returnCustomer.setPhoneNumber(phoneNumber);
        returnCustomer.setBillingAddress(new Address());
        returnCustomer.getBillingAddress().setAddressLineOne(addressLineOne);
        returnCustomer.getBillingAddress().setAddressLineTwo(addressLineTwo);
        returnCustomer.getBillingAddress().setCity(city);
        returnCustomer.getBillingAddress().setZipCode(zipCode);

        when(customerService.saveOrUpdate(Matchers.any(Customer.class))).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                .param("id", id.toString())
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("phoneNumber", phoneNumber)
                .param("billingAddress.addressLineOne", addressLineOne)
                .param("billingAddress.addressLineTwo", addressLineTwo)
                .param("billingAddress.city", city)
                .param("billingAddress.zipCode", zipCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/show/" + id.toString()))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("addressLineOne", is(addressLineOne)))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("addressLineTwo", is(addressLineTwo)))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("city", is(city)))))
                .andExpect(model().attribute("customer", hasProperty("billingAddress", hasProperty("zipCode", is(zipCode)))));

        //verify properties of bound object
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(customerCaptor.capture());

        Customer boundCustomer = customerCaptor.getValue();

        assertEquals(id, boundCustomer.getId());
        assertEquals(firstName, boundCustomer.getFirstName());
        assertEquals(lastName, boundCustomer.getLastName());
        assertEquals(email, boundCustomer.getEmail());
        assertEquals(phoneNumber, boundCustomer.getPhoneNumber());
        assertEquals(addressLineOne, boundCustomer.getBillingAddress().getAddressLineOne());
        assertEquals(addressLineTwo, boundCustomer.getBillingAddress().getAddressLineTwo());
        assertEquals(city, boundCustomer.getBillingAddress().getCity());
        assertEquals(zipCode, boundCustomer.getBillingAddress().getZipCode());

    }

    @Test
    public void deleteCustomer() throws Exception {
        Long id = 1L;

        mockMvc.perform(get("/customer/delete/" + id.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"));

        verify(customerService, times(1)).delete(id);
    }
}