package Processo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessManipulation implements DataProcessTAD {
    Map<String, DataProcess> processo = new HashMap<>();
    Map<String, DataProcess> reportFinishedProcess = new HashMap<>();

    public boolean addNewProcess(String nome, Integer tempo, Integer tempoChegada, Integer prioridade) {
        if (!processo.containsKey(nome)) {
            processo.put(nome, new DataProcess(nome, tempo, "P", 0, tempoChegada, prioridade));
            return true;
        } else {
            return false;
        }
    }

    public DataProcess getNextProcess(Integer tempoAtual) {
        DataProcess processNext = null;

        for (DataProcess item : processo.values()) {
            if (item.getTempoChegada() <= tempoAtual && item.getTempo() > 0) {
                if (processNext == null) {
                    processNext = item;
                } else if (item.getTempo() < processNext.getTempo()) {
                    processNext = item;
                } else if (item.getTempo().equals(processNext.getTempo())) {
                    if (item.getNome().compareToIgnoreCase(processNext.getNome()) < 0) {
                        processNext = item;
                    }
                }
            }
        }

        if (processNext != null) {
            processo.get(processNext.getNome()).setStatus("P");
        }
        return processNext;
    }

    public DataProcess callNextProcess(Integer tempoAtual, String processoAtual) {
        DataProcess atual = processo.get(processoAtual);
        if (atual == null) return null;

        for (DataProcess item : processo.values()) {
            if (item.getTempoChegada() <= tempoAtual && item.getTempo() > 0) {
                if (item.getTempo() < atual.getTempo()) {
                    atual.setStatus("B");
                    return getNextProcess(tempoAtual);
                } else if (item.getTempo().equals(atual.getTempo())) {
                    if (item.getNome().compareToIgnoreCase(atual.getNome()) < 0) {
                        atual.setStatus("B");
                        return getNextProcess(tempoAtual);
                    }
                }
            }
        }
        return null;
    }

    ///2 Algoritmo - onde verifica a maior prioridade, e ordem alfabetica
    public DataProcess getNextProcessPrioridade(Integer tempoAtual) {
        DataProcess processNext = null;

        for (DataProcess item : processo.values()) {
            if (item.getTempoChegada() <= tempoAtual && item.getTempo() > 0) {
                if (processNext == null) {
                    processNext = item;
                } else if (item.getPrioridade() < processNext.getPrioridade()) {
                    processNext = item;
                } else if (item.getPrioridade().equals(processNext.getPrioridade())) {
                    if (item.getNome().compareToIgnoreCase(processNext.getNome()) < 0) {
                        processNext = item;
                    }
                }
            }
        }

        if (processNext != null) {
            processo.get(processNext.getNome()).setStatus("P");
        }
        return processNext;
    }

    //Comparador, e o que troca os processos
    public DataProcess callNextProcessPrioridade(Integer tempoAtual, String processoAtual) {
        DataProcess atual = processo.get(processoAtual);
        if (atual == null) return null;

        for (DataProcess item : processo.values()) {
            if (item.getTempoChegada() <= tempoAtual && item.getTempo() > 0) {
                if (item.getPrioridade() < atual.getPrioridade()) {
                    atual.setStatus("B");
                    return getNextProcessPrioridade(tempoAtual);
                } else if (item.getPrioridade().equals(atual.getPrioridade())) {
                    if (item.getNome().compareToIgnoreCase(atual.getNome()) < 0) {
                        atual.setStatus("B");
                        return getNextProcessPrioridade(tempoAtual);
                    }
                }
            }
        }
        return null;
    }

    public void updateProcess(String nome) {
        DataProcess p = processo.get(nome);

        if (p.getTempo() > 0 && processo.containsKey(nome)) {
            if (processo.get(nome).getTempo() == 1) {
                reportFinishedProcess.put(nome, processo.get(nome));
            }

            processo.get(nome).setTempo(p.getTempo() - 1);
            processo.get(nome).setStatus("E");
        }

        if (p.getTempo() == 0) {
            deleteProcessFinish(nome);
        }
    }

    public void imprimirEstadoTabela(Integer tempoAtual, DataProcess cpuProcess) {
        System.out.println("-------------------------------------------------");
        System.out.println("[ TEMPO: " + tempoAtual + " ]");
        System.out.printf("%-10s | %-14s | %-12s\n", "Processo", "Tempo Chegou", "Tempo Proc.");
        
        List<DataProcess> todos = new ArrayList<>();
        todos.addAll(processo.values());
        todos.addAll(reportFinishedProcess.values());
        
        todos.sort(Comparator.comparingInt(DataProcess::getTempoChegada));

        for (DataProcess p : todos) {
            if (p.getTempoChegada() <= tempoAtual) {
                System.out.printf("%-10s | %-14d | %-12d\n", p.getNome(), p.getTempoChegada(), p.getTempo());
            }
        }
        System.out.println("\n-> CPU: " + (cpuProcess != null ? cpuProcess.getNome() : "Ocioso"));
        System.out.println("-------------------------------------------------\n");
    }

    public void printGantt(String titulo, List<String> historico) {
        System.out.println("\n" + titulo);
        
        int[] larguras = new int[historico.size()];
        for (int i = 0; i < historico.size(); i++) {
            int tamNumero = String.valueOf(i).length();
            String nomeProc = historico.get(i) != null ? historico.get(i) : "-";
            larguras[i] = Math.max(tamNumero, nomeProc.length());
        }
        
        System.out.print("|");
        for (int i = 0; i < historico.size(); i++) {
            System.out.printf(" %" + larguras[i] + "d |", i);
        }
        System.out.println();
        
        System.out.print("|");
        for (int i = 0; i < historico.size(); i++) {
            String nomeProc = historico.get(i) != null ? historico.get(i) : "-";
            System.out.printf(" %" + larguras[i] + "s |", nomeProc);
        }
        System.out.println("\n");
    }

    public Map<String, DataProcess> getReportFinishedProcess() {
        return reportFinishedProcess;
    }

    public void deleteProcessFinish(String nome) {
        processo.get(nome).setStatus("F");
        processo.remove(nome);
    }

    public DataProcess getProcess(String nome) {
        return processo.get(nome);
    }

    public Map<String, DataProcess> getAllProcess() {
        return processo;
    }

    public boolean hasNoProcesses() {
        return processo.isEmpty();
    }
}