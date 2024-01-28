package data;

import java.time.LocalDate;

public class Meter {
    private String typeMeter;
    private LocalDate date = LocalDate.now();
    private boolean limit = false;
    private double priceToPay;


    public boolean isLimit() {
        return limit;
    }

    public void setLimit(boolean limit) {
        this.limit = limit;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public double getPriceToPay() {
        return priceToPay;
    }

    public void setPriceToPay(double priceToPay) {
        this.priceToPay = priceToPay;
    }

    public String getTypeMeter() {
        return typeMeter;
    }

    public void setTypeMeter(String typeMeter) {
        this.typeMeter = typeMeter;
    }
}
