package sla.org.checkboxsaving;

import java.io.Serializable;

public class Model implements Serializable {

    // OBJECT FIELDS
    private int amountOfChecks;
    private boolean yoCurrentlyChecked;

    // CONSTRUCTOR
    Model() {
        amountOfChecks = 0;
        yoCurrentlyChecked = false;
    }

    // OBJECT GETTER/SETTER
    public int getAmountOfChecks() {
        return amountOfChecks;
    }

    public void setAmountOfChecks(int amount) {
        amountOfChecks = amount;
    }

    public void incrementAmountOfChecks() {
        amountOfChecks = amountOfChecks + 1;
    }

    public boolean isYoCurrentlyChecked() {
        return yoCurrentlyChecked;
    }

    public void setYoCurrentlyChecked(boolean checked) {
        yoCurrentlyChecked = checked;
    }

}
