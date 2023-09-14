package com.kineteco.service;

import com.kineteco.model.Customer;
import io.quarkus.runtime.configuration.ProfileManager;
import io.quarkus.scheduler.Scheduled;
import io.quarkus.scheduler.ScheduledExecution;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class Scheduler {
   private static final Logger LOGGER = Logger.getLogger(Scheduler.class);

   @Scheduled(every = "{kineteco.task}", skipExecutionIf = SkipIfNotDev.class)
   public void displayCustomerCount() {
     LOGGER.info(Customer.count());
   }
}

@Singleton
class SkipIfNotDev implements Scheduled.SkipPredicate {
    @Override
    public boolean test(ScheduledExecution execution) {
        return !"dev".equals(ProfileManager.getActiveProfile());
    }
}