# 🖥️ Escalonador de Processos - SRT

[![Java](https://img.shields.io/badge/Java-21+-orange?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Status](https://img.shields.io/badge/Status-Ativo-brightgreen)]()
[![Licença](https://img.shields.io/badge/Licença-Acadêmico-blue)]()

## 📋 Descrição

Projeto acadêmico de **Sistemas Operacionais** que implementa um escalonador de processos utilizando o algoritmo **SRT (Shortest Remaining Time)**, também conhecido como preempção por tempo restante mais curto.

O algoritmo seleciona sempre o processo com menor tempo de execução restante, garantindo otimização no tempo médio de espera dos processos.

## 📁 Estrutura do Projeto

```
SistemasOperacionais/
├── src/
│   ├── Main.java                          # Classe principal com menu interativo
│   ├── Processo/
│   │   ├── DataProcess.java               # Modelo de dados do processo
│   │   ├── DataProcessTAD.java            # Interface TAD (Tipo Abstrato de Dados)
│   │   └── ProcessManipulation.java       # Gerenciamento de processos
│   └── SRT/
│       └── SRT.java                       # Implementação do escalonador SRT
├── out/                                    # Saída compilada (gerada automaticamente)
└── README.md                               # Este arquivo
```

## ⚙️ Funcionalidades

### Menu Principal
| Opção | Descrição |
|-------|-----------|
| **1** | ➕ Adicionar novos processos - Inserir processos com nome e tempo de execução |
| **2** | ▶️ Iniciar escalonamento - Executar o algoritmo SRT nos processos adicionados |
| **3** | 🚪 Sair - Encerrar o programa |

### 🎯 Algoritmo SRT
- ✓ Seleciona sempre o processo com **menor tempo restante**
- ✓ Decrementa o tempo em 1 unidade a cada iteração
- ✓ Move processos bloqueados para o final da fila
- ✓ Marca processos como finalizados quando tempo chega a 0
- ✓ Exibe tempo total de escalonamento

## 🚀 Como Compilar e Executar

### 📦 Pré-requisitos
- ☕ Java 21+ instalado
- 🖥️ PowerShell ou cmd no Windows

### 📍 Passos

**1️⃣ Navegue até a pasta do projeto:**

**2️⃣ Compile os arquivos:**
```powershell
mkdir out
javac -d out src\Main.java src\Processo\*.java src\SRT\*.java
```

**3️⃣ Execute o programa:**
```powershell
java -cp out Main
```

## 📊 Exemplo de Uso

```console
1 - Adicionar novos processos:
2 - Iniciar o processo de escalonamento
3 - Sair

Escolha uma opcao:1

Quantos processos voce deseja adicionar ?2
Informe o nome do processo:Processo A
Informe o tempo do processo:5
Informe o nome do processo:Processo B
Informe o tempo do processo:3

1 - Adicionar novos processos:
2 - Iniciar o processo de escalonamento
3 - Sair

Escolha uma opcao:2

processo atual Processo B Tempo restante 2
processo: Processo B finalizado
processo atual Processo A Tempo restante 4
processo atual Processo A Tempo restante 3
processo atual Processo A Tempo restante 2
processo atual Processo A Tempo restante 1
processo: Processo A finalizado
Tempo total : 5
```

## 🔍 Como Debugar

### 🎯 No VS Code
1. Abra o arquivo `Main.java`
2. Pressione `Ctrl+Shift+D` para abrir Debug
3. Clique em **"Run and Debug"**
4. Selecione **"Java"** como ambiente
5. Pressione `F5` para iniciar

| Atalho | Ação |
|--------|------|
| `F10` | ⏭️ Próxima linha |
| `F11` | ➡️ Entrar no método |
| `Shift+F11` | ⬅️ Sair do método |
| `F5` | ▶️ Continuar |

## 🏗️ Classes Principais

### `Main.java`
Gerencia a interface do usuário com menu interativo usando `Scanner`.

### `DataProcess.java`
Armazena informações de um processo:
- 📛 `nome` - Identificador do processo
- ⏱️ `tempo` - Tempo de execução restante
- 🔄 `status` - Estado do processo (E=Execução, B=Bloqueado, F=Finalizado)

### `ProcessManipulation.java`
Implementa a interface `DataProcessTAD` e gerencia:
- ➕ Adição de novos processos
- 🔍 Busca do próximo processo (menor tempo restante)
- 🔄 Atualização de status
- ❌ Remoção de processos finalizados

### `SRT.java`
Implementa o algoritmo de escalonamento SRT.

## ⚠️ Tratamento de Erros

O programa valida:
- ✅ Entrada numérica para tempo (evita `InputMismatchException`)
- ✅ Quantidade de processos antes de iniciar escalonamento
- ✅ Status de processos durante execução

## 📝 Notas Importantes

- 💾 Os processos são armazenados em um `HashMap` ordenado por menor tempo restante
- 🔄 O escalonador continua até que todos os processos sejam finalizados
- 📈 A saída mostra cada transição de processo e o tempo total gasto

---

## 👤 Autor
**Victor Moreira / Samuel Lemos /Lucas Carvalho**

## 📄 Licença
Uso acadêmico permitido
