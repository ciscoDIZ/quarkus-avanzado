package com.kineteco.rest;

import com.kineteco.model.Customer;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/customers")
public class CustomerResource {

    private static final Logger LOGGER = Logger.getLogger(CustomerResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> customers() {
        return Customer.listAll();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer get(@PathParam("customerId") String customerId) {
        return Customer.findByCustomerId(customerId)
              .orElseThrow(() -> new NotFoundException());
    }

    @GET
    @Path("/display/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance get(@PathParam("id") Long id) {
        Optional<Customer> customer = Customer.<Customer>findByIdOptional(id);
        String name = customer.map(c -> c.name).orElse("Unknown");
        return Templates.customers(name);
    }

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance customers(String name);
    }
}