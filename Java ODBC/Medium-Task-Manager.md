### Aplicação Medium: Gerenciador de Tarefas

Essa aplicação é de nível médio. Ela permite CRUD (Create, Read, Update, Delete) em uma tabela MySQL `tarefas` (colunas: id INT AUTO_INCREMENT PRIMARY KEY, descricao VARCHAR(255), concluida BOOLEAN). Usa uma JTable para exibir dados, com botões para adicionar, editar e deletar. Emprega POO com classe `Tarefa` (modelo) e `TarefaDAO` para abstrair o banco.

**Pré-requisitos:**
- Banco MySQL com database `gerenciador` e tabela `tarefas`.
- Driver JDBC MySQL.

**Código Sugerido (MainApp.java):**
```java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class MainApp {
    private static JTable table;
    private static DefaultTableModel model;
    private static TarefaDAO dao = new TarefaDAO();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gerenciador de Tarefas");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            model = new DefaultTableModel(new String[]{"ID", "Descrição", "Concluída"}, 0);
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel();
            JLabel lblDesc = new JLabel("Descrição:");
            JTextField txtDesc = new JTextField(20);
            JCheckBox chkConcluida = new JCheckBox("Concluída");
            JButton btnAdd = new JButton("Adicionar");
            JButton btnEdit = new JButton("Editar");
            JButton btnDelete = new JButton("Deletar");
            JButton btnRefresh = new JButton("Atualizar");

            panel.add(lblDesc);
            panel.add(txtDesc);
            panel.add(chkConcluida);
            panel.add(btnAdd);
            panel.add(btnEdit);
            panel.add(btnDelete);
            panel.add(btnRefresh);
            frame.add(panel, BorderLayout.SOUTH);

            btnAdd.addActionListener(e -> {
                Tarefa tarefa = new Tarefa(txtDesc.getText(), chkConcluida.isSelected());
                dao.adicionar(tarefa);
                atualizarTabela();
            });

            btnEdit.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int id = (int) model.getValueAt(row, 0);
                    Tarefa tarefa = new Tarefa(txtDesc.getText(), chkConcluida.isSelected());
                    dao.editar(id, tarefa);
                    atualizarTabela();
                }
            });

            btnDelete.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int id = (int) model.getValueAt(row, 0);
                    dao.deletar(id);
                    atualizarTabela();
                }
            });

            btnRefresh.addActionListener(e -> atualizarTabela());

            atualizarTabela();
            frame.setVisible(true);
        });
    }

    private static void atualizarTabela() {
        model.setRowCount(0);
        for (Tarefa t : dao.listar()) {
            model.addRow(new Object[]{t.getId(), t.getDescricao(), t.isConcluida()});
        }
    }
}

// Classe Modelo
class Tarefa {
    private int id;
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao, boolean concluida) {
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public boolean isConcluida() { return concluida; }
}

// Classe DAO
class TarefaDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador";
    private static final String USER = "root";
    private static final String PASS = "senha";

    public void adicionar(Tarefa tarefa) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isConcluida());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) tarefa.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar(int id, Tarefa tarefa) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("UPDATE tarefas SET descricao = ?, concluida = ? WHERE id = ?")) {
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isConcluida());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM tarefas WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Tarefa> listar() {
        Vector<Tarefa> tarefas = new Vector<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tarefas")) {
            while (rs.next()) {
                Tarefa t = new Tarefa(rs.getString("descricao"), rs.getBoolean("concluida"));
                t.setId(rs.getInt("id"));
                tarefas.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }
}
```