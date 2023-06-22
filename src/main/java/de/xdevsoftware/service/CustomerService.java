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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Inject
    CustomerRepository customerRepository;

    @Inject
    ItemMapper itemMapper;

    public HttpResponse<List<CustomerDTO>> findAll() {
        LOG.info("An attempt is made to load all customers.");
        List<Customer> customers = customerRepository.findAll();
        return HttpResponse.ok(itemMapper.fromList(customers, CustomerDTO.class));
    }

    public HttpResponse<CustomerDTO> findById(String id) {
        LOG.info("An attempt is made to load a customer with the ID: {}.", id);
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()) {
            LOG.info("The customer with ID {} was loaded successfully.", id);
            return HttpResponse.ok(itemMapper.fromItem(customer.get(), CustomerDTO.class));
        }

        LOG.info("No customer with ID {} was found.", id);
        return HttpResponse.notFound();
    }

    public HttpResponse<CustomerDTO> save(CustomerCreateDTO customerDTO) {
        LOG.info("An attempt is made to save a customer with the following data. Firstname: {}, Lastname: {}, E-Mail: {}", customerDTO.getFirstname(), customerDTO.getLastname(), customerDTO.getEmail());
        
        Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setEmail(customerDTO.getEmail());

        customerRepository.save(customer);
        LOG.info("The customer {} {} ({}) was saved successfully.", customerDTO.getFirstname(), customerDTO.getLastname(), customerDTO.getEmail());
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
