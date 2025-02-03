package com.ecomerce.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService costumerService;

    @PostMapping
    public ResponseEntity<String> createCostumer(@RequestBody
                                                 @Valid CustomerRequest request
                                                 )
    {
        return ResponseEntity.ok(costumerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request){
        costumerService.updateCustomer(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        return ResponseEntity.ok(costumerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(costumerService.existById(customerId));
    }

    @GetMapping("/find/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId){
        return ResponseEntity.ok(costumerService.findById(customerId));
    }

    @DeleteMapping("/delete/{customer-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("customer-id") String customerId){
        costumerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}

