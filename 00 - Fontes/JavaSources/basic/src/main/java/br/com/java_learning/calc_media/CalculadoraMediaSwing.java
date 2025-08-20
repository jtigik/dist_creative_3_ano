package br.com.java_learning.calc_media;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CalculadoraMediaSwing extends JFrame {

    private final JTextField txtNome, txtNota1, txtNota2, txtNota3;
    private final JTextArea txtResultado;

    public CalculadoraMediaSwing() {
        //Configuração da interface gráfica
        setTitle("Calculadora de Média");
        setSize(300, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Criação dos componentes
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 150, 25);

        JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(20, 50, 80, 25);

        txtNota1 = new JTextField();
        txtNota1.setBounds(100, 50, 150, 25);

        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(20, 80, 80, 25);

        txtNota2 = new JTextField();
        txtNota2.setBounds(100, 80, 150, 25);

        JLabel lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(20, 110, 80, 25);

        txtNota3 = new JTextField();
        txtNota3.setBounds(100, 110, 150, 25);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setBounds(100, 140, 100, 30);

        txtResultado = new JTextArea();
        txtResultado.setBounds(20, 180, 230, 50);
        txtResultado.setEditable(false);

        // Ação do botão Calcular
        btnCalcular.addActionListener(e -> {
            try {
                String nome = txtNome.getText();
                double nota1 = Double.parseDouble(txtNota1.getText());
                double nota2 = Double.parseDouble(txtNota2.getText());
                double nota3 = Double.parseDouble(txtNota3.getText());

                Aluno aluno = new Aluno(nome, nota1, nota2, nota3);
                String resultado = aluno.toString();
                txtResultado.setText(resultado);

                try (FileWriter writer = new FileWriter("MediaAluno.txt", true)) {
                    writer.write(resultado + "\n");
                } catch (IOException ex) {
                    txtResultado.setText("Erro ao salvar o arquivo: " + ex.getMessage());
                }

            } catch (NumberFormatException ex) {
                txtResultado.setText("Por favor, insira valores válidos para as notas.");
            }
        });

        // Adiciona componentes à janela
        add(lblNome);
        add(txtNome);
        add(lblNota1);
        add(txtNota1);
        add(lblNota2);
        add(txtNota2);
        add(lblNota3);
        add(txtNota3);
        add(btnCalcular);
        add(txtResultado);
    }

    public static void main(String[] args) {
        CalculadoraMediaSwing calculadora = new CalculadoraMediaSwing();
        calculadora.setVisible(true);
    }
}
