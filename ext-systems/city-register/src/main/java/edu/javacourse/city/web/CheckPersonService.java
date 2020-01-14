package edu.javacourse.city.web;

import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.domain.PersonRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/check")
public class CheckPersonService {
  //  @GET
   // @Path("/{id}")
  //  @Produces(MediaType.APPLICATION_JSON)
//    public String checkPerson(@PathParam("id") int simpleId,
//                              @QueryParam("name") String simpleName) {
//        return simpleId + ": " + simpleName;
//    public PersonResponse personResponse(@PathParam("id") int simpleId,
//                                         @QueryParam("name") String simpleName) {
//        return new PersonResponse();
//public PersonRequest checkPerson(){
//        PersonRequest pr = new PersonRequest();
//        pr.setSurName("Semenov");
//        pr.setGivenName("Pavel");
//        pr.setPatronimicName("Trifonovich");
//        pr.setStreetCode(1L);
//        pr.setDateOfBirth(LocalDate.of(1995,03,9));
//        pr.setBuilding("3");
//        pr.setExtention("EX");
//        pr.setApartment("4657A");
//        return pr;
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PersonResponse checkPerson(PersonRequest request) {
        System.out.println(request.toString());
        return new PersonResponse();
    }
}
