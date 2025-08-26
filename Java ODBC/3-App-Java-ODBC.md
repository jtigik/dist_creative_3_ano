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

### Aplicação Intermediate: Sistema de Biblioteca

Essa aplicação é de nível intermediário. Ela gerencia livros e autores com relacionamentos (tabelas MySQL: `autores` (id, nome), `livros` (id, titulo, autor_id FOREIGN KEY)). Usa múltiplos painéis Swing (abas com JTabbedPane) para cadastro de autores, livros e listagem com joins. Emprega POO com classes `Autor`, `Livro`, `AutorDAO` e `LivroDAO`.

**Pré-requisitos:**
- Banco MySQL com database `biblioteca` e tabelas `autores` e `livros` com foreign key.
- Driver JDBC MySQL.

**Código Sugerido (MainApp.java):**
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;

public class MainApp {
    private static AutorDAO autorDAO = new AutorDAO();
    private static LivroDAO livroDAO = new LivroDAO();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Biblioteca");
            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTabbedPane tabbedPane = new JTabbedPane();

            // Painel de Autores
            JPanel panelAutores = new JPanel(new FlowLayout());
            JLabel lblNomeAutor = new JLabel("Nome Autor:");
            JTextField txtNomeAutor = new JTextField(20);
            JButton btnAddAutor = new JButton("Adicionar Autor");
            JTextArea txtAutores = new JTextArea(10, 40);
            txtAutores.setEditable(false);
            panelAutores.add(lblNomeAutor);
            panelAutores.add(txtNomeAutor);
            panelAutores.add(btnAddAutor);
            panelAutores.add(new JScrollPane(txtAutores));

            btnAddAutor.addActionListener(e -> {
                Autor autor = new Autor(txtNomeAutor.getText());
                autorDAO.adicionar(autor);
                atualizarAutores(txtAutores);
            });

            // Painel de Livros
            JPanel panelLivros = new JPanel(new FlowLayout());
            JLabel lblTitulo = new JLabel("Título Livro:");
            JTextField txtTitulo = new JTextField(20);
            JLabel lblAutorId = new JLabel("ID Autor:");
            JTextField txtAutorId = new JTextField(5);
            JButton btnAddLivro = new JButton("Adicionar Livro");
            JTextArea txtLivros = new JTextArea(10, 40);
            txtLivros.setEditable(false);
            panelLivros.add(lblTitulo);
            panelLivros.add(txtTitulo);
            panelLivros.add(lblAutorId);
            panelLivros.add(txtAutorId);
            panelLivros.add(btnAddLivro);
            panelLivros.add(new JScrollPane(txtLivros));

            btnAddLivro.addActionListener(e -> {
                Livro livro = new Livro(txtTitulo.getText(), Integer.parseInt(txtAutorId.getText()));
                livroDAO.adicionar(livro);
                atualizarLivros(txtLivros);
            });

            tabbedPane.addTab("Autores", panelAutores);
            tabbedPane.addTab("Livros", panelLivros);

            frame.add(tabbedPane);
            atualizarAutores(txtAutores);
            atualizarLivros(txtLivros);
            frame.setVisible(true);
        });
    }

    private static void atualizarAutores(JTextArea txt) {
        txt.setText("");
        for (Autor a : autorDAO.listar()) {
            txt.append("ID: " + a.getId() + ", Nome: " + a.getNome() + "\n");
        }
    }

    private static void atualizarLivros(JTextArea txt) {
        txt.setText("");
        for (Livro l : livroDAO.listarComAutores()) {
            txt.append("ID: " + l.getId() + ", Título: " + l.getTitulo() + ", Autor: " + l.getAutorNome() + "\n");
        }
    }
}

// Classe Autor
class Autor {
    private int id;
    private String nome;

    public Autor(String nome) {
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
}

// Classe Livro
class Livro {
    private int id;
    private String titulo;
    private int autorId;
    private String autorNome; // Para join

    public Livro(String titulo, int autorId) {
        this.titulo = titulo;
        this.autorId = autorId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public int getAutorId() { return autorId; }
    public String getAutorNome() { return autorNome; }
    public void setAutorNome(String autorNome) { this.autorNome = autorNome; }
}

// DAO para Autor
class AutorDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "senha";

    public void adicionar(Autor autor) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO autores (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, autor.getNome());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) autor.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Autor> listar() {
        Vector<Autor> autores = new Vector<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM autores")) {
            while (rs.next()) {
                Autor a = new Autor(rs.getString("nome"));
                a.setId(rs.getInt("id"));
                autores.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }
}

// DAO para Livro
class LivroDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    private static final String USER = "root";
    private static final String PASS = "senha";

    public void adicionar(Livro livro) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO livros (titulo, autor_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAutorId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) livro.setId(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Livro> listarComAutores() {
        Vector<Livro> livros = new Vector<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT l.id, l.titulo, l.autor_id, a.nome AS autor_nome FROM livros l JOIN autores a ON l.autor_id = a.id")) {
            while (rs.next()) {
                Livro l = new Livro(rs.getString("titulo"), rs.getInt("autor_id"));
                l.setId(rs.getInt("id"));
                l.setAutorNome(rs.getString("autor_nome"));
                livros.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
}
```