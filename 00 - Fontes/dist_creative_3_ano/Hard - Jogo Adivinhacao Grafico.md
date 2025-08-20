Apresento um exercício prático, projetado para durar aproximadamente 2 horas, incluindo implementação, validação, personalização e documentação. Com objetivos claros, instruções detalhadas, código base para orientação e critérios de avaliação.

---

### **Exercício 4: Jogo de Adivinhação Gráfico com JFrame (Difícil)**

**Objetivo**: Criar um jogo gráfico com `JFrame` onde o usuário tenta adivinhar um número aleatório, com dicas, histórico de tentativas e pontuação.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` com título "Jogo de Adivinhação" (700x600 pixels).
   - Inclui um campo de texto (`JTextField`) para entrada do palpite.
   - Gera um número aleatório entre 1 e 100 (usar `Random`).
   - Adiciona botões: "Enviar Palpite", "Novo Jogo" e "Sair".
   - Exibe dicas em um `JLabel` (ex.: "Muito alto!", "Muito baixo!") e o histórico de palpites em um `JTextArea` com `JScrollPane`.
   - Calcula pontuação: 100 pontos iniciais, subtrai 10 por tentativa, zera ao acertar ou esgotar pontos.
   - Usa `JOptionPane` para mensagens de vitória, derrota ou erros.
2. Requisitos adicionais:
   - Validar:
     - Palpite deve ser um número entre 1 e 100.
     - Tratar entradas não numéricas.
   - Usar `GridBagLayout` para organizar componentes.
   - Personalizar com:
     - Cores diferentes para botões e fundo.
     - Ícones nos botões (ex.: ícone de dado para "Novo Jogo").
     - Borda no `JTextArea` e fonte personalizada.
   - Salvar o histórico de tentativas em um arquivo ao encerrar o jogo.
   - Adicionar um temporizador (`Timer`) que exibe o tempo decorrido no `JLabel`.
3. Documentação:
   - Comentar o código detalhadamente.
   - Criar um diagrama UML (classe e caso de uso).
   - Escrever um relatório (2 páginas) descrevendo o funcionamento, desafios e possíveis melhorias.

**Código base (para orientação)**:
```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class JogoAdivinhacao extends JFrame {
    private JTextField campoPalpite;
    private JLabel labelDica, labelTempo;
    private JTextArea historico;
    private int numeroSecreto, pontuacao, tempo;
    private Timer timer;

    public JogoAdivinhacao() {
        setTitle("Jogo de Adivinhação");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        campoPalpite = new JTextField(10);
        labelDica = new JLabel("Digite um palpite entre 1 e 100");
        labelDica.setFont(new Font("Arial", Font.BOLD, 16));
        labelTempo = new JLabel("Tempo: 0s");
        labelTempo.setFont(new Font("Arial", Font.BOLD, 16));
        historico = new JTextArea(10, 30);
        historico.setEditable(false);
        JScrollPane scroll = new JScrollPane(historico);
        scroll.setBorder(BorderFactory.createTitledBorder("Histórico"));
        JButton botaoPalpite = new JButton("Enviar Palpite");
        botaoPalpite.setBackground(Color.CYAN);
        JButton botaoNovo = new JButton("Novo Jogo");
        botaoNovo.setBackground(Color.GREEN);
        JButton botaoSair = new JButton("Sair");
        botaoSair.setBackground(Color.RED);

        botaoPalpite.addActionListener(e -> enviarPalpite());
        botaoNovo.addActionListener(e -> novoJogo());
        botaoSair.addActionListener(e -> sair());

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; add(labelDica, gbc);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; add(new JLabel("Palpite:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; add(campoPalpite, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; add(scroll, gbc);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; add(botaoPalpite, gbc);
        gbc.gridx = 1; gbc.gridy = 3; add(botaoNovo, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(labelTempo, gbc);
        gbc.gridx = 1; gbc.gridy = 4; add(botaoSair, gbc);

        novoJogo();
        timer = new Timer(1000, e -> {
            tempo++;
            labelTempo.setText("Tempo: " + tempo + "s");
        });
        timer.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("historico_jogo.txt"))) {
                    writer.write(historico.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar histórico!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    private void enviarPalpite() {
        try {
            int palpite = Integer.parseInt(campoPalpite.getText());
            if (palpite < 1 || palpite > 100) {
                JOptionPane.showMessageDialog(null, "Palpite deve estar entre 1 e 100!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            pontuacao -= 10;
            historico.append("Palpite: " + palpite + " (Pontuação: " + pontuacao + ")\n");
            if (palpite == numeroSecreto) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Parabéns! Você acertou! Pontuação final: " + pontuacao, "Vitória", JOptionPane.INFORMATION_MESSAGE);
                novoJogo();
            } else if (pontuacao <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Game Over! O número era " + numeroSecreto, "Derrota", JOptionPane.ERROR_MESSAGE);
                novoJogo();
            } else {
                labelDica.setText(palpite > numeroSecreto ? "Muito alto!" : "Muito baixo!");
            }
            campoPalpite.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void novoJogo() {
        numeroSecreto = new Random().nextInt(100) + 1;
        pontuacao = 100;
        tempo = 0;
        labelDica.setText("Digite um palpite entre 1 e 100");
        labelTempo.setText("Tempo: 0s");
        historico.setText("");
        campoPalpite.setText("");
        if (timer != null) timer.restart();
    }

    private void sair() {
        timer.stop();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historico_jogo.txt"))) {
            writer.write(historico.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar histórico!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new JogoAdivinhacao();
    }
}
```

**Critérios de Avaliação**:
- [ ] Interface com campo, botões, dicas e histórico funcional.
- [ ] Lógica de adivinhação com pontuação e temporizador.
- [ ] Validações de palpite e salvamento de histórico.
- [ ] Personalização visual (cores, ícones, bordas).
- [ ] Diagrama UML e relatório completos.

**Duração Estimada**: 2 horas.
- 60 min: Implementação da interface e lógica do jogo.
- 40 min: Validações, personalização e salvamento.
- 20 min: Diagrama UML e relatório.

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