package de.xdevsoftware.repository;

import de.xdevsoftware.domain.Customer;
import de.xdevsoftware.dto.CustomerUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> findAll();

    Optional<Customer> findById(String id);

    void save(Customer customer);

    Customer update(CustomerUpdateDTO customer);
}
