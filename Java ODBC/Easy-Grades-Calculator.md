### Aplicação Easy: Calculadora de Notas Simples

Essa aplicação é de nível fácil. Ela consiste em uma janela Swing simples com campos para inserir o nome de um aluno e sua nota. Ao clicar em "Salvar", os dados são inseridos em uma tabela MySQL chamada `alunos`. Há também um botão para listar todos os alunos salvos em um JTextArea. Utiliza POO com uma classe `Aluno` (modelo) e uma classe `AlunoDAO` para operações de banco de dados.

**Pré-requisitos:**
- Banco MySQL com database `escola` e tabela `alunos` (colunas: id INT AUTO_INCREMENT PRIMARY KEY, nome VARCHAR(100), nota DOUBLE).
- Driver JDBC MySQL (adicionar ao classpath, ex: mysql-connector-java-8.0.x.jar).

**Código Sugerido (MainApp.java):**
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculadora de Notas Simples");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JLabel lblNome = new JLabel("Nome:");
            JTextField txtNome = new JTextField(20);
            JLabel lblNota = new JLabel("Nota:");
            JTextField txtNota = new JTextField(20);
            JButton btnSalvar = new JButton("Salvar");
            JButton btnListar = new JButton("Listar Alunos");
            JTextArea txtArea = new JTextArea(10, 30);
            txtArea.setEditable(false);

            frame.add(lblNome);
            frame.add(txtNome);
            frame.add(lblNota);
            frame.add(txtNota);
            frame.add(btnSalvar);
            frame.add(btnListar);
            frame.add(new JScrollPane(txtArea));

            AlunoDAO dao = new AlunoDAO();

            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = txtNome.getText();
                    double nota = Double.parseDouble(txtNota.getText());
                    Aluno aluno = new Aluno(nome, nota);
                    dao.salvar(aluno);
                    JOptionPane.showMessageDialog(frame, "Aluno salvo!");
                }
            });

            btnListar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtArea.setText(dao.listar());
                }
            });

            frame.setVisible(true);
        });
    }
}

// Classe Modelo (POO)
class Aluno {
    private String nome;
    private double nota;

    public Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }
}

// Classe DAO para operações no banco (POO)
class AlunoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/escola";
    private static final String USER = "root"; // Substitua pelo seu usuário
    private static final String PASS = "senha"; // Substitua pela sua senha

    public void salvar(Aluno aluno) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO alunos (nome, nota) VALUES (?, ?)")) {
            stmt.setString(1, aluno.getNome());
            stmt.setDouble(2, aluno.getNota());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM alunos")) {
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(", Nome: ").append(rs.getString("nome"))
                  .append(", Nota: ").append(rs.getDouble("nota")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
```