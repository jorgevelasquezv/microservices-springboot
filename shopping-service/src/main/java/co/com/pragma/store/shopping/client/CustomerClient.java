package co.com.pragma.store.shopping.client;

import co.com.pragma.store.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "custom-service", fallback = CustomerHystrixFallbackFactory.class, url = "http://localhost:8081")
public interface CustomerClient {

    @GetMapping(path = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id", required = false) long id );
}
