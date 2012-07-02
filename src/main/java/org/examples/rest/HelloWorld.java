package org.examples.rest;
import com.yammer.metrics.annotation.Metered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
@Path("/hello")
public class HelloWorld {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorld.class);

    @GET
    @Path("/echo/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    @Metered
    public String ping(@PathParam("input") String input) {
        LOG.info("Ping " + input);
        return input;
    }

    @GET
    @Path("/log/debug/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    @Metered
    public String debug(@PathParam("input") String input) {
        LOG.debug("Ping " + input);
        return input;
    }

    @GET
    @Path("/log/info/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    @Metered
    public String info(@PathParam("input") String input) {
        LOG.info("Ping " + input);
        return input;
    }

    @GET
    @Path("/log/warn/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    @Metered
    public String warn(@PathParam("input") String input) {
        LOG.warn("Ping " + input);
        return input;
    }

    @GET
    @Path("/log/error/{input}")
    @Produces(MediaType.TEXT_PLAIN)
    @Metered
    public String error(@PathParam("input") String input) {
        LOG.error("Ping " + input);
        return input;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/jsonBean")
    @Metered
    public Response modifyJson(JsonBean input) {
        LOG.info("modifyJson " + input);
        input.setVal2(input.getVal1());
        return Response.ok().entity(input).build();
    }
}

