package com.okular.jserv;

import com.okular.jserv.orders.control.EntityBuilder;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class RootResource {

    @Context
    UriInfo uriInfo;

    @Inject
    EntityBuilder entityBuilder;

    @GET
    public Response getIndex() {
        return Response.ok(entityBuilder.buildIndex(this.uriInfo))
                .header("Greeting", "Hello world")
                .build();
    }

}
