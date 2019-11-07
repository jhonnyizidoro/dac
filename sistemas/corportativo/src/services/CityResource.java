package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.City;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("city")
public class CityResource {
    @GET
    @Path("state/{state}")
    @Produces("text/plain; charset=UTF-8")
    public Response getCitiesByState(@PathParam("state") int stateId) {
        List<City> cities = City.getByStateId(stateId);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.ok(gson.toJson(cities)).build();
    }
}
