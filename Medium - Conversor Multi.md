O exercício inclui tarefas, como personalização da interface, validação avançada e documentação, para atingir o tempo estipulado.

---

### **Exercício 3: Conversor Multifuncional com JFrame (Médio)**

**Objetivo**: Criar uma aplicação com `JFrame` que permita converter temperaturas (Celsius ↔ Fahrenheit) e moedas (Real ↔ Dólar), com escolha via botões e validações avançadas.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` com título "Conversor Multifuncional" (320x270 pixels).
   - Inclui um campo de texto (`JTextField`) para entrada do valor.
   - Adiciona quatro botões: "Celsius para Fahrenheit", "Fahrenheit para Celsius", "Real para Dólar", "Dólar para Real".
   - Exibe o resultado da conversão em um `JOptionPane`.
2. Requisitos adicionais:
   - Fórmulas:
     - Celsius para Fahrenheit: `F = (C * 9/5) + 32`.
     - Fahrenheit para Celsius: `C = (F - 32) * 5/9`.
     - Real para Dólar: usar taxa fixa (ex.: 1 USD = 5.5 BRL).
   - Validar:
     - Entrada numérica válida.
     - Temperaturas em Celsius entre -100 e 100; em Fahrenheit entre -148 e 212.
     - Valores monetários positivos.
   - Adicionar um `JLabel` que exibe o resultado na própria janela, além do `JOptionPane`.
   - Usar `BorderLayout` para organizar componentes (entrada no centro, botões ao sul, resultado no norte).
   - Personalizar com cores, bordas e ícones nos botões (ex.: `ImageIcon`).
   - Adicionar um botão "Limpar" para resetar campos e resultados.
3. Documentação:
   - Comentar o código detalhadamente.
   - Criar um fluxograma (à mão ou digital) do processo de conversão.
   - Escrever um relatório (1-2 páginas) descrevendo o funcionamento, desafios e escolhas de design.

**Código base (para orientação)**:
```java
package br.com.java_learning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConversorMultifuncional extends JFrame {

    private final JTextField campoValor;
    private final JLabel labelResultado;

    public ConversorMultifuncional() {
        setTitle("Conversor Multifuncional");
        setSize(320, 270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.white);

        // Componentes da interface
        labelResultado = new JLabel("Resultado: ");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 18));
        campoValor = new JTextField(10);
        campoValor.setFont(new Font("Arial", Font.PLAIN, 16));
        JPanel painelBotoes = new JPanel(new FlowLayout());

        // Opcional Configura o painelBotoes com BoxLayout vertical
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        JButton botaoCtoF = new JButton("Celsius → Fahrenheit");
        JButton botaoFtoC = new JButton("Fahrenheit → Celsius");
        JButton botaoRtoD = new JButton("Real → Dólar");
        JButton botaoDtoR = new JButton("Dólar → Real");
        JButton botaoLimpar = new JButton("Limpar");

        botaoCtoF.addActionListener(e -> converter("CtoF"));
        botaoFtoC.addActionListener(e -> converter("FtoC"));
        botaoRtoD.addActionListener(e -> converter("RtoD"));
        botaoDtoR.addActionListener(e -> converter("DtoR"));
        botaoLimpar.addActionListener(e -> limpar());

        painelBotoes.add(botaoCtoF);
        painelBotoes.add(botaoFtoC);
        painelBotoes.add(botaoRtoD);
        painelBotoes.add(botaoDtoR);
        painelBotoes.add(botaoLimpar);

        // Opcional: Adiciona os botões com espaçamento de 8 pixels
        painelBotoes.add(Box.createHorizontalStrut(8));
        painelBotoes.add(botaoCtoF);
        painelBotoes.add(Box.createVerticalStrut(8)); // Espaçamento de 8 pixels
        painelBotoes.add(botaoFtoC);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoRtoD);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoDtoR);
        painelBotoes.add(Box.createVerticalStrut(8));
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(Box.createVerticalStrut(8));

        add(labelResultado, BorderLayout.NORTH);
        add(campoValor, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void converter(String tipo) {
        try {
            double valor = Double.parseDouble(campoValor.getText());
            double resultado;
            String message = "";

            switch (tipo) {
                case "CtoF":
                    resultado = (valor * 9 / 5) + 32;
                    message = String.format("%.1f °C = %.1f °F", valor, resultado);
                    break;
                case "FtoC":
                    resultado = (valor - 32) * 5 / 9;
                    message = String.format("%.1f °F = %.1f °C", valor, resultado);
                    break;
                case "RtoD":
                    resultado = valor / 5.25; // Exemplo de taxa de conversão
                    message = String.format("R$ %.2f = US$ %.2f", valor, resultado);
                    break;
                case "DtoR":
                    resultado = valor * 5.25; // Exemplo de taxa de conversão
                    message = String.format("US$ %.2f = R$ %.2f", valor, resultado);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de conversão desconhecido.");
            }
            labelResultado.setText("Resultado: " + message);
            JOptionPane.showMessageDialog(null, message, "Conversão", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IllegalArgumentException e) {
            // System.err.println("Erro ao converter: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao converter: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            labelResultado.setText("Resultado: Erro na conversão");
        }
    }

    private void limpar() {
        campoValor.setText("");
        labelResultado.setText("Resultado: ");
    }

    public static void main(String[] args) {
        new ConversorMultifuncional();
    }

}
```

**Critérios de Avaliação**:
- [ ] Interface com todos os botões e campo de texto.
- [ ] Conversões corretas para temperatura e moeda.
- [ ] Validações de entrada detalhadas.
- [ ] Personalização visual e funcionalidade de limpar.
- [ ] Fluxograma e relatório completos.

**Duração Estimada**: 2 horas.
- 50 min: Implementação da interface e lógica de conversão.
- 40 min: Validações, personalização e testes.
- 30 min: Fluxograma e relatório.

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

