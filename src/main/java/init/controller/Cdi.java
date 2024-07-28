package init.controller;

import java.text.NumberFormat;
/**
 * This class its necessary to all Cdi functions: calculate month cash yield, calculate total month cash amounth, total cash yield, total cash investment and total cash amount.
 */

public class Cdi {

    private double initialInvestment;
    private int yearsOfInvestment;
    private final double tax = 11.68 / 12 / 100;
    private double monthInvest;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance();

    public Cdi() {
        this(0, 0, 0);
    }

    public Cdi(double initialInvestment, int yearsOfInvestment, double monthInvest) {
        this.initialInvestment = initialInvestment;
        this.yearsOfInvestment = yearsOfInvestment;
        this.monthInvest = monthInvest;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }
    
     /**
      * This method its necessary to all calculations because represent the initial investment
      * @param initialInvestment Initial investment
      */
    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public int getYearsOfInvestment() {
        return yearsOfInvestment;
    }
    /**
     * This method its necessary to all calculations because represent the years of investment
     * @param yearsOfInvestment The investment time (years)
     */

    public void setYearsOfInvestment(int yearsOfInvestment) {
        this.yearsOfInvestment = yearsOfInvestment;
    }

    public double getTax() {
        return tax;
    }

    public double getMonthInvest() {
        return monthInvest;
    }

    public void setMonthInvest(double monthInvest) {
        this.monthInvest = monthInvest;
    }

    public double getTotal() {
        double total = getInitialInvestment();
        int months = getYearsOfInvestment() * 12;

        for (int i = 1; i <= months; i++) {
            if (i == 1) {
                total += total * getTax();
            } else {
                total += getMonthInvest();
                total += total * getTax();
            }
        }
        return total;
    }

    public double getTotalInvest() {
        double initial = getInitialInvestment();
        int month = getYearsOfInvestment() * 12;

        if (getMonthInvest() == 0) {
            return initial;
        } else {
            return initial + (getMonthInvest() * (month - 1));
        }
    }

    public double getRend() {
        return getTotal() - getTotalInvest();
    }

    public String[][] getResultInfos() {
        double total = getInitialInvestment();
        double totalMonth = getInitialInvestment();
        double initialMonth = getInitialInvestment();
        double rend = 0;
        int months = getYearsOfInvestment() * 12;
        String[][] infos = new String[months][3];

        for (int i = 0; i < months; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    if (j == 0) {
                        infos[i][j] = "1º";
                    } else if (j == 1) {
                        infos[i][j] = nf.format(getInitialInvestment() * getTax());
                    } else {
                        infos[i][j] = nf.format(initialMonth + (initialMonth * getTax()));
                        total = initialMonth + (initialMonth * getTax());
                        totalMonth = initialMonth + (initialMonth * getTax());
                    }
                } else {
                    if (j == 0) {
                        infos[i][j] = (i + 1) + "º";
                    } else if (j == 1) {
                        total += getMonthInvest();
                        rend = total * getTax();
                        infos[i][j] = nf.format(rend);
                    } else {
                        totalMonth += getMonthInvest();
                        totalMonth += totalMonth * getTax();
                        infos[i][j] = nf.format(totalMonth);
                    }
                }

            }
        }
        return infos;
    }

}
