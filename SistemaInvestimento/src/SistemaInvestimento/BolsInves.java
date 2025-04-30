package SistemaInvestimento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BolsInves extends JFrame {

    private JTextField campoValorInicial, campoTaxa, campoMeses;
    private JTextArea resultadoArea;

    public BolsInves() {
        super("Simulador de Investimentos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Componentes
        painel.add(new JLabel("Valor Inicial (R$):"));
        campoValorInicial = new JTextField();
        painel.add(campoValorInicial);

        painel.add(new JLabel("Taxa de Juros (% ao mês):"));
        campoTaxa = new JTextField();
        painel.add(campoTaxa);

        painel.add(new JLabel("Meses:"));
        campoMeses = new JTextField();
        painel.add(campoMeses);

        JButton botaoSimular = new JButton("Simular");
        JButton botaoLimpar = new JButton("Limpar");

        // Estilo dos botões
        botaoSimular.setBackground(Color.GREEN);
        botaoSimular.setForeground(Color.WHITE);
        botaoSimular.setFocusPainted(false);

        botaoLimpar.setBackground(Color.RED);
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);

        painel.add(botaoSimular);
        painel.add(botaoLimpar);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(resultadoArea);

        add(painel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Ação do botão Simular
        botaoSimular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simularInvestimento();
            }
        });

        // Ação do botão Limpar
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void simularInvestimento() {
        try {
            double valorInicial = Double.parseDouble(campoValorInicial.getText());
            double taxaMensal = Double.parseDouble(campoTaxa.getText()) / 100;
            int meses = Integer.parseInt(campoMeses.getText());

            if (valorInicial <= 0 || taxaMensal <= 0 || meses <= 0) {
                JOptionPane.showMessageDialog(this, "Insira valores positivos e diferentes de zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double montante = valorInicial;
            StringBuilder resultado = new StringBuilder();

            for (int i = 1; i <= meses; i++) {
                montante *= (1 + taxaMensal);
                resultado.append(String.format("Mês %2d: R$ %.2f\n", i, montante));
            }

            resultado.append("\n=============================\n");
            resultado.append("Valor investido: R$ ").append(String.format("%.2f", valorInicial)).append("\n");
            resultado.append("Montante final:   R$ ").append(String.format("%.2f", montante)).append("\n");
            resultado.append("Lucro total:      R$ ").append(String.format("%.2f", montante - valorInicial)).append("\n");

            resultadoArea.setText(resultado.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoValorInicial.setText("");
        campoTaxa.setText("");
        campoMeses.setText("");
        resultadoArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BolsInves().setVisible(true);
        });
    }
}
