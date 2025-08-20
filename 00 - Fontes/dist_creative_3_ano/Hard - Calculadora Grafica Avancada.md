O exercício inclui tarefas de personalização da interface, validação avançada e documentação, para atingir o tempo estipulado.

---

### **Exercício 4: Calculadora Gráfica Avançada com JFrame (Difícil)**

**Objetivo**: Criar uma calculadora gráfica completa com `JFrame` que suporte operações matemáticas avançadas, histórico de cálculos e interface personalizada.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` com título "Calculadora Avançada" (700x600 pixels).
   - Inclui dois campos de texto (`JTextField`) para entrada de números.
   - Oferece botões para operações: soma, subtração, multiplicação, divisão, potência e raiz quadrada (aplicada ao primeiro número).
   - Inclui um `JTextArea` para exibir o histórico de cálculos.
   - Adiciona botões "Calcular", "Limpar Campos" e "Limpar Histórico".
   - Exibe resultados em um `JOptionPane` e no histórico.
2. Requisitos adicionais:
   - Validar:
     - Entradas numéricas válidas.
     - Divisão por zero.
     - Raiz quadrada de números negativos.
   - Usar `GridBagLayout` para um layout mais sofisticado.
   - Personalizar a interface com:
     - Cores diferentes para botões (ex.: soma em verde, divisão em vermelho).
     - Ícones nos botões (ex.: ícones de operações matemáticas).
     - Borda no `JTextArea` e barra de rolagem (`JScrollPane`).
   - Salvar o histórico em um arquivo de texto ao fechar a aplicação.
   - Centralizar a janela e configurar fechamento correto.
3. Documentação:
   - Comentar o código detalhadamente.
   - Criar um diagrama UML (classe e caso de uso, à mão ou digital).
   - Escrever um relatório (2 páginas) descrevendo a implementação, desafios e possíveis melhorias.

**Código base (para orientação)**:
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CalculadoraAvancada extends JFrame {
    private JTextField campoNum1, campoNum2;
    private JTextArea historico;

    public CalculadoraAvancada() {
        setTitle("Calculadora Avançada");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        campoNum1 = new JTextField(10);
        campoNum2 = new JTextField(10);
        historico = new JTextArea(10, 30);
        historico.setEditable(false);
        JScrollPane scroll = new JScrollPane(historico);
        scroll.setBorder(BorderFactory.createTitledBorder("Histórico"));

        JButton botaoSoma = new JButton("+");
        botaoSoma.setBackground(Color.GREEN);
        JButton botaoSub = new JButton("-");
        botaoSub.setBackground(Color.YELLOW);
        JButton botaoMult = new JButton("*");
        botaoMult.setBackground(Color.CYAN);
        JButton botaoDiv = new JButton("/");
        botaoDiv.setBackground(Color.RED);
        JButton botaoPot = new JButton("^");
        botaoPot.setBackground(Color.ORANGE);
        JButton botaoRaiz = new JButton("√");
        botaoRaiz.setBackground(Color.MAGENTA);
        JButton botaoLimpar = new JButton("Limpar Campos");
        JButton botaoLimparHist = new JButton("Limpar Histórico");

        botaoSoma.addActionListener(e -> calcular("+"));
        botaoSub.addActionListener(e -> calcular("-"));
        botaoMult.addActionListener(e -> calcular("*"));
        botaoDiv.addActionListener(e -> calcular("/"));
        botaoPot.addActionListener(e -> calcular("^"));
        botaoRaiz.addActionListener(e -> calcular("√"));
        botaoLimpar.addActionListener(e -> limparCampos());
        botaoLimparHist.addActionListener(e -> historico.setText(""));

        // Layout com GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Número 1:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; add(campoNum1, gbc);
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Número 2:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(campoNum2, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; add(scroll, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 3; add(botaoSoma, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(botaoSub, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(botaoMult, gbc);
        gbc.gridx = 1; gbc.gridy = 4; add(botaoDiv, gbc);
        gbc.gridx = 0; gbc.gridy = 5; add(botaoPot, gbc);
        gbc.gridx = 1; gbc.gridy = 5; add(botaoRaiz, gbc);
        gbc.gridx = 0; gbc.gridy = 6; add(botaoLimpar, gbc);
        gbc.gridx = 1; gbc.gridy = 6; add(botaoLimparHist, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("historico.txt"))) {
                    writer.write(historico.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar histórico!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void calcular(String operacao) {
        try {
            double num1 = Double.parseDouble(campoNum1.getText());
            double num2 = operacao.equals("√") ? 0 : Double.parseDouble(campoNum2.getText());
            double resultado = 0;
            String mensagem = "";
            switch (operacao) {
                case "+": resultado = num1 + num2; mensagem = num1 + " + " + num2 + " = " + resultado; break;
                case "-": resultado = num1 - num2; mensagem = num1 + " - " + num2 + " = " + resultado; break;
                case "*": resultado = num1 * num2; mensagem = num1 + " * " + num2 + " = " + resultado; break;
                case "/":
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(null, "Divisão por zero não permitida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultado = num1 / num2; mensagem = num1 + " / " + num2 + " = " + resultado; break;
                case "^": resultado = Math.pow(num1, num2); mensagem = num1 + " ^ " + num2 + " = " + resultado; break;
                case "√":
                    if (num1 < 0) {
                        JOptionPane.showMessageDialog(null, "Raiz quadrada de número negativo não permitida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultado = Math.sqrt(num1); mensagem = "√" + num1 + " = " + resultado; break;
            }
            historico.append(mensagem + "\n");
            JOptionPane.showMessageDialog(null, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNum1.setText("");
        campoNum2.setText("");
    }

    public static void main(String[] args) {
        new CalculadoraAvancada();
    }
}
```

**Critérios de Avaliação**:
- [ ] Interface com todos os botões, campos e histórico funcional.
- [ ] Cálculos corretos para todas as operações, incluindo potência e raiz.
- [ ] Validações de entrada detalhadas.
- [ ] Personalização visual (cores, ícones, bordas).
- [ ] Salvamento do histórico em arquivo.
- [ ] Diagrama UML e relatório completos.

**Duração Estimada**: 2 horas.
- 60 min: Implementação da interface e lógica de cálculos.
- 40 min: Validações, personalização e salvamento do histórico.
- 20 min: Diagrama UML e relatório.

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