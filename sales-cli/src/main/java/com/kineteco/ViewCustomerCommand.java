package com.kineteco;

import com.kineteco.service.CustomerService;
import picocli.CommandLine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@CommandLine.Command(
        name = "view",
        description = "View customer data",
        mixinStandardHelpOptions = true,
        version = "1.0"
)
@ApplicationScoped
public class ViewCustomerCommand implements Runnable {

   Long customerId;

   @Inject
   CustomerService customerService;

   @CommandLine.Option(names = {"-i", "--id"}, description = "Customer id", defaultValue = "1")
   public void setCustomerId(Long customerId) {
      this.customerId = customerId;
   }

   @Override
   public void run() {
      customerService.displayCustomer(customerId);
   }
}

