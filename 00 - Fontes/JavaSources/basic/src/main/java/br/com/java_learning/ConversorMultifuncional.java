package br.com.java_learning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConversorMultifuncional extends JFrame {

    private final JTextField campoValor;
    private final JLabel labelResultado;

    public ConversorMultifuncional() {
        setTitle("Conversor Multifuncional");
        setSize(320, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.white);

        // Componentes da interface
        labelResultado = new JLabel("Resultado: ");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 18));
        campoValor = new JTextField(10);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel painelBotoes = new JPanel(new FlowLayout());

        // Opcional Configura o painelBotoes com BoxLayout vertical
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        JButton botaoCtoF = new JButton("Celsius → Fahrenheit");
        JButton botaoFtoC = new JButton("Fahrenheit → Celsius");
        JButton botaoRtoD = new JButton("Real → Dólar");
        JButton botaoDtoR = new JButton("Dólar → Real");
        JButton botaoLimpar = new JButton("Limpar");

        botaoCtoF.addActionListener(e -> converter("CtoF"));
        botaoFtoC.addActionListener(e -> converter("FtoC"));
        botaoRtoD.addActionListener(e -> converter("RtoD"));
        botaoDtoR.addActionListener(e -> converter("DtoR"));
        botaoLimpar.addActionListener(e -> limpar());

        painelBotoes.add(botaoCtoF);
        painelBotoes.add(botaoFtoC);
        painelBotoes.add(botaoRtoD);
        painelBotoes.add(botaoDtoR);
        painelBotoes.add(botaoLimpar);

        // Opcional: Adiciona os botões com espaçamento de 8 pixels
        painelBotoes.add(Box.createHorizontalStrut(8));
        painelBotoes.add(botaoCtoF);
        painelBotoes.add(Box.createVerticalStrut(8)); // Espaçamento de 8 pixels
        painelBotoes.add(botaoFtoC);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoRtoD);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoDtoR);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(Box.createVerticalStrut(8));

        add(labelResultado, BorderLayout.NORTH);
        add(campoValor, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void converter(String tipo) {
        try {
            double valor = Double.parseDouble(campoValor.getText());
            double resultado;
            String message = "";

            switch (tipo) {
                case "CtoF":
                    resultado = (valor * 9 / 5) + 32;
                    message = String.format("%.1f °C = %.1f °F", valor, resultado);
                    break;
                case "FtoC":
                    resultado = (valor - 32) * 5 / 9;
                    message = String.format("%.1f °F = %.1f °C", valor, resultado);
                    break;
                case "RtoD":
                    resultado = valor / 5.25; // Exemplo de taxa de conversão
                    message = String.format("R$ %.2f = US$ %.2f", valor, resultado);
                    break;
                case "DtoR":
                    resultado = valor * 5.25; // Exemplo de taxa de conversão
                    message = String.format("US$ %.2f = R$ %.2f", valor, resultado);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de conversão desconhecido.");
            }
            labelResultado.setText("Resultado: " + message);
            JOptionPane.showMessageDialog(null, message, "Conversão", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IllegalArgumentException e) {
            // System.err.println("Erro ao converter: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao converter: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            labelResultado.setText("Resultado: Erro na conversão");
        }
    }

    private void limpar() {
        campoValor.setText("");
        labelResultado.setText("Resultado: ");
    }

    public static void main(String[] args) {
        new ConversorMultifuncional();
    }

}
