### **Exercício 1: Calculadora Interativa com JOptionPane (Muito Fácil)**

**Objetivo**: Criar uma aplicação que utiliza `JOptionPane` para realizar operações matemáticas básicas (soma, subtração, multiplicação e divisão) de forma interativa, com menu e validações detalhadas.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Apresenta um menu usando `JOptionPane.showOptionDialog` com as opções: "Soma", "Subtração", "Multiplicação", "Divisão" e "Sair".
   - Solicita dois números inteiros para a operação escolhida usando `JOptionPane.showInputDialog`.
   - Exibe o resultado da operação em uma caixa de diálogo com `JOptionPane.showMessageDialog`.
2. Requisitos adicionais:
   - Tratar erros de entrada (ex.: letras em vez de números) com `try-catch`.
   - Validar que os números estejam no intervalo de 0 a 1000; caso contrário, exibir mensagem de erro.
   - Para divisão, verificar se o divisor é zero e exibir mensagem específica.
   - Permitir que o usuário repita operações até escolher "Sair".
   - Personalizar as mensagens com ícones (ex.: `JOptionPane.INFORMATION_MESSAGE` para resultados, `JOptionPane.ERROR_MESSAGE` para erros).
3. Documentação:
   - Escrever comentários no código explicando cada seção.
   - Criar um arquivo de texto com um breve relatório (máximo 1 página) descrevendo como o programa funciona e os desafios enfrentados.

**Código base (para orientação)**:
```java
import javax.swing.JOptionPane;

public class CalculadoraInterativa {
    public static void main(String[] args) {
        String[] opcoes = {"Soma", "Subtração", "Multiplicação", "Divisão", "Sair"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma operação:", "Calculadora",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
            if (escolha == 4) break; // Sair
            try {
                String input1 = JOptionPane.showInputDialog("Digite o primeiro número (0-1000):");
                String input2 = JOptionPane.showInputDialog("Digite o segundo número (0-1000):");
                int num1 = Integer.parseInt(input1);
                int num2 = Integer.parseInt(input2);
                if (num1 < 0 || num1 > 1000 || num2 < 0 || num2 > 1000) {
                    JOptionPane.showMessageDialog(null, "Números devem estar entre 0 e 1000!", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                double resultado = 0;
                String operacao = "";
                switch (escolha) {
                    case 0: resultado = num1 + num2; operacao = "+"; break;
                    case 1: resultado = num1 - num2; operacao = "-"; break;
                    case 2: resultado = num1 * num2; operacao = "*"; break;
                    case 3:
                        if (num2 == 0) {
                            JOptionPane.showMessageDialog(null, "Divisão por zero não permitida!", "Erro", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        resultado = (double) num1 / num2; operacao = "/"; break;
                }
                JOptionPane.showMessageDialog(null, num1 + " " + operacao + " " + num2 + " = " + resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Digite apenas números inteiros!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        JOptionPane.showMessageDialog(null, "Programa finalizado!", "Fim", JOptionPane.INFORMATION_MESSAGE);
    }
}
```

**Critérios de Avaliação**:
- [ ] Menu funcional com todas as opções.
- [ ] Validações de intervalo (0-1000) e divisão por zero.
- [ ] Interface com ícones apropriados.
- [ ] Loop para repetir operações até "Sair".
- [ ] Relatório de documentação completo.

**Duração Estimada**: 2 horas.
- 40 min: Implementação do menu e lógica básica.
- 40 min: Validações e personalização da interface.
- 40 min: Testes e redação do relatório.

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

Essa reformulação mantém a progressão de dificuldade, aumenta o engajamento com tarefas criativas e assegura que cada exercício seja um desafio completo e enriquecedor.