O exercício agora inclui tarefas adicionais, como personalização da interface, validação avançada e documentação, para atingir o tempo estipulado.

---

### **Exercício 2: Janela de Cadastro com JFrame (Fácil)**

**Objetivo**: Criar uma janela gráfica com `JFrame` para cadastrar informações de um aluno (nome, idade e nota) e exibir os dados em um `JOptionPane` ao clicar em um botão.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` para criar uma janela com título "Cadastro de Aluno" (500x400 pixels).
   - Inclui campos de texto (`JTextField`) para nome, idade e nota.
   - Adiciona um botão "Cadastrar" que exibe os dados em um `JOptionPane`.
   - Adiciona um botão "Limpar" que reseta os campos.
2. Requisitos adicionais:
   - Usar `GridLayout` ou `BorderLayout` para organizar os componentes.
   - Validar:
     - Nome não pode ser vazio.
     - Idade deve ser um número inteiro entre 10 e 100.
     - Nota deve ser um número decimal entre 0 e 10.
   - Exibir mensagens de erro específicas com `JOptionPane` se as validações falharem.
   - Personalizar a janela com fundo colorido (ex.: `setBackground(Color.LIGHT_GRAY)`) e fonte personalizada para rótulos.
   - Centralizar a janela e configurar fechamento correto.
3. Documentação:
   - Adicionar comentários no código.
   - Criar um diagrama simples (desenho à mão ou digital) do layout da janela.
   - Escrever um relatório (máximo 1 página) explicando o funcionamento e escolhas de design.

**Código base (para orientação)**:
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CadastroAluno extends JFrame {
    private JTextField campoNome, campoIdade, campoNota;

    public CadastroAluno() {
        setTitle("Cadastro de Aluno");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        campoNome = new JTextField(20);
        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setFont(new Font("Arial", Font.BOLD, 14));
        campoIdade = new JTextField(20);
        JLabel labelNota = new JLabel("Nota:");
        labelNota.setFont(new Font("Arial", Font.BOLD, 14));
        campoNota = new JTextField(20);
        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoLimpar = new JButton("Limpar");

        botaoCadastrar.addActionListener(e -> cadastrar());
        botaoLimpar.addActionListener(e -> limparCampos());

        add(labelNome); add(campoNome);
        add(labelIdade); add(campoIdade);
        add(labelNota); add(campoNota);
        add(botaoCadastrar); add(botaoLimpar);

        setVisible(true);
    }

    private void cadastrar() {
        try {
            String nome = campoNome.getText().trim();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int idade = Integer.parseInt(campoIdade.getText());
            if (idade < 10 || idade > 100) {
                JOptionPane.showMessageDialog(null, "Idade deve estar entre 10 e 100!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double nota = Double.parseDouble(campoNota.getText());
            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(null, "Nota deve estar entre 0 e 10!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nIdade: " + idade + "\nNota: " + nota, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Idade e nota devem ser números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoIdade.setText("");
        campoNota.setText("");
    }

    public static void main(String[] args) {
        new CadastroAluno();
    }
}
```

**Critérios de Avaliação**:
- [ ] Interface com campos de texto, rótulos e botões funcionais.
- [ ] Validações de entrada (nome, idade, nota).
- [ ] Personalização visual (cor, fonte).
- [ ] Diagrama do layout e relatório completo.
- [ ] Código organizado e comentado.

**Duração Estimada**: 2 horas.
- 50 min: Implementação da interface e lógica.
- 40 min: Validações e personalização.
- 30 min: Diagrama e relatório.

---

### **Organização da Atividade em Sala de Aula**

**Estrutura**:
- Cada exercício é projetado para ser realizado em uma aula de 2 horas, podendo ser distribuído em dias diferentes ou como uma sequência intensiva (8 horas no total).
- **Introdução (15 minutos por exercício)**:
  - Explique o objetivo, mostre o código base no projetor e discuta os conceitos envolvidos (ex.: layouts, eventos, validações).
- **Desenvolvimento (90 minutos por exercício)**:
  - Alunos trabalham individualmente ou em duplas.
  - Implementam a interface, lógica, validações e personalizações.
  - Testam a aplicação para garantir funcionamento.
- **Documentação e Conclusão (15 minutos por exercício)**:
  - Alunos finalizam o relatório e diagramas.
  - Professor revisa rapidamente algumas soluções no projetor, discutindo boas práticas e erros comuns.

**Materiais Necessários**:
- Computadores com IDE (NetBeans, IntelliJ ou Eclipse).
- Enunciados dos exercícios (impressos ou digitais).
- Papel ou ferramenta digital para diagramas (ex.: Lucidchart, Draw.io).
- Projetor para demonstrações.

**Dicas para o Professor**:
- Certifique-se de que os alunos dominem conceitos básicos de Java (eventos, entrada/saída, manipulação de arquivos).
- Para alunos mais rápidos, sugira melhorias (ex.: adicionar mais operações no Exercício 4, como logaritmo).
- Monitore o tempo para garantir que a documentação seja concluída.
- Incentive a criatividade na personalização visual (cores, ícones, fontes).

**Justificativa do Tempo**:
- Cada exercício foi expandido com validações detalhadas, personalização visual, funcionalidades extras (ex.: histórico, salvamento em arquivo) e documentação (relatórios, diagramas).
- A combinação de implementação, testes e documentação garante que cada exercício ocupe 2 horas, considerando o ritmo de alunos de Ensino Médio/Técnico.