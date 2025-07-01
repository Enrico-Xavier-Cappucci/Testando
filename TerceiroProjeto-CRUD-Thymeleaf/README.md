# Sistema de Gerenciamento de Empregados - Melhorado

## Melhorias Implementadas

### 1. Spring Security
- Adicionado Spring Security para autenticação e autorização
- Sistema de login e logout funcional
- Proteção de todas as rotas (exceto login e registro)

### 2. Sistema de Usuários
- Entidade User para gerenciar administradores
- Criptografia de senhas com BCrypt
- Validação de usuários únicos

### 3. Interface Melhorada
- Design moderno com gradientes e animações
- Páginas responsivas com Bootstrap 5
- Validação de formulários em tempo real
- Confirmação para ações de exclusão

### 4. Funcionalidades de Segurança
- Tela de login com validação de erros
- Tela de registro para novos administradores
- Logout seguro com invalidação de sessão
- Mensagens de erro e sucesso

### 5. Correções de Bugs
- Corrigido nome do arquivo "novo empregado.html" para "novo_empregado.html"
- Melhorada a paginação com navegação mais intuitiva
- Adicionada confirmação para exclusão de empregados

## Como Usar

1. **Primeiro Acesso:**
   - Acesse `/registration` para criar o primeiro usuário administrador
   - Faça login com as credenciais criadas

2. **Funcionalidades:**
   - Listar empregados com paginação e ordenação
   - Adicionar novos empregados
   - Editar empregados existentes
   - Excluir empregados (com confirmação)
   - Logout seguro

## Configuração do Banco de Dados

Certifique-se de configurar o `application.properties` com suas credenciais do MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

## Tecnologias Utilizadas

- Spring Boot 3.5.0
- Spring Security 6
- Spring Data JPA
- Thymeleaf
- Bootstrap 5
- MySQL
- BCrypt para criptografia

