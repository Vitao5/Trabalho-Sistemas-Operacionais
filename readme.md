# 🖥️ Escalonador de Processos - SRT e Prioridade

[![Java](https://img.shields.io/badge/Java-21+-orange?logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Status](https://img.shields.io/badge/Status-Ativo-brightgreen)]()
[![Licença](https://img.shields.io/badge/Licença-Acadêmico-blue)]()

## 📋 Descrição

Projeto de **Sistemas Operacionais** que implementa um escalonador de processos utilizando os algoritmos **SRT (Shortest Remaining Time)** e **Escalonamento por Prioridade** com técnica de envelhecimento (*aging*).

A ferramenta permite a simulação da entrada de processos na CPU, considerando tempos de chegada, execução e níveis de prioridade, além de gerar relatórios de espera e gráficos de Gantt textuais para análise de desempenho.

## 📁 Estrutura do Projeto

O projeto adota a arquitetura e padronização do Maven.

SistemasOperacionais/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           ├── Processo/
│           │   ├── DataProcess.java
│           │   ├── DataProcessTAD.java
│           │   └── ProcessManipulation.java
│           ├── SRT/
│           │   └── SRT.java
│           └── com/mycompany/escalonamentotrabalho/
│               ├── EscalonamentoTrabalho.java
│               └── Prioridade/
│                   └── Prioridade.java
└── README.md

## ⚙️ Funcionalidades

### Menu Principal
| Opção | Descrição |
|-------|-----------|
| **1** | Adicionar processos (Nome, Tempo, Chegada e Prioridade) |
| **2** | Iniciar escalonamento SRT |
| **3** | Iniciar escalonamento Prioridade |
| **4** | Sair |

### 🎯 Algoritmos Implementados

**SRT (Shortest Remaining Time):**
- Seleciona o processo com menor tempo restante.
- Preemptivo.

**Prioridade (com Aging):**
- Seleciona o processo baseado no nível de prioridade (menor número = maior prioridade).
- Implementa *aging*: a cada três ciclos de espera na fila, o processo ganha prioridade para evitar inanição (*starvation*).

### 📊 Observabilidade
- Geração no terminal ao final da execução.
- Relatório detalhado do tempo total de espera de cada processo finalizado.

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
==========================  MENU  ==========================

1 - Adicionar processos
2 - Iniciar escalonamento SRT
3 - Iniciar escalonamento Prioridade
4 - Visualizar relatorio de espera
5 - Sair

Escolha uma opcao: 1

Quantos processos deseja adicionar? 2

Informe o nome: A
Informe o tempo do processo: 5
Informe o tempo de chegada: 0
Informe a prioridade (Quanto menor o numero, maior a prioridade): 1

Informe o nome: B
Informe o tempo do processo: 3
Informe o tempo de chegada: 1
Informe a prioridade (Quanto menor o numero, maior a prioridade): 2
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

### `SRT.java  e Prioridade.java`
Motores de escalonamento que aplicam as regras de negócio de cada algoritmo e gerenciam as transições de estado na CPU.

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
