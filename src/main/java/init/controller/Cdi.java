package init.controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * This class its necessary to all Cdi functions: calculate month cash yield, calculate total month cash amounth, total cash yield, total cash investment and total cash amount.
 */

public class Cdi {

    private double initialInvestment;
    private int yearsOfInvestment;
    private double tax = 0;
    private double yearTax = 0;
    private double monthInvest;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance();
    private final FinanceClient fc = new FinanceClient();

    public Cdi() {
        this(0, 0, 0);
    }

    public Cdi(double initialInvestment, int yearsOfInvestment, double monthInvest) {
        this.initialInvestment = initialInvestment;
        this.yearsOfInvestment = yearsOfInvestment;
        this.monthInvest = monthInvest;
        this.tax = fc.getCdiTax() / 12 / 100;
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

    public void setTax() {
        this.tax = this.yearTax / 12 / 100;
    }

    public double getTax() {
        return tax;
    }

    public double getYearTax() {
        return yearTax;
    }

    public void setYearTax() {
        this.yearTax = fc.getCdiTax();
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
                if (i == 0) {//primeiro mês
                    if (j == 0) {
                        infos[i][j] = "1º";
                    } else if (j == 1) {
                        infos[i][j] = nf.format(getInitialInvestment() * getTax());
                    } else {
                        infos[i][j] = nf.format(initialMonth + (initialMonth * getTax()));
                        total = initialMonth + (initialMonth * getTax());
                        totalMonth = initialMonth + (initialMonth * getTax());
                    }
                } else {//demais meses
                    if (j == 0) {//declaração de mês
                        infos[i][j] = (i + 1) + "º";
                    } else if (j == 1) {
                        total += getMonthInvest();
                        rend = total * getTax();
                        total += rend;
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

    public String fileContent() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        DateTimeFormatter formDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formTime = DateTimeFormatter.ofPattern("HH-mm-ss");

        String fileContent = "Informações da Simulação:\nData: " + formDate.format(date) + "\nHora: " + formTime.format(time) + "\nTaxa do CDI (anual):" + this.yearTax + "%" + "\nTaxa do CDI (mensal): "+ this.tax + "%" + "\n\nMensal: \n";

        double total = getInitialInvestment();
        int months = getYearsOfInvestment() * 12;

        for (int i = 1; i <= months; i++) {
            if (i == 1) {
                total += getInitialInvestment() * getTax();
                fileContent += "\n" + i + "º Mês:" + nf.format(total);
            } else {
                total += getMonthInvest();
                total += total * getTax();
                fileContent += "\n" + i + "º Mês:" + nf.format(total);
            }
        }
        fileContent += "\n\nTotal investido: " + nf.format(getTotalInvest()) + "\nRendimento Total: " + nf.format(getRend()) + "\nMontante total: " + nf.format(getTotal());
        return fileContent;
    }

}
