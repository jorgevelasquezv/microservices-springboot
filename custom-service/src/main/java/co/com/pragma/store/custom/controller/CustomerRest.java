package co.com.pragma.store.custom.controller;

import co.com.pragma.store.custom.repository.entity.Customer;
import co.com.pragma.store.custom.repository.entity.Region;
import co.com.pragma.store.custom.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/customers")
public class CustomerRest {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers(@RequestHeader(name = "regionId", required = false) Long regionId){
        List<Customer> customers = new ArrayList<>();

        if (null == regionId){
            customers = customerService.findCustomerAll();
            if (customers.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            Region region = new Region();
            region.setId(regionId);
            customers = customerService.findCustomerByRegion(region);
            if (null == customers){
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id", required = false) long id ){
        log.info("Fetching Customer whit id {}", id);
        Customer customer = customerService.getCustomer(id);
        if (null == customer){
            log.error("Fetching Customer whit id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
        log.info("Creating Customer: {}", customer);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result) );
        }
        Customer customerDB = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
        log.info("Updating Customer whit id {}", id);

        Customer currentCustomer = customerService.getCustomer(id);

        if(null == currentCustomer){
            log.error("Unable to update. Customer whit id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        currentCustomer = customerService.updateCustomer(customer);
        return ResponseEntity.ok(currentCustomer);
    }

    @DeleteMapping(value = "/{d}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id){
        log.info("Fetching and Deleting Customer whith id {}", id);

        Customer customer = customerService.getCustomer(id);
        if (null == customer){
            log.error("Unable to delete. Customer whith id {} not found", id );
            return ResponseEntity.notFound().build();
        }
        customer= customerService.deleteCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .message(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
