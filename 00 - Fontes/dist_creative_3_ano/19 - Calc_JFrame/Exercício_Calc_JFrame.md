**Exercício de Fixação: Criando uma Calculadora Gráfica com JOptionPane e JFrame**

**Objetivo**: Desenvolver uma aplicação gráfica em Java utilizando as classes `JFrame` e `JOptionPane` para criar uma calculadora simples que realize operações básicas (soma, subtração, multiplicação e divisão). A atividade visa reforçar conceitos de interfaces gráficas, entrada/saída de dados e lógica de programação.

**Nível**: Ensino Médio/Técnico de Informática  
**Duração**: 2 horas  
**Pré-requisitos**: Conhecimento básico de Java, incluindo variáveis, estruturas condicionais, loops e classes. Familiaridade com `JFrame` e `JOptionPane`.

---

### **Descrição da Atividade**

Os alunos irão criar uma aplicação gráfica que exibe uma janela principal (`JFrame`) com um menu inicial e utiliza caixas de diálogo (`JOptionPane`) para entrada de dados e exibição de resultados. A calculadora permitirá ao usuário:
1. Escolher uma operação matemática (soma, subtração, multiplicação ou divisão).
2. Inserir dois números.
3. Exibir o resultado da operação selecionada.

---

### **Instruções para os Alunos**

1. **Organização da Atividade**:
   - **Primeiros 30 minutos**: Explicação do código base e planejamento da solução.
   - **Próximos 90 minutos**: Desenvolvimento do código e testes.
   - **Últimos 30 minutos**: Apresentação dos resultados e discussão em grupo.

2. **Especificações do Programa**:
   - Crie uma janela principal usando `JFrame` com o título "Calculadora Gráfica".
   - Adicione um rótulo (`JLabel`) com a mensagem "Bem-vindo à Calculadora!" e um botão (`JButton`) com o texto "Iniciar Calculadora".
   - Ao clicar no botão, exiba uma caixa de diálogo (`JOptionPane`) com um menu para escolher a operação (1 - Soma, 2 - Subtração, 3 - Multiplicação, 4 - Divisão).
   - Solicite dois números via `JOptionPane` (use `showInputDialog`).
   - Realize a operação selecionada e exiba o resultado em outra caixa de diálogo.
   - Trate possíveis erros, como:
     - Entrada de valores não numéricos.
     - Divisão por zero.
   - A janela principal deve permanecer aberta até o usuário fechá-la.

3. **Requisitos Técnicos**:
   - Use `JFrame` para criar a janela principal.
   - Use `JOptionPane` para entrada de dados e exibição de mensagens.
   - Implemente tratamento de exceções com `try-catch`.
   - O código deve ser organizado em métodos para facilitar a leitura (ex.: um método para cada operação).

4. **Desafios Extras (Opcional)**:
   - Adicione botões para cada operação diretamente no `JFrame`.
   - Inclua uma opção para repetir a operação sem fechar o programa.
   - Estilize a janela com cores ou layouts personalizados (ex.: use `setBackground` ou `BorderLayout`).

---

### **Código Base (Fornecido aos Alunos)**

```java
import javax.swing.*;
import java.awt.event.*;

public class CalculadoraGrafica extends JFrame {
    public CalculadoraGrafica() {
        // Configurações da janela principal
        setTitle("Calculadora Gráfica");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        // Layout e componentes
        setLayout(null); // Layout manual para simplicidade
        JLabel label = new JLabel("Bem-vindo à Calculadora!");
        label.setBounds(50, 20, 200, 30);
        add(label);

        JButton botaoIniciar = new JButton("Iniciar Calculadora");
        botaoIniciar.setBounds(50, 80, 150, 30);
        add(botaoIniciar);

        // Ação do botão
        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica aqui
                JOptionPane.showMessageDialog(null, "Iniciando a calculadora...");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculadoraGrafica();
    }
}
```

---

### **Passo a Passo Sugerido**

1. **Explicação Inicial (30 minutos)**:
   - O professor explica o código base, destacando:
     - Estrutura do `JFrame` (janela, título, tamanho, etc.).
     - Uso do `JOptionPane` para entrada/saída.
     - Estrutura do evento do botão (`ActionListener`).
   - Discuta como organizar a lógica da calculadora (ex.: métodos para cada operação).
   - Apresente exemplos de tratamento de erros (ex.: `NumberFormatException`).

2. **Desenvolvimento (90 minutos)**:
   - **Passo 1**: Modifique o método `actionPerformed` para exibir um `JOptionPane` com as opções de operação (1 a 4).
   - **Passo 2**: Use `JOptionPane.showInputDialog` para coletar dois números do usuário.
   - **Passo 3**: Crie métodos para cada operação (soma, subtração, etc.) e use uma estrutura `switch` ou `if` para selecionar a operação.
   - **Passo 4**: Implemente tratamento de erros para entradas inválidas e divisão por zero.
   - **Passo 5**: Exiba o resultado com `JOptionPane.showMessageDialog`.

3. **Testes e Depuração**:
   - Teste o programa com diferentes entradas (números válidos, inválidos, zero na divisão, etc.).
   - Verifique se a janela principal permanece funcional após cada cálculo.

4. **Apresentação e Discussão (30 minutos)**:
   - Cada grupo apresenta sua solução, explicando:
     - Como implementou a lógica.
     - Como tratou os erros.
     - Quais desafios extras (se algum) foram implementados.
   - O professor fornece feedback e sugere melhorias.

---

### **Exemplo de Solução Completa**

```java
import javax.swing.*;
import java.awt.event.*;

public class CalculadoraGrafica extends JFrame {
    public CalculadoraGrafica() {
        setTitle("Calculadora Gráfica");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bem-vindo à Calculadora!");
        label.setBounds(50, 20, 200, 30);
        add(label);

        JButton botaoIniciar = new JButton("Iniciar Calculadora");
        botaoIniciar.setBounds(50, 80, 150, 30);
        add(botaoIniciar);

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String opcao = JOptionPane.showInputDialog("Escolha a operação:\n1 - Soma\n2 - Subtração\n3 - Multiplicação\n4 - Divisão");
                    int escolha = Integer.parseInt(opcao);

                    String input1 = JOptionPane.showInputDialog("Digite o primeiro número:");
                    double num1 = Double.parseDouble(input1);
                    String input2 = JOptionPane.showInputDialog("Digite o segundo número:");
                    double num2 = Double.parseDouble(input2);

                    double resultado = 0;
                    String operacao = "";
                    switch (escolha) {
                        case 1:
                            resultado = somar(num1, num2);
                            operacao = "Soma";
                            break;
                        case 2:
                            resultado = subtrair(num1, num2);
                            operacao = "Subtração";
                            break;
                        case 3:
                            resultado = multiplicar(num1, num2);
                            operacao = "Multiplicação";
                            break;
                        case 4:
                            if (num2 == 0) {
                                throw new ArithmeticException("Divisão por zero!");
                            }
                            resultado = dividir(num1, num2);
                            operacao = "Divisão";
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operação inválida!");
                            return;
                    }
                    JOptionPane.showMessageDialog(null, operacao + ": " + num1 + " e " + num2 + " = " + resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: Insira números válidos!");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    private double somar(double a, double b) {
        return a + b;
    }

    private double subtrair(double a, double b) {
        return a - b;
    }

    private double multiplicar(double a, double b) {
        return a * b;
    }

    private double dividir(double a, double b) {
        return a / b;
    }

    public static void main(String[] args) {
        new CalculadoraGrafica();
    }
}
```

---

### **Critérios de Avaliação**

- **Funcionalidade (50%)**: O programa realiza todas as operações corretamente e exibe os resultados esperados.
- **Tratamento de Erros (20%)**: O programa lida adequadamente com entradas inválidas e divisão por zero.
- **Organização do Código (20%)**: Uso de métodos, comentários e estrutura clara.
- **Criatividade (10%)**: Implementação de desafios extras ou personalizações visuais.

---

### **Recursos Necessários**

- Computadores com IDE instalada (ex.: Eclipse, IntelliJ IDEA ou NetBeans).
- JDK instalado (versão 8 ou superior).
- Slides ou quadro para explicação inicial.
- Exemplo do código base salvo em formato `.java` para distribuição.

---

Essa atividade combina prática de programação com conceitos de interfaces gráficas, promovendo aprendizado ativo e colaborativo. O tempo de 2 horas é suficiente para a explicação, desenvolvimento e discussão, com espaço para os alunos explorarem criatividade nos desafios extras.