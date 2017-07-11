package me.gladysz.service.reposervice;

import me.gladysz.commands.CustomerForm;
import me.gladysz.converters.CustomerFormToCustomer;
import me.gladysz.model.Customer;
import me.gladysz.repository.CustomerRepository;
import me.gladysz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class CustomerServiceRepoImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerFormToCustomer customerFormToCustomer;

    @Autowired
    public CustomerServiceRepoImpl(CustomerRepository customerRepository, CustomerFormToCustomer customerFormToCustomer) {
        this.customerRepository = customerRepository;
        this.customerFormToCustomer = customerFormToCustomer;
    }


    @Override
    public List<Customer> listAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return customerRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Customer saveOrUpdateCustomerForm(CustomerForm customerForm) {
        Customer newCustomer = customerFormToCustomer.convert(customerForm);

        if (newCustomer.getUser().getId() != null) {
            Customer existingCustomer = getById(newCustomer.getId());

            newCustomer.getUser().setEnabled(existingCustomer.getUser().getEnabled());
        }

        return saveOrUpdate(newCustomer);
    }
}
