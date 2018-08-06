package com.kileydelaney.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QueryResultObject {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy")
    private String date;

    private String symbol;

    private double maxPrice;

    private double minPrice;

    private int totalVol;



    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }


    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }


    public double getMaxPrice() { return maxPrice; }

    public void setMaxPrice(double price) { this.maxPrice = price; }


    public double getMinPrice() { return minPrice; }

    public void setMinPrice(double price) { this.minPrice = price; }


    public int getTotalVol() { return totalVol; }

    public void setTotalVol(int vol) { this.totalVol = vol; }



    public String toString() {
        if (maxPrice == 0 || minPrice == 0 || totalVol == 0) {
            return "Sorry, no data for that date and/or stock";
        } else {
            return ("The highest price of stock " + symbol + " on " + date + " is $" + maxPrice + ". \n" +
                    "The lowest price of stock " + symbol + " on " + date + " is $" + minPrice + ". \n" +
                    "The total volume of stock " + symbol + " traded on " + date + " is " + totalVol + " units. \n");
        }
    }

    public String maxToString() {
        if (maxPrice == 0) {
            return "Sorry, no data for that date and/or stock";
        } else {
            return ("The highest price of stock " + symbol + " on " + date + " is $" + maxPrice);
        }
    }

    public String minToString() {
        if (minPrice == 0) {
            return "Sorry, no data for that date and/or stock";
        } else {
            return ("The lowest price of stock " + symbol + " on " + date + " is $" + minPrice);
        }
    }

    public String volToString() {
        if (totalVol == 0) {
            return "Sorry, no data for that date and/or stock";
        } else {
            return ("The total volume of stock " + symbol + " traded on " + date + " is " + totalVol + " units");
        }
    }


}
