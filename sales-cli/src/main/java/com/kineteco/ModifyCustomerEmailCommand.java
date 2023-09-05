package com.kineteco;

import com.kineteco.service.CustomerService;
import picocli.CommandLine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
@CommandLine.Command(name = "update", description = "Update customer data")
@ApplicationScoped
public class ModifyCustomerEmailCommand implements Runnable {
    Long customerId;

    String customerEmail;

    @Inject
    CustomerService customerService;

    @CommandLine.Option(names = {"-i", "--id"}, description = "Customer id")
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @CommandLine.Option(names = {"-e", "--email"}, description = "Customer email")
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public void run() {
        customerService.updateEmail(customerId, customerEmail);
    }
}

