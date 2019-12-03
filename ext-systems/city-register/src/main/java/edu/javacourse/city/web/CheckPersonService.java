package edu.javacourse.city.web;

import edu.javacourse.city.PersonResponse;
import edu.javacourse.city.dao.PersonCheckDao;
import edu.javacourse.city.dao.PoolConnectionBuilder;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.exception.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/check")
public class CheckPersonService {
    private static final Logger log = LoggerFactory.getLogger(CheckPersonService.class);
    private PersonCheckDao dao;

     @PostConstruct //do imediatelly after creation of object
     @Singleton // object will not be is destroyed
     public void init(){
         log.info("START");
         dao = new PersonCheckDao();
         dao.setConnectionBuilder(new PoolConnectionBuilder());
     }

//     @PreDestroy
//     public void destroy(){
//         log.info("FINISH");
//     }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //public PersonResponse checkPerson(@PathParam("id") int simpleId,
//                                      @QueryParam("name") String simpleName){
    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        log.info(request.toString());
        return dao.checkPerson(request);
    }
}
