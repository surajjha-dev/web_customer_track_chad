package com.wct.controller;

import com.wct.entity.Customer;
import com.wct.entity.Employee;
import com.wct.service.CustomerService;
import com.wct.service.MultiDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MultiDataSourceService multiDataSourceService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listCustomer(Model theModel) {

        // get customers from the service layer
        List<Customer> theCustomers = customerService.getCustomers();

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @GetMapping(value = "/lists")
    public String listOfCustomerAndEmployee(Model theModel) {

        // get customers from the service layer
        List<Customer> theCustomers = multiDataSourceService.getCustomers();
        List<Employee> theEmployees = multiDataSourceService.getEmployees();

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        theModel.addAttribute("employees", theEmployees);
        return "display-results";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        // create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {

        Customer customer = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service
        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping ("/deleteCustomer")
    public String deleteCustomer(@RequestParam("customerId") int customerId) {

        // delete the customer using our service
        customerService.deleteCustomer(customerId);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }

}
