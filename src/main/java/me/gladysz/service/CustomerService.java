package me.gladysz.service;

import me.gladysz.commands.CustomerForm;
import me.gladysz.model.Customer;

public interface CustomerService extends CRUDService<Customer> {

    Customer saveOrUpdateCustomerForm(CustomerForm customerForm);
}
