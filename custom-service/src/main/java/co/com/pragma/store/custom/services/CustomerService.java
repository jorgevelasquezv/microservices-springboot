package co.com.pragma.store.custom.services;

import co.com.pragma.store.custom.repository.entity.Customer;
import co.com.pragma.store.custom.repository.entity.Region;

import java.util.List;

public interface CustomerService {

    public List<Customer> findCustomerAll();

    public List<Customer> findCustomerByRegion(Region region);

    public Customer createCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public Customer deleteCustomer(Customer customer);

    public Customer getCustomer(Long id);

}
