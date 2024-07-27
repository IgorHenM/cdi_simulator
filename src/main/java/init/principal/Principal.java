package init.principal;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import init.controller.Cdi;
import init.controller.Recibo;

public class Principal {

    public static void main(String[] args) {
        Cdi inves = new Cdi();
        Recibo rc = new Recibo();
        double initialInv = 0;
        int yearInves = 0;
        double monthInvest = 0;
        String message = "Informações do Investimento: \n";
        ArrayList<Double> list = new ArrayList<>();
        int count = 1;

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        String[] op = {"Investimento Fixo", "Investimento mensal"};
        int typeOfInvestment = JOptionPane.showOptionDialog(null, "Escolha o tipo de investimento:", "Cdi simulador", 0, 1, null, op, op);

        if (typeOfInvestment == 0) {
            try {
                initialInv = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o investimento inicial:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }

            try {
                yearInves = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o tempo de rendimento(em anos):"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }
            if (initialInv > 0 && yearInves > 0) {
                inves.setInitialInvestment(initialInv);
                inves.setYearsOfInvestment(yearInves);
                list = inves.getMonthRend();
                Iterator<Double> it = list.iterator();

                while (it.hasNext()) {
                    message += "O total para o  mês " + count + " foi de: " + nf.format(it.next()) + "\n";
                    count++;
                    if (!it.hasNext()) {
                        message += "\nO total investido foi de: " + nf.format(inves.getTotalInvest())
                                + "\nO rendimento total foi de: " + nf.format(inves.getRend())
                                + "\nO montante final é: " + nf.format(inves.getTotal());
                    }
                }
                JOptionPane.showMessageDialog(null, message);
                rc.setFileContent(message);
                rc.createFile();
            } else {
                JOptionPane.showMessageDialog(null, "Digite valores maiores que 0!");
            }
        } else {
            try {
                initialInv = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o investimento inicial:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }

            try {
                yearInves = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o tempo de rendimento(em anos):"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }

            try {
                monthInvest = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o investimento mensal:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido!");
            }

            if (initialInv > 0 && yearInves > 0 && monthInvest > 0) {
                inves.setInitialInvestment(initialInv);
                inves.setYearsOfInvestment(yearInves);
                inves.setMonthInvest(monthInvest);
                list = inves.getMonthRend();
                Iterator<Double> it = list.iterator();

                while (it.hasNext()) {
                    message += "O total para o  mês " + count + " foi de: " + nf.format(it.next()) + "\n";
                    count++;
                    if (!it.hasNext()) {
                        message += "\nO total investido foi de: " + nf.format(inves.getTotalInvest())
                                + "\nO rendimento total foi de: " + nf.format(inves.getRend())
                                + "\nO montante final é: " + nf.format(inves.getTotal());
                    }
                }
                JOptionPane.showMessageDialog(null, message);
                rc.setFileContent(message);
                rc.createFile();
            } else {
                JOptionPane.showMessageDialog(null, "Digite valores maiores que 0!");
            }
        }
    }
}
