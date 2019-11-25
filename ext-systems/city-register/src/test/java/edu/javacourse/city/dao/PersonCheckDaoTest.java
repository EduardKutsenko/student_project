package edu.javacourse.city.dao;

import edu.javacourse.city.PersonResponse;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.exception.PersonCheckException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonCheckDaoTest {



    @Test
    public void checkPerson() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Semenov");
        pr.setGivenName("Pavel");
        pr.setPatronimicName("Trifonovich");
        pr.setStreetCode(1L);
        pr.setDateOfBirth(LocalDate.of(1995,03,9));
        pr.setBuilding("3");
        pr.setExtention("EX");
        pr.setApartment("4657A");
        PersonCheckDao pcd = new PersonCheckDao();
        PersonResponse presponse = pcd.checkPerson(pr);
        Assert.assertTrue(presponse.isRegestered());
        Assert.assertFalse(presponse.isTemporal());
    }

    @Test
    public void checkPerson2() throws PersonCheckException {
        PersonRequest pr = new PersonRequest();
        pr.setSurName("Semenova");
        pr.setGivenName("Polina"); //('Semenova', 'Polina', 'Borisobna', '1996-03-09'
        pr.setPatronimicName("Borisobna");
        pr.setStreetCode(1L);
        pr.setDateOfBirth(LocalDate.of(1996,03,9));
        pr.setBuilding("3");
        //pr.setExtention(null);
        pr.setApartment("4");
        PersonCheckDao pcd = new PersonCheckDao();
        PersonResponse presponse = pcd.checkPerson(pr);
        Assert.assertTrue(presponse.isRegestered());
        Assert.assertFalse(presponse.isTemporal());
    }

}