# 🏥 Sistema de Gerenciamento de Clínica Médica

## 📌 Sobre o Projeto
Este é um sistema desktop para clínicas médicas, desenvolvido em **Java Swing** com banco de dados **SQLite**. Ele permite o gerenciamento de pacientes, consultas e usuários, garantindo um controle eficiente das informações médicas.

## 🛠 Tecnologias Utilizadas
- **Java (Swing)** – Interface gráfica moderna e responsiva.
- **SQLite** – Banco de dados local para armazenar registros.
- **Maven** – Gerenciamento de dependências e build.
- **Arquitetura MVC** – Separação clara entre Model, View e Controller.

## 🔹 Funcionalidades
✅ Tela de Login e Cadastro de Usuários  
✅ Diferentes níveis de acesso para **Admin, Recepcionista e Médico**  
✅ Gerenciamento de Pacientes e Consultas  
✅ Conexão com Banco de Dados SQLite  
✅ Interface Responsiva e Moderna  
✅ Interface de Login e Cadastro melhorada com design moderno

## 🚀 Como Executar o Projeto
1. **Clone o repositório:**
   ```sh
   git clone https://github.com/pabloreis5/clinica-medica-system.git
   ```
2. **Abra o projeto no IntelliJ IDEA ou Eclipse.**
3. **Se necessário, instale as dependências do Maven:**
   ```sh
   mvn install
   ```
4. **Execute a classe principal `Main.java`.**

## 🏗 Configuração do Banco de Dados
- O banco **SQLite** (`clinica.db`) é criado automaticamente se não existir.
- Para visualizar e gerenciar as tabelas, conecte o banco ao IntelliJ na aba **Database**.

## 🔒 Segurança
- O banco de dados **não deve ser incluído no GitHub**. Certifique-se de adicionar `database/clinica.db` ao **`.gitignore`**.
- As senhas dos usuários devem ser armazenadas usando **hashing (ex: BCrypt)**.

## 📌 Como Contribuir
1. **Faça um Fork** do repositório.
2. **Crie uma branch** com a nova funcionalidade:
   ```sh
   git checkout -b minha-feature
   ```
3. **Commit suas mudanças:**
   ```sh
   git commit -m "Adicionando nova funcionalidade X"
   ```
4. **Envie para o repositório remoto:**
   ```sh
   git push origin minha-feature
   ```
5. **Abra um Pull Request** no GitHub.

## 📄 Licença
Projeto de código aberto sob a licença MIT.

---

Se tiver dúvidas ou sugestões, entre em contato! 🚀

