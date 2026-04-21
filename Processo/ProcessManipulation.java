package Processo;

import java.util.HashMap;
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
        Integer minTime = Integer.MAX_VALUE;
        DataProcess processNext = null;

        for (DataProcess item : processo.values()) {
            if ((item.getTempoChegada() <= tempoAtual) && (item.getTempo() < minTime)) {
                minTime = item.getTempo();
                processNext = item;
            }
        }

        if (processNext != null) {
            processo.get(processNext.getNome()).setStatus("P");
        }
        return processNext;
    }

    public DataProcess callNextProcess(Integer tempoAtual, String processoAtual) {
        for (DataProcess item : processo.values()) {
            if ((item.getTempoChegada() == tempoAtual) && (item.getTempo() < processo.get(processoAtual).getTempo())) {
                processo.get(processoAtual).setStatus("B");
                return getNextProcess(tempoAtual);
            }
        }
        return null;
    }

    public DataProcess getNextProcessPrioridade(Integer tempoAtual) {
        Integer minPrio = Integer.MAX_VALUE;
        DataProcess processNext = null;

        for (DataProcess item : processo.values()) {
            if ((item.getTempoChegada() <= tempoAtual) && (item.getPrioridade() < minPrio)) {
                minPrio = item.getPrioridade();
                processNext = item;
            }
        }

        if (processNext != null) {
            processo.get(processNext.getNome()).setStatus("P");
        }
        return processNext;
    }

    public DataProcess callNextProcessPrioridade(Integer tempoAtual, String processoAtual) {
        for (DataProcess item : processo.values()) {
            if ((item.getTempoChegada() == tempoAtual) && (item.getPrioridade() < processo.get(processoAtual).getPrioridade())) {
                processo.get(processoAtual).setStatus("B");
                return getNextProcessPrioridade(tempoAtual);
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

    public void printTimeline(Integer tempoAtual, DataProcess cpuProcess) {
        String nomeCpu = (cpuProcess != null) ? cpuProcess.getNome() : "Ocioso";
        
        System.out.printf("[ Tempo: %02d ] | CPU: [ %-15s ] | Fila: ", tempoAtual, nomeCpu);
        
        boolean hasReady = false;
        for (DataProcess p : processo.values()) {
            if (p.getTempoChegada() <= tempoAtual && p.getTempo() > 0 && (cpuProcess == null || !p.getNome().equals(cpuProcess.getNome()))) {
                System.out.printf("[ %s (Rest: %02d) ] ", p.getNome(), p.getTempo());
                hasReady = true;
            }
        }
        
        if (!hasReady) {
            System.out.print("[ Vazia ]");
        }
        System.out.println();
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