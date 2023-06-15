package de.xdevsoftware.storage;

import de.xdevsoftware.domain.Customer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DataRoot {

    private final List<Customer> customers = new ArrayList<>();
}
