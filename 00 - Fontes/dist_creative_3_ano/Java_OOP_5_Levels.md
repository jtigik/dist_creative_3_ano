Abaixo, apresento cinco exercícios de Programação Orientada a Objetos (POO) em Java, com níveis de dificuldade crescente (Very Easy, Easy, Medium, Hard, Very Hard), adaptados para alunos do terceiro ano do Ensino Médio Profissionalizante - Desenvolvedor de Aplicativos. Os dois primeiros exercícios evitam Java Swing e camada de persistência, o terceiro introduz Java Swing, e os dois últimos combinam Java Swing com MySQL. Cada exercício inclui um enunciado claro e uma sugestão de código-fonte.

---

### Exercício 1: Very Easy - Classe Pessoa
**Enunciado**: Crie uma classe `Pessoa` que represente uma pessoa com atributos `nome` e `idade`. Inclua métodos para definir e obter esses atributos, além de um método que retorna uma mensagem com a apresentação da pessoa (ex.: "Oi, meu nome é Ana e tenho 17 anos!"). Crie uma classe principal para testar a criação de uma pessoa e exibir sua apresentação.

**Objetivo**: Introduzir conceitos básicos de classes, atributos, métodos e encapsulamento.

**Código-fonte**:

```java
// Classe Pessoa
public class Pessoa {
    private String nome;
    private int idade;

    // Construtor
    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Método de apresentação
    public String apresentar() {
        return "Oi, meu nome é " + nome + " e tenho " + idade + " anos!";
    }
}

// Classe principal para teste
public class Main {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa("Ana", 17);
        System.out.println(pessoa.apresentar());
    }
}
```

**Saída esperada**:
```
Oi, meu nome é Ana e tenho 17 anos!
```

---

### Exercício 2: Easy - Classe Conta Bancária
**Enunciado**: Crie uma classe `ContaBancaria` com atributos `titular` (nome do titular) e `saldo`. Implemente métodos para depositar, sacar (com validação para evitar saldo negativo) e consultar o saldo. Crie uma classe principal para testar a criação de uma conta, realizar depósitos e saques, e exibir o saldo final.

**Objetivo**: Reforçar encapsulamento, validação de dados e lógica condicional.

**Código-fonte**:

```java
// Classe ContaBancaria
public class ContaBancaria {
    private String titular;
    private double saldo;

    // Construtor
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Métodos
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Valor inválido para depósito!");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado com sucesso!");
        } else {
            System.out.println("Saque inválido: saldo insuficiente ou valor inválido!");
        }
    }

    public double consultarSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}

// Classe principal para teste
public class Main {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria("João", 1000.0);
        conta.depositar(500.0);
        conta.sacar(200.0);
        conta.sacar(2000.0); // Tentativa de saque inválido
        System.out.println("Saldo final de " + conta.getTitular() + ": R$" + conta.consultarSaldo());
    }
}
```

**Saída esperada**:
```
Depósito de R$500.0 realizado com sucesso!
Saque de R$200.0 realizado com sucesso!
Saque inválido: saldo insuficiente ou valor inválido!
Saldo final de João: R$1300.0
```

---

### Exercício 3: Medium - Cadastro de Alunos com Interface Gráfica
**Enunciado**: Crie uma aplicação com Java Swing para cadastrar alunos. A classe `Aluno` deve ter atributos `nome` e `nota`. A interface gráfica deve conter campos para inserir o nome e a nota do aluno, um botão para cadastrar e uma área de texto para exibir a lista de alunos cadastrados. A aplicação deve validar que a nota esteja entre 0 e 10.

**Objetivo**: Introduzir Java Swing e manipulação de eventos, mantendo a lógica de POO.

**Código-fonte**:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Classe Aluno
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

    @Override
    public String toString() {
        return "Nome: " + nome + ", Nota: " + nota;
    }
}

// Classe principal com interface gráfica
public class CadastroAlunos extends JFrame {
    private ArrayList<Aluno> alunos = new ArrayList<>();
    private JTextField txtNome, txtNota;
    private JTextArea txtArea;
    private JButton btnCadastrar;

    public CadastroAlunos() {
        // Configuração da janela
        setTitle("Cadastro de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes
        txtNome = new JTextField(20);
        txtNota = new JTextField(5);
        btnCadastrar = new JButton("Cadastrar");
        txtArea = new JTextArea(10, 30);
        txtArea.setEditable(false);

        // Adicionar componentes à janela
        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Nota:"));
        add(txtNota);
        add(btnCadastrar);
        add(new JScrollPane(txtArea));

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    double nota = Double.parseDouble(txtNota.getText());
                    if (nota >= 0 && nota <= 10) {
                        Aluno aluno = new Aluno(nome, nota);
                        alunos.add(aluno);
                        txtArea.append(aluno.toString() + "\n");
                        txtNome.setText("");
                        txtNota.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Nota deve estar entre 0 e 10!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nota inválida!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroAlunos frame = new CadastroAlunos();
            frame.setVisible(true);
        });
    }
}
```

**Saída esperada**: Uma janela gráfica com campos para nome e nota, um botão para cadastrar e uma área de texto que exibe os alunos cadastrados. Mensagens de erro aparecem se a nota for inválida.

---

### Exercício 4: Hard - Sistema de Biblioteca com Persistência em MySQL

**Enunciado**: Crie uma aplicação com Java Swing para gerenciar uma biblioteca. A classe `Livro` deve ter atributos `titulo`, `autor` e `ano`. A interface gráfica deve permitir cadastrar livros e exibir a lista de livros cadastrados. Os livros devem ser salvos em um banco de dados MySQL e carregados ao iniciar a aplicação.

**Código-fonte**:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

// Classe Livro
class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;

    public Livro(int id, String titulo, String autor, int ano) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Título: " + titulo + ", Autor: " + autor + ", Ano: " + ano;
    }
}

// Classe principal com interface gráfica
public class SistemaBiblioteca extends JFrame {
    private ArrayList<Livro> livros = new ArrayList<>();
    private JTextField txtTitulo, txtAutor, txtAno;
    private JTextArea txtArea;
    private JButton btnCadastrar;
    private Connection conn;

    public SistemaBiblioteca() {
        // Configuração da janela
        setTitle("Sistema de Biblioteca");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes
        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtAno = new JTextField(5);
        btnCadastrar = new JButton("Cadastrar");
        txtArea = new JTextArea(15, 30);
        txtArea.setEditable(false);

        // Adicionar componentes
        add(new JLabel("Título:"));
        add(txtTitulo);
        add(new JLabel("Autor:"));
        add(txtAutor);
        add(new JLabel("Ano:"));
        add(txtAno);
        add(btnCadastrar);
        add(new JScrollPane(txtArea));

        // Conectar ao banco de dados
        conectarBanco();
        carregarLivros();

        // Ação do botão
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = txtTitulo.getText();
                    String autor = txtAutor.getText();
                    int ano = Integer.parseInt(txtAno.getText());
                    salvarLivro(titulo, autor, ano);
                    carregarLivros();
                    txtTitulo.setText("");
                    txtAutor.setText("");
                    txtAno.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ano inválido!");
                }
            }
        });
    }

    private void conectarBanco() {
        try {
            // Substitua com suas credenciais do MySQL
            String url = "jdbc:mysql://localhost:3306/biblioteca?useSSL=false";
            String usuario = "root";
            String senha = "senha123";
            conn = DriverManager.getConnection(url, usuario, senha);

            // Criar tabela se não existir
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS livros (" +
                         "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                         "titulo VARCHAR(255)," +
                         "autor VARCHAR(255)," +
                         "ano INTEGER)";
            stmt.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    private void carregarLivros() {
        livros.clear();
        txtArea.setText("");
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM livros")) {
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getInt("ano"));
                livros.add(livro);
                txtArea.append(livro.toString() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar livros: " + e.getMessage());
        }
    }

    private void salvarLivro(String titulo, String autor, int ano) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)");
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.setInt(3, ano);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar livro: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaBiblioteca frame = new SistemaBiblioteca();
            frame.setVisible(true);
        });
    }
}
```

**Ajustes realizados**:
- Substituída a persistência em arquivo de texto por MySQL.
- Adicionada conexão ao banco MySQL com JDBC (URL: `jdbc:mysql://localhost:3306/biblioteca`).
- Criada tabela `livros` com colunas `id`, `titulo`, `autor` e `ano`.
- Operações de leitura (`carregarLivros`) e escrita (`salvarLivro`) usam consultas SQL.
- Mantida a mesma interface gráfica e funcionalidade.

**Pré-requisitos específicos**:
- Criar o banco `biblioteca` no MySQL:
  ```sql
  CREATE DATABASE biblioteca;
  ```
- Adicionar o driver MySQL ao projeto (ex.: `mysql-connector-java-8.0.28.jar`).
- Ajustar `usuario` e `senha` no método `conectarBanco` conforme a configuração do MySQL.

**Saída esperada**: Uma janela gráfica para cadastrar livros, com a lista de livros exibida e salva no banco MySQL. Ao reabrir a aplicação, os livros são carregados do banco.

---

### Exercício 5: Very Hard - Sistema de Gerenciamento de Tarefas com Persistência em MySQL

**Enunciado**: Crie uma aplicação com Java Swing para gerenciar tarefas. A classe `Tarefa` deve ter atributos `descricao`, `prioridade` (alta, média, baixa) e `concluida` (boolean). A interface gráfica deve permitir cadastrar, editar, marcar como concluída e excluir tarefas. As tarefas devem ser salvas em um banco de dados MySQL e carregadas ao iniciar a aplicação.

**Código-fonte**:

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

// Classe Tarefa
class Tarefa {
    private int id;
    private String descricao;
    private String prioridade;
    private boolean concluida;

    public Tarefa(int id, String descricao, String prioridade, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = concluida;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Prioridade: " + prioridade + ", Concluída: " + (concluida ? "Sim" : "Não");
    }
}

// Classe principal com interface gráfica
public class SistemaTarefas extends JFrame {
    private ArrayList<Tarefa> tarefas = new ArrayList<>();
    private JTextField txtDescricao;
    private JComboBox<String> cbPrioridade;
    private JTextArea txtArea;
    private JButton btnCadastrar, btnEditar, btnConcluir, btnExcluir;
    private Connection conn;

    public SistemaTarefas() {
        // Configuração da janela
        setTitle("Gerenciador de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes
        txtDescricao = new JTextField(20);
        cbPrioridade = new JComboBox<>(new String[]{"Alta", "Média", "Baixa"});
        btnCadastrar = new JButton("Cadastrar");
        btnEditar = new JButton("Editar");
        btnConcluir = new JButton("Marcar Concluída");
        btnExcluir = new JButton("Excluir");
        txtArea = new JTextArea(15, 40);
        txtArea.setEditable(false);

        // Adicionar componentes
        add(new JLabel("Descrição:"));
        add(txtDescricao);
        add(new JLabel("Prioridade:"));
        add(cbPrioridade);
        add(btnCadastrar);
        add(btnEditar);
        add(btnConcluir);
        add(btnExcluir);
        add(new JScrollPane(txtArea));

        // Conectar ao banco de dados
        conectarBanco();
        carregarTarefas();

        // Ações dos botões
        btnCadastrar.addActionListener(e -> cadastrarTarefa());
        btnEditar.addActionListener(e -> editarTarefa());
        btnConcluir.addActionListener(e -> marcarConcluida());
        btnExcluir.addActionListener(e -> excluirTarefa());
    }

    private void conectarBanco() {
        try {
            // Substitua com suas credenciais do MySQL
            String url = "jdbc:mysql://localhost:3306/tarefas?useSSL=false";
            String usuario = "root";
            String senha = "senha123";
            conn = DriverManager.getConnection(url, usuario, senha);

            // Criar tabela se não existir
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                         "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                         "descricao VARCHAR(255)," +
                         "prioridade VARCHAR(50)," +
                         "concluida BOOLEAN)";
            stmt.execute(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    private void carregarTarefas() {
        tarefas.clear();
        txtArea.setText("");
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM tarefas")) {
            while (rs.next()) {
                Tarefa tarefa = new Tarefa(rs.getInt("id"), rs.getString("descricao"), rs.getString("prioridade"), rs.getBoolean("concluida"));
                tarefas.add(tarefa);
                txtArea.append(tarefa.toString() + "\n");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar tarefas: " + e.getMessage());
        }
    }

    private void cadastrarTarefa() {
        try {
            String descricao = txtDescricao.getText();
            String prioridade = (String) cbPrioridade.getSelectedItem();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tarefas (descricao, prioridade, concluida) VALUES (?, ?, ?)");
            pstmt.setString(1, descricao);
            pstmt.setString(2, prioridade);
            pstmt.setBoolean(3, false);
            pstmt.executeUpdate();
            carregarTarefas();
            txtDescricao.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
        }
    }

    private void editarTarefa() {
        String idStr = JOptionPane.showInputDialog("Digite o ID da tarefa a editar:");
        try {
            int id = Integer.parseInt(idStr);
            String novaDescricao = txtDescricao.getText();
            String novaPrioridade = (String) cbPrioridade.getSelectedItem();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tarefas SET descricao = ?, prioridade = ? WHERE id = ?");
            pstmt.setString(1, novaDescricao);
            pstmt.setString(2, novaPrioridade);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            carregarTarefas();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e.getMessage());
        }
    }

    private void marcarConcluida() {
        String idStr = JOptionPane.showInputDialog("Digite o ID da tarefa a marcar como concluída:");
        try {
            int id = Integer.parseInt(idStr);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tarefas SET concluida = ? WHERE id = ?");
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            carregarTarefas();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao marcar concluída: " + e.getMessage());
        }
    }

    private void excluirTarefa() {
        String idStr = JOptionPane.showInputDialog("Digite o ID da tarefa a excluir:");
        try {
            int id = Integer.parseInt(idStr);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM tarefas WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            carregarTarefas();
        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaTarefas frame = new SistemaTarefas();
            frame.setVisible(true);
        });
    }
}
```

**Ajustes realizados**:
- Substituído SQLite por MySQL.
- Alterada a URL de conexão para `jdbc:mysql://localhost:3306/tarefas`.
- Criada tabela `tarefas` com colunas `id`, `descricao`, `prioridade` e `concluida`.
- Mantidas todas as operações CRUD (cadastrar, editar, marcar como concluída, excluir) com consultas SQL adaptadas para MySQL.
- Mantida a mesma interface gráfica e funcionalidade.

**Pré-requisitos específicos**:
- Criar o banco `tarefas` no MySQL:
  ```sql
  CREATE DATABASE tarefas;
  ```
- Adicionar o driver MySQL ao projeto.
- Ajustar `usuario` e `senha` no método `conectarBanco` conforme a configuração do MySQL.

**Saída esperada**: Uma janela gráfica para gerenciar tarefas, com opções para cadastrar, editar, marcar como concluída e excluir tarefas. As tarefas são salvas no banco MySQL e carregadas ao iniciar.

---

### Notas Adicionais
- **Configuração do MySQL**:
  - Certifique-se de que o MySQL está rodando na porta padrão (3306).
  - Crie os bancos `biblioteca` e `tarefas` antes de executar os programas.
  - Ajuste as credenciais (`usuario` e `senha`) no código conforme a configuração do seu MySQL.
- **Driver JDBC**: O `mysql-connector-java` deve ser incluído no classpath do projeto. Para IDEs como IntelliJ ou Eclipse, adicione o JAR como dependência. Para compilação manual, use:
  ```bash
  javac -cp .;mysql-connector-java-8.0.28.jar SistemaBiblioteca.java
  java -cp .;mysql-connector-java-8.0.28.jar SistemaBiblioteca
  ```
  (No Windows, use `;` como separador; no Linux/Mac, use `:`).
- **Adaptação para Ensino Médio**: Os códigos mantêm a simplicidade na lógica e interface, mas a configuração do MySQL pode exigir orientação adicional do professor para os alunos. Considere demonstrar a criação do banco e a adição do driver em aula.
- **Erro comum**: Se o MySQL não estiver configurado corretamente (banco inexistente, credenciais erradas, driver ausente), os alunos verão mensagens de erro via `JOptionPane`. Oriente-os a verificar a conexão e as dependências.

Se precisar de mais ajustes ou explicações, é só avisar!