package edu.javacourse.city.web;

import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.dao.PersonCheckDao;
import edu.javacourse.city.dao.PoolConnectionBuilder;
import edu.javacourse.city.domain.PersonRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {
    public static final Logger log = LoggerFactory.getLogger(CheckPersonServlet.class);
    private PersonCheckDao dao;

    @Override
    public void init() throws ServletException {
        dao = new PersonCheckDao();
        dao.setConnectionBuilder(new PoolConnectionBuilder());
        log.info("Servlet is created");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  resp.getWriter().println("GET Check Person - Called");
        req.setCharacterEncoding("UTF-8");

       // String surname = req.getParameter("surname");
        PersonRequest pr = new PersonRequest();
        pr.setSurName(req.getParameter("surname"));
       // pr.setGivenName("Pavel");
        pr.setGivenName(req.getParameter("givenName"));
       // pr.setPatronimicName("Trifonovich");
        pr.setPatronimicName(req.getParameter("patrName"));
       // pr.setDateOfBirth(LocalDate.of(1995,03,9));
        LocalDate dob=  LocalDate.parse(req.getParameter("dob"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        pr.setDateOfBirth(dob);
     //   pr.setStreetCode(1L);
        pr.setStreetCode(Long.parseLong(req.getParameter("streetCode")));
//        pr.setBuilding("3");
//        pr.setExtention("EX");
//        pr.setApartment("4657A");
        pr.setBuilding(req.getParameter("building"));
        pr.setExtention(req.getParameter("extension"));
        pr.setApartment(req.getParameter("apartment"));

        try {
            PersonResponse presp = dao.checkPerson(pr);
            if(presp.isRegestered())
                resp.getWriter().println("Person is registered");
            else
                resp.getWriter().println("Person is NOT registered");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
