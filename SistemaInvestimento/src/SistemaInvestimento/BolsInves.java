package SistemaInvestimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BolsInves extends JFrame {

    // Componentes
    private JTextField campoAtivo, campoValorInicial, campoAporteMensal, campoTaxaJuros, campoMeses;
    private JButton botaoSimular;

    public BolsInves() {
        setTitle("Simulador de Investimentos");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Campos de entrada
        add(new JLabel("Nome do Ativo:"));
        campoAtivo = new JTextField();
        add(campoAtivo);

        add(new JLabel("Valor Inicial (R$):"));
        campoValorInicial = new JTextField();
        add(campoValorInicial);

        add(new JLabel("Aporte Mensal (R$):"));
        campoAporteMensal = new JTextField();
        add(campoAporteMensal);

        add(new JLabel("Taxa de Juros Mensal (%):"));
        campoTaxaJuros = new JTextField();
        add(campoTaxaJuros);

        add(new JLabel("Duração (meses):"));
        campoMeses = new JTextField();
        add(campoMeses);

        // Botão de simular
        botaoSimular = new JButton("Simular");
        add(botaoSimular);
        add(new JLabel()); // espaço em branco

        // Ação do botão
        botaoSimular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simularInvestimento();
            }
        });

        setVisible(true);
    }

    // Método para fazer o cálculo da simulação
    private void simularInvestimento() {
        try {
            String ativo = campoAtivo.getText();
            double valorInicial = Double.parseDouble(campoValorInicial.getText());
            double aporteMensal = Double.parseDouble(campoAporteMensal.getText());
            double taxaJuros = Double.parseDouble(campoTaxaJuros.getText()) / 100;
            int meses = Integer.parseInt(campoMeses.getText());

            double valorFinal = valorInicial * Math.pow(1 + taxaJuros, meses) +
                    aporteMensal * ((Math.pow(1 + taxaJuros, meses) - 1) / taxaJuros);

            double totalInvestido = valorInicial + (aporteMensal * meses);
            double rendimento = valorFinal - totalInvestido;

            String mensagem = "Ativo: " + ativo +
                    "\nValor Final: R$ " + String.format("%.2f", valorFinal) +
                    "\nTotal Investido: R$ " + String.format("%.2f", totalInvestido) +
                    "\nRendimento: R$ " + String.format("%.2f", rendimento);

            JOptionPane.showMessageDialog(this, mensagem, "Resultado da Simulação", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Verifique os dados digitados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new BolsInves();
    }
}
