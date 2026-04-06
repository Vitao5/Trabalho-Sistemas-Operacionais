package Processo;

import java.util.HashMap;
import java.util.Map;

public class ProcessManipulation implements DataProcessTAD {
    Map<String, DataProcess> processo = new HashMap<>();


    public boolean addNewProcess(String nome, Integer tempo){

        //verifica se ja existe um processo com aquele id para nao adicionar repetido
        if(!processo.containsKey(nome)){
            processo.put(nome,new DataProcess(nome, tempo));
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
            return getNextProcess();
        }
        return null;
    }

    public void updateProcess(String nome){
        DataProcess p = processo.get(nome);

        if(p.getTempo() > 0 && processo.containsKey(nome)){
            processo.get(nome).setTempo(p.getTempo() - 1);
        }

        if(p.getTempo() == 0){
            deleteProcessFinish(nome);
        }
    }

    public void deleteProcessFinish(String nome){
        processo.remove(nome);
    }

    public DataProcess getProcess(String nome){
        return processo.get(nome);
    }

    public boolean hasNoProcesses() {
        return processo.isEmpty(); // Aqui 'processo' é o seu Map
    }



}
