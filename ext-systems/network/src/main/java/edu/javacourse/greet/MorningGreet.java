package edu.javacourse.greet;

import edu.javacourse.net.Greetable;
import org.w3c.dom.ls.LSOutput;

public class MorningGreet extends Greetable {

    @Override
    public String buildResponse(String userName) {
        return "Good morning, " + userName;
    }
}
