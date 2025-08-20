package br.com.java_learning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SistemaVotacao extends JFrame {

    private JComboBox<String> comboCandidatos;
    private JLabel labelResultados;
    private int[] votos = new int[3]; // Candidato A, B, C

    public SistemaVotacao() {
        setTitle("Sistema de Votação");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Imagem de fundo (exemplo)
        JLabel fundo = new JLabel(new ImageIcon("./images/bar-bg-1845992362.png"));
        setContentPane(fundo);
        setLayout(new BorderLayout(10, 10));

        labelResultados = new JLabel("Votos: A=0, B=0, C=0");
        labelResultados.setFont(new Font("Arial", Font.BOLD, 16));
        String[] candidatos = {"Candidato A", "Candidato B", "Candidato C"};
        comboCandidatos = new JComboBox<>(candidatos);
        comboCandidatos.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JPanel painelCandidatos = new JPanel(new FlowLayout());
        JButton botaoVotar = new JButton("Votar");
        JButton botaoResultados = new JButton("Exibir Resultados");
        JButton botaoZerar = new JButton("Zerar Votação");

        botaoVotar.addActionListener(e -> votar());
        botaoResultados.addActionListener(e -> exibirResultados());
        botaoZerar.addActionListener(e -> zerarVotacao());

        painelCandidatos.add(comboCandidatos);
        painelBotoes.add(botaoVotar);
        painelBotoes.add(botaoResultados);
        painelBotoes.add(botaoZerar);

        add(labelResultados, BorderLayout.NORTH);
        add(painelCandidatos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void votar() {
        String candidato = (String) comboCandidatos.getSelectedItem();
        if (candidato == null) {
            JOptionPane.showMessageDialog(null, "Selecione um candidato!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Confirmar voto em " + candidato + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            switch (candidato) {
                case "Candidato A":
                    votos[0]++;
                    break;
                case "Candidato B":
                    votos[1]++;
                    break;
                case "Candidato C":
                    votos[2]++;
                    break;
            }
            labelResultados.setText("Votos: A=" + votos[0] + ", B=" + votos[1] + ", C=" + votos[2]);
            JOptionPane.showMessageDialog(null, "Voto registrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exibirResultados() {
        String resultado = "Resultados:\nCandidato A: " + votos[0] + "\nCandidato B: " + votos[1] + "\nCandidato C: " + votos[2];
        JOptionPane.showMessageDialog(null, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void zerarVotacao() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados_votacao.txt"))) {
            writer.write("Resultados:\nCandidato A: " + votos[0] + "\nCandidato B: " + votos[1] + "\nCandidato C: " + votos[2]);
            votos = new int[3];
            labelResultados.setText("Votos: A=0, B=0, C=0");
            JOptionPane.showMessageDialog(null, "Votação zerada e resultados salvos!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar resultados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new SistemaVotacao();
    }
}
