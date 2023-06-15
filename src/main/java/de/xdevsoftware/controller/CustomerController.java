package de.xdevsoftware.controller;

import de.xdevsoftware.dto.CustomerCreateDTO;
import de.xdevsoftware.dto.CustomerDTO;
import de.xdevsoftware.dto.CustomerUpdateDTO;
import de.xdevsoftware.service.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;

import java.util.List;

@Controller("customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @Get
    public HttpResponse<List<CustomerDTO>> findAll() {
        return customerService.findAll();
    }

    @Get("{id}")
    public HttpResponse<CustomerDTO> findById(@PathVariable("id") String id) {
        return customerService.findById(id);
    }

    @Post
    public HttpResponse<CustomerDTO> save(@Body CustomerCreateDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @Put
    public HttpResponse<CustomerDTO> update(@Body CustomerUpdateDTO customerDTO) {
        return customerService.update(customerDTO);
    }
}
