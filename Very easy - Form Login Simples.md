Apresento um exercício prático, projetado para durar aproximadamente 2 horas, incluindo implementação, validação, personalização e documentação. Com objetivos claros, instruções detalhadas, código base para orientação e critérios de avaliação.

---

### **Exercício 1: Formulário de Login Simples com JOptionPane (Muito Fácil)**

**Objetivo**: Criar uma aplicação que simule um sistema de login usando `JOptionPane`, com validação de usuário e senha, e um menu interativo para ações adicionais.

**Instruções**:
1. Desenvolva uma aplicação Java que:
   - Usa `JOptionPane.showInputDialog` para solicitar nome de usuário e senha.
   - Valida se o usuário é "admin" e a senha é "12345" (valores fixos).
   - Após login bem-sucedido, exibe um menu com `JOptionPane.showOptionDialog` com as opções: "Ver Mensagem", "Alterar Senha", "Sair".
   - Funcionalidades do menu:
     - "Ver Mensagem": exibe uma mensagem de boas-vindas com o nome do usuário.
     - "Alterar Senha": permite alterar a senha (armazenada em uma variável) e confirma a mudança.
     - "Sair": encerra o programa.
2. Requisitos adicionais:
   - Tratar entradas vazias ou nulas com mensagens de erro (`JOptionPane.ERROR_MESSAGE`).
   - Exibir mensagens personalizadas com ícones (ex.: `JOptionPane.INFORMATION_MESSAGE` para boas-vindas).
   - Limitar a 3 tentativas de login; após isso, exibir mensagem de bloqueio e encerrar.
   - Adicionar comentários detalhados no código.
3. Documentação:
   - Criar um arquivo de texto (máximo 1 página) descrevendo o funcionamento do programa e os desafios enfrentados.
   - Incluir um fluxograma simples (à mão ou digital) do processo de login e menu.

**Código base (para orientação)**:
```java
import javax.swing.JOptionPane;

public class FormularioLogin {
    private static String senha = "12345"; // Senha inicial
    public static void main(String[] args) {
        int tentativas = 0;
        while (tentativas < 3) {
            String usuario = JOptionPane.showInputDialog(null, "Digite o usuário:", "Login", JOptionPane.PLAIN_MESSAGE);
            if (usuario == null || usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Usuário não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            String inputSenha = JOptionPane.showInputDialog(null, "Digite a senha:", "Login", JOptionPane.PLAIN_MESSAGE);
            if (inputSenha == null || inputSenha.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Senha não pode ser vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            if (usuario.equals("admin") && inputSenha.equals(senha)) {
                menu(usuario);
                break;
            } else {
                tentativas++;
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos! Tentativas restantes: " + (3 - tentativas), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (tentativas >= 3) {
            JOptionPane.showMessageDialog(null, "Limite de tentativas excedido!", "Bloqueio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void menu(String usuario) {
        String[] opcoes = {"Ver Mensagem", "Alterar Senha", "Sair"};
        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Bem-vindo, " + usuario + "! Escolha uma opção:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
            if (escolha == 2) break; // Sair
            switch (escolha) {
                case 0: // Ver Mensagem
                    JOptionPane.showMessageDialog(null, "Olá, " + usuario + "! Bem-vindo ao sistema!", "Boas-vindas", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1: // Alterar Senha
                    String novaSenha = JOptionPane.showInputDialog(null, "Digite a nova senha:", "Alterar Senha", JOptionPane.PLAIN_MESSAGE);
                    if (novaSenha != null && !novaSenha.trim().isEmpty()) {
                        senha = novaSenha;
                        JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha não pode ser vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Programa finalizado!", "Fim", JOptionPane.INFORMATION_MESSAGE);
    }
}
```

**Critérios de Avaliação**:
- [ ] Login com validação de usuário e senha.
- [ ] Menu funcional com todas as opções.
- [ ] Limite de 3 tentativas e mensagens com ícones.
- [ ] Fluxograma e relatório completos.
- [ ] Código comentado.

**Duração Estimada**: 2 horas.
- 40 min: Implementação do login e validações.
- 40 min: Menu, funcionalidades e personalização.
- 40 min: Testes, fluxograma e relatório.

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