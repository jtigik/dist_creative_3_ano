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