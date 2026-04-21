package Processo;

import java.util.Map;

public interface DataProcessTAD {
    boolean addNewProcess(String nome, Integer tempo, Integer tempoChegada, Integer prioridade);
    DataProcess getNextProcess(Integer tempoAtual);
    DataProcess callNextProcess(Integer tempoAtual, String processoAtual);
    DataProcess getNextProcessPrioridade(Integer tempoAtual);
    DataProcess callNextProcessPrioridade(Integer tempoAtual, String processoAtual);
    void updateProcess(String nome);
    void deleteProcessFinish(String nome);
    DataProcess getProcess(String nome);
    boolean hasNoProcesses();
    Map<String, DataProcess> getAllProcess();
    Map<String, DataProcess> getReportFinishedProcess();
    void printTimeline(Integer tempoAtual, DataProcess cpuProcess);
}