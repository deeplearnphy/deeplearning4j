package org.deeplearning4j.plot.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Api Resource
 *
 * @author Adam Gibson
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)

public class ApiResource extends Application<ApiConfiguration> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ApiResource.class);
    private List<String> coords;


    public ApiResource(String coordPath) throws Exception {
        if(coordPath != null && new File(coordPath).exists()) {
            coords = FileUtils.readLines(new File(coordPath));
        }
    }

    @GET
    @Path("/coords")
    @Produces(MediaType.APPLICATION_JSON)
    public Response coords() {
        return Response.ok(coords).build();
    }

    /**
     * Initializes the application bootstrap.
     *
     * @param bootstrap the application bootstrap
     */
    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());

    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {

    }




}
