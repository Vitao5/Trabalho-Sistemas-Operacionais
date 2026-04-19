package Processo;

import java.util.Map;

public interface DataProcessTAD {
    boolean addNewProcess(String nome, Integer tempo, Integer tempoChegada);
    DataProcess getNextProcess(Integer tempoAtual);
    DataProcess callNextProcess(Integer tempoAtual, String processoAtual);
    void updateProcess(String nome);
    void deleteProcessFinish(String nome);
    DataProcess getProcess(String nome);
    boolean hasNoProcesses();
    Map<String, DataProcess> getAllProcess();
}
