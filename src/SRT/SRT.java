package SRT;

import Processo.DataProcess;
import Processo.ProcessManipulation;

public class SRT {

    ProcessManipulation processo = new ProcessManipulation();

    public void iniciarEscalonamento() {
        int tempoTotal = 0;

        while(!processo.hasNoProcesses()) {
            DataProcess atual = processo.getNextProcess();

            if(atual != null){

                System.out.println("Aquivo atual " + atual.getNome() + "Tempo restante " + atual.getTempo());

                atual.setTempo(atual.getTempo() - 1);
                tempoTotal++;

                if(atual.getTempo() == 0){
                    System.out.println("Arquivo: " + atual.getNome() + "finalizado");
                    processo.deleteProcessFinish(atual.getNome());
                }
            }
        }

        System.out.println("Tempo total : " + tempoTotal);
    }
}
