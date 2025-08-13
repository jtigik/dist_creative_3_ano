Apresento um exercício prático, projetado para durar aproximadamente 2 horas, incluindo implementação, validação, personalização e documentação. Com objetivos claros, instruções detalhadas, código base para orientação e critérios de avaliação.

---

### **Exercício 3: Sistema de Votação com JFrame (Médio)**

**Objetivo**: Criar uma aplicação com `JFrame` que simule um sistema de votação para três candidatos, com contagem de votos e exibição de resultados.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JFrame` com título "Sistema de Votação" (600x500 pixels).
   - Inclui um `JComboBox` para selecionar um candidato (ex.: "Candidato A", "Candidato B", "Candidato C").
   - Adiciona botões: "Votar", "Exibir Resultados" e "Zerar Votação".
   - Usa `JOptionPane` para confirmar o voto e exibir resultados.
   - Exibe a contagem de votos em um `JLabel` atualizado dinamicamente.
2. Requisitos adicionais:
   - Armazenar a contagem de votos em variáveis ou um array.
   - Validar que um candidato foi selecionado antes de votar.
   - Confirmar cada voto com `JOptionPane.showConfirmDialog` antes de contabilizar.
   - Usar `BorderLayout` para organizar: `JComboBox` no centro, botões no sul, `JLabel` no norte.
   - Personalizar com cores, bordas e uma imagem de fundo (usar `JLabel` com `ImageIcon` como fundo).
   - Salvar os resultados em um arquivo de texto ao zerar a votação.
3. Documentação:
   - Comentar o código detalhadamente.
   - Criar um fluxograma do processo de votação.
   - Escrever um relatório (1-2 páginas) descrevendo o funcionamento e escolhas de design.

**Código base (para orientação)**:
```java
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SistemaVotacao extends JFrame {
    private JComboBox<String> comboCandidatos;
    private JLabel labelResultados;
    private int[] votos = new int[3]; // Candidato A, B, C

    public SistemaVotacao() {
        setTitle("Sistema de Votação");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Imagem de fundo (exemplo)
        JLabel fundo = new JLabel(new ImageIcon("fundo.jpg"));
        setContentPane(fundo);
        setLayout(new BorderLayout(10, 10));

        labelResultados = new JLabel("Votos: A=0, B=0, C=0");
        labelResultados.setFont(new Font("Arial", Font.BOLD, 16));
        String[] candidatos = {"Candidato A", "Candidato B", "Candidato C"};
        comboCandidatos = new JComboBox<>(candidatos);
        comboCandidatos.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JPanel painelCandidatos = new JPanel(new FlowLayout());
        JButton botaoVotar = new JButton("Votar");
        JButton botaoResultados = new JButton("Exibir Resultados");
        JButton botaoZerar = new JButton("Zerar Votação");

        botaoVotar.addActionListener(e -> votar());
        botaoResultados.addActionListener(e -> exibirResultados());
        botaoZerar.addActionListener(e -> zerarVotacao());

        painelCandidatos.add(comboCandidatos);
        painelBotoes.add(botaoVotar);
        painelBotoes.add(botaoResultados);
        painelBotoes.add(botaoZerar);

        add(labelResultados, BorderLayout.NORTH);
        add(painelCandidatos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void votar() {
        String candidato = (String) comboCandidatos.getSelectedItem();
        if (candidato == null) {
            JOptionPane.showMessageDialog(null, "Selecione um candidato!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null, "Confirmar voto em " + candidato + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            switch (candidato) {
                case "Candidato A": votos[0]++; break;
                case "Candidato B": votos[1]++; break;
                case "Candidato C": votos[2]++; break;
            }
            labelResultados.setText("Votos: A=" + votos[0] + ", B=" + votos[1] + ", C=" + votos[2]);
            JOptionPane.showMessageDialog(null, "Voto registrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void exibirResultados() {
        String resultado = "Resultados:\nCandidato A: " + votos[0] + "\nCandidato B: " + votos[1] + "\nCandidato C: " + votos[2];
        JOptionPane.showMessageDialog(null, resultado, "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    private void zerarVotacao() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultados_votacao.txt"))) {
            writer.write("Resultados:\nCandidato A: " + votos[0] + "\nCandidato B: " + votos[1] + "\nCandidato C: " + votos[2]);
            votos = new int[3];
            labelResultados.setText("Votos: A=0, B=0, C=0");
            JOptionPane.showMessageDialog(null, "Votação zerada e resultados salvos!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar resultados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new SistemaVotacao();
    }
}
```

**Critérios de Avaliação**:
- [ ] Interface com `JComboBox`, botões e `JLabel` funcional.
- [ ] Contagem e validação de votos.
- [ ] Confirmação de votos e salvamento de resultados.
- [ ] Personalização visual (cores, imagem de fundo).
- [ ] Fluxograma e relatório completos.

**Duração Estimada**: 2 horas.
- 50 min: Implementação da interface e lógica de votação.
- 40 min: Validações, personalização e salvamento.
- 30 min: Fluxograma e relatório.

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

