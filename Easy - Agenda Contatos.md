Apresento um exercício prático, projetado para durar aproximadamente 2 horas, incluindo implementação, validação, personalização e documentação. Com objetivos claros, instruções detalhadas, código base para orientação e critérios de avaliação.

---

### **Exercício 2: Agenda de Contatos com JFrame (Fácil)**

**Objetivo**: Criar uma janela gráfica com `JFrame` para gerenciar uma agenda de contatos simples, permitindo adicionar e visualizar contatos.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` com título "Agenda de Contatos" (500x400 pixels).
   - Inclui campos de texto (`JTextField`) para nome, telefone e e-mail.
   - Adiciona botões: "Adicionar Contato" (armazena em uma lista), "Listar Contatos" (exibe em `JOptionPane`) e "Limpar Campos".
   - Usa `GridLayout` para organizar componentes.
2. Requisitos adicionais:
   - Validar:
     - Nome não pode ser vazio.
     - Telefone deve seguir o formato `(XX)XXXXX-XXXX` (usar regex simples ou tamanho fixo).
     - E-mail deve conter "@" e ".".
   - Armazenar contatos em um `ArrayList<String>` (cada contato como uma string formatada).
   - Personalizar a interface com fundo colorido, fontes personalizadas e bordas nos campos.
   - Centralizar a janela e configurar fechamento correto.
3. Documentação:
   - Comentar o código detalhadamente.
   - Criar um diagrama do layout da janela (à mão ou digital).
   - Escrever um relatório (1 página) explicando o funcionamento e escolhas de design.

**Código base (para orientação)**:
```java
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AgendaContatos extends JFrame {
    private JTextField campoNome, campoTelefone, campoEmail;
    private ArrayList<String> contatos = new ArrayList<>();

    public AgendaContatos() {
        setTitle("Agenda de Contatos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        campoNome = new JTextField(20);
        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(new Font("Arial", Font.BOLD, 14));
        campoTelefone = new JTextField(20);
        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 14));
        campoEmail = new JTextField(20);
        JButton botaoAdicionar = new JButton("Adicionar Contato");
        JButton botaoListar = new JButton("Listar Contatos");
        JButton botaoLimpar = new JButton("Limpar Campos");

        botaoAdicionar.addActionListener(e -> adicionarContato());
        botaoListar.addActionListener(e -> listarContatos());
        botaoLimpar.addActionListener(e -> limparCampos());

        add(labelNome); add(campoNome);
        add(labelTelefone); add(campoTelefone);
        add(labelEmail); add(campoEmail);
        add(botaoAdicionar); add(botaoListar);
        add(botaoLimpar);

        setVisible(true);
    }

    private void adicionarContato() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();
        String email = campoEmail.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!telefone.matches("\\(\\d{2}\\)\\d{5}-\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Telefone deve seguir o formato (XX)XXXXX-XXXX!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "E-mail inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String contato = "Nome: " + nome + ", Telefone: " + telefone + ", E-mail: " + email;
        contatos.add(contato);
        JOptionPane.showMessageDialog(null, "Contato adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        limparCampos();
    }

    private void listarContatos() {
        if (contatos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum contato cadastrado!", "Agenda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder lista = new StringBuilder("Lista de Contatos:\n");
            for (String contato : contatos) {
                lista.append(contato).append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
    }

    public static void main(String[] args) {
        new AgendaContatos();
    }
}
```

**Critérios de Avaliação**:
- [ ] Interface com campos e botões funcionais.
- [ ] Validações de nome, telefone e e-mail.
- [ ] Armazenamento e listagem de contatos.
- [ ] Personalização visual (cores, fontes, bordas).
- [ ] Diagrama do layout e relatório completos.

**Duração Estimada**: 2 horas.
- 50 min: Implementação da interface e lógica de adição/listagem.
- 40 min: Validações e personalização.
- 30 min: Diagrama e relatório.

---

### **Organização da Atividade em Sala de Aula**

**Estrutura**:
- Cada exercício é projetado para 2 horas, podendo ser distribuído em aulas separadas.
- **Introdução (15 minutos por exercício)**:
  - Apresente o objetivo, mostre o código base no projetor e explique conceitos (ex.: `JComboBox`, `GridBagLayout`, `Timer`).
- **Desenvolvimento (90 minutos por exercício)**:
  - Alunos trabalham individualmente ou em duplas.
  - Implementam interface, lógica, validações e personalizações.
  - Testam a aplicação para corrigir erros.
- **Documentação e Conclusão (15 minutos por exercício)**:
  - Alunos finalizam relatórios e diagramas.
  - Professor revisa algumas soluções, destacando boas práticas.

**Materiais Necessários**:
- Computadores com IDE (NetBeans, IntelliJ, Eclipse).
- Enunciados dos exercícios (impressos ou digitais).
- Papel ou ferramenta digital para diagramas (ex.: Draw.io).
- Imagens para personalização (ex.: ícones, fundos).
- Projetor para demonstrações.

**Dicas para o Professor**:
- Verifique se os alunos conhecem Java básico (eventos, coleções, manipulação de arquivos).
- Incentive personalizações criativas (ex.: sons no jogo, imagens de candidatos).
- Monitore o tempo para garantir a conclusão da documentação.
- Para alunos avançados, sugira melhorias (ex.: carregar contatos de um arquivo no Exercício 2).

**Justificativa do Tempo**:
- O exercício inclui implementação complexa (ex.: regex, temporizador, salvamento em arquivo), validações detalhadas, personalização visual e documentação (relatórios, diagramas).
- O tempo é distribuído para equilibrar codificação, testes e documentação, adequando-se ao nível dos alunos.