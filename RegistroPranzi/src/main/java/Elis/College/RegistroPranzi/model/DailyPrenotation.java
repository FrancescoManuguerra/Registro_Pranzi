package Elis.College.RegistroPranzi.model;

public class DailyPrenotation {

    Integer lunchvalue;
    Integer dinnervalue;

    public DailyPrenotation(Integer lunchvalue, Integer dinnervalue) {
        this.lunchvalue = lunchvalue;
        this.dinnervalue = dinnervalue;
    }

    public DailyPrenotation() {
    }

    public Integer getLunchvalue() {
        return lunchvalue;
    }

    public void setLunchvalue(Integer lunchvalue) {
        this.lunchvalue = lunchvalue;
    }

    public Integer getDinnervalue() {
        return dinnervalue;
    }

    public void setDinnervalue(Integer dinnervalue) {
        this.dinnervalue = dinnervalue;
    }
}
