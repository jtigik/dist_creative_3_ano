package br.com.java_learning;

import javax.swing.JOptionPane;

public class FormularioLogin {

    private static String pass = "123456";

    public static void main(String[] args) {
        int tentativas = 0;
        while (tentativas < 3) {
            String senha = JOptionPane.showInputDialog("Digite a senha:");
            if (senha.equals(pass)) {
                JOptionPane.showMessageDialog(null, "Acesso permitido!");
                break;
            } else {
                tentativas++;
                if (tentativas < 3) {
                    JOptionPane.showMessageDialog(null, "Senha incorreta! Tente novamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Número máximo de tentativas atingido. Acesso negado.");
                }
            }
        }
    }
}
