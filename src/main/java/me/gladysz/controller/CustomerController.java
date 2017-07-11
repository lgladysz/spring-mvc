package me.gladysz.controller;

import me.gladysz.commands.CustomerForm;
import me.gladysz.model.Customer;
import me.gladysz.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    private static final String ATRIBUTE_1 = "customers";
    private static final String ATRIBUTE_2 = "customer";
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping({"/list", "/"})
    public String listCustomers(Model model) {
        model.addAttribute(ATRIBUTE_1, customerService.listAll());
        return "customer/list";
    }

    @RequestMapping("/show/{id}")
    public String getCustomer(@PathVariable Long id, Model model) {
        model.addAttribute(ATRIBUTE_2, customerService.getById(id));
        return "customer/show";
    }

    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);

        CustomerForm customerForm = new CustomerForm();

        customerForm.setCustomerId(customer.getId());
        customerForm.setCustomerVersion(customer.getVersion());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setEmail(customer.getEmail());
        customerForm.setPhoneNumber(customer.getPhoneNumber());
        customerForm.setUserId(customer.getUser().getId());
        customerForm.setUserVersion(customer.getUser().getVersion());
        customerForm.setUsername(customer.getUser().getUsername());

        model.addAttribute(ATRIBUTE_2, customerForm);
        return "customer/form";
    }

    @RequestMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute(ATRIBUTE_2, new CustomerForm());
        return "customer/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdateCustomer(CustomerForm customerForm) {
        Customer newCustomer = customerService.saveOrUpdateCustomerForm(customerForm);
        return "redirect:/customer/show/" + newCustomer.getId();
    }

    @RequestMapping("delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }
}
