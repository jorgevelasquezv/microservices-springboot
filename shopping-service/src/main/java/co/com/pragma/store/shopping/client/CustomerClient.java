package co.com.pragma.store.shopping.client;

import co.com.pragma.store.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {

    @GetMapping(value = "/customer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
}
