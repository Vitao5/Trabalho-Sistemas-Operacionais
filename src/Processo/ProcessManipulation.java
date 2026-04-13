package Processo;

import java.util.HashMap;
import java.util.Map;

public class ProcessManipulation implements DataProcessTAD {
    Map<String, DataProcess> processo = new HashMap<>();
    Map<String, DataProcess> reportFinishedProcess = new HashMap<>();


    public boolean addNewProcess(String nome, Integer tempo){

        //verifica se ja existe um processo com aquele id para nao adicionar repetido
        if(!processo.containsKey(nome)){
            processo.put(nome,new DataProcess(nome, tempo, "P", 0));
            return true;
        }else{
            return false;
        }
    }

    public DataProcess getNextProcess(){
        Integer minTime = Integer.MAX_VALUE;
        DataProcess processNext = null;

        for (DataProcess item : processo.values()){
            if(item.getTempo() < minTime){
                minTime = item.getTempo();
                processNext = item;
            }
        }

        return processNext;
    }

    public DataProcess callNextProcess(String nome){
        if(getNextProcess().getTempo() < processo.get(nome).getTempo()){
            //STATUS P PARA PRONTO
            processo.get(nome).setStatus("P");
            return getNextProcess();
        }
        return null;
    }

    public void updateProcess(String nome){
        DataProcess p = processo.get(nome);

        if(p.getTempo() > 0 && processo.containsKey(nome)){
            if(processo.get(nome).getTempo() == 1){
                reportFinishedProcess.put(nome, processo.get(nome)); 
            }

            processo.get(nome).setTempo(p.getTempo() - 1);

            ///STATUS E PARA EXECUÇÃO
            if(!processo.get(nome).getStatus().equalsIgnoreCase("P")){
                processo.get(nome).setStatus("E");
            }

        }

        if(p.getTempo() == 0){
            ///STATUS F PARA FINALIZADO
            processo.get(nome).setStatus("F");

            deleteProcessFinish(nome);
        }
    }

    public Map<String, DataProcess> getReportFinishedProcess() {
        return reportFinishedProcess;
    }

    public void deleteProcessFinish(String nome){
        processo.remove(nome);
    }

    public DataProcess getProcess(String nome){
        return processo.get(nome);
    }
    public Map<String, DataProcess> getAllProcess(){
        return processo;
    }

    public boolean hasNoProcesses() {
        return processo.isEmpty();
    }



}
