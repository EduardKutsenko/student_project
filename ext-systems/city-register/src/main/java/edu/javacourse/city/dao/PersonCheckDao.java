package edu.javacourse.city.dao;

import edu.javacourse.city.PersonResponse;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {
    private static String SQL_REQUEST = "SELECT * FROM cr_address_person ap " +
            "INNER JOIN cr_person per ON ap.person_id = per.person_id " +
            "INNER JOIN cr_address adr ON ap.address_id=adr.address_id " +
            "WHERE " +
            "CURRENT_DATE >= ap.start_date AND (CURRENT_DATE <=ap.end_date OR ap.end_date is null) " +
            "AND per.sur_name = ? " +
            "AND per.given_name = ? " +
            "AND per.patronimic_name = ? " +
            "AND per.date_of_birth = ? " +
            "AND adr.street_code = ? " +
            "AND adr.building =  ? ";


    private IConnectionBuilder connectionBuilder;

    public void setConnectionBuilder(IConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();
        String sql = SQL_REQUEST;

        if (request.getExtention() != null)
            sql += "AND adr.extention = ? ";
        else
            sql += "AND adr.extention is null ";
        if (request.getApartment() != null)
            sql += "AND adr.apartment = ? ";
        else
            sql += "AND adr.apartment is null ";

        try (Connection con = getConnection();
             PreparedStatement stm = con.prepareStatement(sql)) {
            int count = 1; //if extention == null but apartment != null
            stm.setString(count++, request.getSurName());
            stm.setString(count++, request.getGivenName());
            stm.setString(count++, request.getPatronimicName());
            stm.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            stm.setLong(count++, request.getStreetCode());
            stm.setString(count++, request.getBuilding());

            if (request.getExtention() != null)
                stm.setString(count++, request.getExtention());

            if (request.getApartment() != null)
                stm.setString(count++, request.getApartment());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                response.setRegestered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException e) {
            throw new PersonCheckException(e);
        }
        return response;
    }

    private Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();

    }
}
