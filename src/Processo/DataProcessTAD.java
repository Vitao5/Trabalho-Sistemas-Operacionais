package Processo;

import java.util.Map;

public interface DataProcessTAD {
    boolean addNewProcess(String nome, Integer tempo);
    DataProcess getNextProcess();
    DataProcess callNextProcess(String nome);
    void updateProcess(String nome);
    void deleteProcessFinish(String nome);
    DataProcess getProcess(String nome);
    boolean hasNoProcesses();
    Map<String, DataProcess> getAllProcess();
}
