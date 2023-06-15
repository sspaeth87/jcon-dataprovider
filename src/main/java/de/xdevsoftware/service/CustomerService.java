package de.xdevsoftware.service;

import de.xdevsoftware.domain.Customer;
import de.xdevsoftware.dto.CustomerCreateDTO;
import de.xdevsoftware.dto.CustomerDTO;
import de.xdevsoftware.dto.CustomerUpdateDTO;
import de.xdevsoftware.repository.CustomerRepository;
import de.xdevsoftware.util.ItemMapper;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    ItemMapper itemMapper;

    public HttpResponse<List<CustomerDTO>> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return HttpResponse.ok(itemMapper.fromList(customers, CustomerDTO.class));
    }

    public HttpResponse<CustomerDTO> findById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            return HttpResponse.ok(itemMapper.fromItem(customer.get(), CustomerDTO.class));
        }

        return HttpResponse.notFound();
    }

    public HttpResponse<CustomerDTO> save(CustomerCreateDTO customerDTO) {
        Customer customer = Customer.builder()
                .firstname(customerDTO.getFirstname())
                .lastname(customerDTO.getLastname())
                .email(customerDTO.getEmail())
                .build();

        customerRepository.save(customer);
        return HttpResponse.created(itemMapper.fromItem(customer, CustomerDTO.class));
    }

    public HttpResponse<CustomerDTO> update(CustomerUpdateDTO customerDTO) {
        Customer customer = customerRepository.update(customerDTO);
        if(customer == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(itemMapper.fromItem(customer, CustomerDTO.class));
    }
}
