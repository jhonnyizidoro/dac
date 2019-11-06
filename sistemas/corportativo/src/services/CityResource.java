package services;

import models.City;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("city")
public class CityResource {
    @GET
    @Path("state/{state}")
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    public String getCitiesByState(@PathParam("state") int stateId) {
        List<City> cities = City.getByStateId(stateId);
        String json = "[";
        for (City city : cities) {
            json += "{\"id\":"+city.getId()+",\"name\":\""+city.getName()+"\",\"state_id\":"+city.getState().getId()+"},";
        }
        json = json.substring(0, json.length() - 1) + "]";
        return json;
    }
}
