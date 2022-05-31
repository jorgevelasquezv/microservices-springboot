package co.com.pragma.store.custom.services;

import co.com.pragma.store.custom.repository.CustomerRepository;
import co.com.pragma.store.custom.repository.entity.Customer;
import co.com.pragma.store.custom.repository.entity.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {

        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomerByRegion(Region region) {

        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberId(customer.getNumberId());
        if (customerDB != null){
            return customerDB;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null){
            return null;
        }

        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB = getCustomer(customer.getId());
        if (customerDB == null){
            return null;
        }
        customer.setState("DELETED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
