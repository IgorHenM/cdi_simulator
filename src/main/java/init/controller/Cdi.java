package init.controller;

import java.text.NumberFormat;

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

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public int getYearsOfInvestment() {
        return yearsOfInvestment;
    }

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
                total += getInitialInvestment() * getTax();
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
        double rend = 0;
        int months = getYearsOfInvestment() * 12;
        String[][] infos = new String[months][3];

        for (int i = 0; i < months; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    infos[i][j] = (i + 1) + "º";
                } else if (j == 1) {
                    total += getMonthInvest();
                    rend = total * getTax();
                    infos[i][j] = nf.format(rend);
                } else {
                    total += getMonthInvest();
                    total += total * getTax();
                    infos[i][j] = nf.format(total);
                }
            }
        }
        return infos;
    }

}
