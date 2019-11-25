package edu.javacourse.city;

public class PersonResponse {
    private boolean isRegestered;
    private boolean isTemporal;

    public boolean isRegestered() {
        return isRegestered;
    }

    public void setRegestered(boolean regestered) {
        isRegestered = regestered;
    }

    public boolean isTemporal() {
        return isTemporal;
    }

    public void setTemporal(boolean temporal) {
        isTemporal = temporal;
    }
}
