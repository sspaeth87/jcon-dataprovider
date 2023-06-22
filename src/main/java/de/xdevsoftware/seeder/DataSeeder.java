package de.xdevsoftware.seeder;

import de.xdevsoftware.domain.Customer;
import de.xdevsoftware.repository.CustomerRepository;
import de.xdevsoftware.storage.DB;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class DataSeeder implements ApplicationEventListener<ServerStartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(DataSeeder.class);

    @Inject
    CustomerRepository customerRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        List<Customer> customers = customerRepository.findAll();

        if(customers.isEmpty()) {
            LOG.info("Customers are empty, DataSeeder is running.");
            
            Customer customer = new Customer();
            customer.setFirstname("Sebastian");
            customer.setLastname("Späth");
            customer.setEmail("s.spaeth@xdev-software.de");

            DB.root.getCustomers().add(customer);
            DB.storageManager.store(DB.root.getCustomers());

            LOG.info("Customers have been created successfully.");
        }
    }
}
