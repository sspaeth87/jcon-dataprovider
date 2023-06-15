package de.xdevsoftware.repository;

import de.xdevsoftware.domain.Customer;
import de.xdevsoftware.dto.CustomerUpdateDTO;
import de.xdevsoftware.storage.DB;
import de.xdevsoftware.util.ItemMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public List<Customer> findAll() {
        return DB.root.getCustomers();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return DB.root.getCustomers()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    @Override
    public void save(Customer customer) {
        DB.root.getCustomers().add(customer);
        DB.storageManager.store(DB.root.getCustomers());
    }

    @Override
    public Customer update(CustomerUpdateDTO customer) {
        Optional<Customer> customerOptional = this.findById(customer.getId());
        if (!customerOptional.isPresent()) return null;

        Customer customerToUpdate = customerOptional.get();
        customerToUpdate.setFirstname(customer.getFirstname());
        customerToUpdate.setLastname(customer.getLastname());
        customerToUpdate.setEmail(customer.getEmail());

        DB.storageManager.store(customerToUpdate);
        return customerToUpdate;
    }
}
