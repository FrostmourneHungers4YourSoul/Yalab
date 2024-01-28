package data;

import data.Meter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MeterReadings {
    private Map<LocalDate, List<Meter>> metersMap;
    private LocalDate dateToday = LocalDate.now();
    private String username;
    // price per unit
    private double priceCW = 15;
    private double priceHW = 25;
    private double priceHeating = 60;

    public MeterReadings() {
        this.metersMap = new HashMap<>();
        metersMap.put(LocalDate.now(), new ArrayList<>());
    }

    public void setMeterReading(LocalDate date, double reading, String type) {
        List<Meter> meters = metersMap.get(date);

        if (meters == null) {
            meters = new ArrayList<>();
            metersMap.put(date, meters);
        }

        Meter meter = new Meter();
        meter.setTypeMeter(type);
        meter.setPriceToPay(reading * getPriceForType(type));
        meter.setLimit(true);

        meters.add(meter);
    }

    private double getPriceForType(String type) {
        switch (type) {
            case "Cold":
                return priceCW;
            case "Hot":
                return priceHW;
            case "Heating":
                return priceHeating;
            default:
                throw new IllegalArgumentException("Invalid meter type");
        }
    }

    public void printHistory() {
        System.out.println("+----------------------------------------------------+");
        System.out.println("|   Date       |  ColdWater  |  HotWater  |   Heating   |");
        System.out.println("+----------------------------------------------------+");

        for (Map.Entry<LocalDate, List<Meter>> entry : metersMap.entrySet()) {
            LocalDate date = entry.getKey();
            List<Meter> meters = entry.getValue();

            System.out.printf("| %12s | %-10.2f | %-10.2f | %-12.2f |\n",
                    date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    getTypeValue(meters, "Cold"),
                    getTypeValue(meters, "Hot"),
                    getTypeValue(meters, "Heating"));
        }

        System.out.println("+----------------------------------------------------+");
    }

    private double getTypeValue(List<Meter> meters, String type) {
        double totalValue = 0.0;

        for (Meter meter : meters) {
            if (meter.getTypeMeter().equals(type)) {
                totalValue += meter.getPriceToPay();
            }
        }

        return totalValue;
    }

    public void printSquare() {
        System.out.println("================ Meter Readings ================");
        System.out.println("Username: " + username);
        System.out.println("Date: " + dateToday);

        printMeterReadings("Cold", "Cold Water Meter:");
        printMeterReadings("Hot", "Hot Water Meter:");
        printMeterReadings("Heating", "Heating Meter:");

        System.out.println("=================================================");
    }

    private void printMeterReadings(String type, String meterType) {
        System.out.println(meterType);
        System.out.println("+---------------------+");
        System.out.println("| Date       | Reading |");
        System.out.println("+---------------------+");

        List<Meter> meters = metersMap.get(LocalDate.now());
        for (Meter meter : meters) {
            if (Objects.equals(type, meter.getTypeMeter()))
                System.out.printf("| %-11s| %-8.2f |\n", meter.getDate()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), meter.getPriceToPay());
        }


        System.out.println("+---------------------+");
    }


    public Meter getMeterByDate(LocalDate date) {
        List<Meter> meters = metersMap.get(date);
        return meters != null && !meters.isEmpty() ? meters.get(0) : null;
    }

    public void setMeterByDate(LocalDate date, List<Meter> meters) {
        this.metersMap.put(date, meters);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPriceCW() {
        return priceCW;
    }

    public void setPriceCW(double priceCW) {
        this.priceCW = priceCW;
    }

    public double getPriceHW() {
        return priceHW;
    }

    public void setPriceHW(double priceHW) {
        this.priceHW = priceHW;
    }

    public double getPriceHeating() {
        return priceHeating;
    }

    public void setPriceHeating(double priceHeating) {
        this.priceHeating = priceHeating;
    }
}
