package in.co.promon.controller;

import java.util.Arrays;
import java.util.List;


import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import in.co.promon.Model.Customer;
import in.co.promon.service.customerService;

@RestController
@RequestMapping("/api")
public class customerController {
	@Autowired
	   RestTemplate restTemplate;
	
	private static final Logger logger=Logger.getLogger(customerController.class);
	
	@Autowired
    private customerService customerservice;

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
    	logger.info("Start The Process to Add The Customer");
        return customerservice.saveCustomer(customer);
    }

    @PostMapping("/addCustomers")
    public List<Customer> addCustomers(@RequestBody List<Customer> customers) {
    	logger.info("Start The Process of Bunch Of Customers to add");
        return customerservice.saveCustomers(customers);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers() {
    	logger.info(" The Process to Get The All Customer ");
        return customerservice.getCustomers();
    }

    @GetMapping("/customerById/{id}")
    public Customer findCustomerById(@PathVariable int id) {
    	logger.info(" The Process to Get The Particular Customer ");
        return customerservice.getCustomerById(id);
    }

    @GetMapping("/customer/{name}")
    public Customer findCustomerByFirstName(@PathVariable String name) {
    	logger.info(" The Process to Get The Customer By Name ");
        return customerservice.getCustomerByFirstName(name);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
    	logger.info(" The Process to Update Existed Customer ");
        return customerservice.updateCustomer(customer);
    }
    @GetMapping("/response")
	public  List<Object> getrest(){
		logger.info(" findAllCustomers method start");
		
		 String url="https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8";
		   Object[] list=restTemplate.getForObject(url,Object[].class);
		   return Arrays.asList(list);
}

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable int id) {
    	logger.info(" The Process to Delete Customer ");
        return customerservice.deleteCustomer(id);
    }

}
