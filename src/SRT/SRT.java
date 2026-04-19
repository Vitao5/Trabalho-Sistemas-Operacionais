package SRT;

import Processo.DataProcess;
import Processo.ProcessManipulation;

public class SRT {

    static ProcessManipulation pr = new ProcessManipulation();


    public static void runProcess() throws InterruptedException {
        DataProcess processMoment;

        System.out.println("INICIANDO O PROCESSO DE ESCALONAMENTO SRT\n");

        Integer tempoAtual = 0;
        while (!pr.hasNoProcesses()) {
            tempoAtual++;
            processMoment = pr.getNextProcess(tempoAtual);
            System.out.println(
                    "\n\n==========================  TEMPO ATUAL: " + tempoAtual + "  ==========================");

            if (processMoment != null) {
                System.out.println("\n\nPROCESSO: " + processMoment.getNome() + " - TEMPO PARA CONCLUSÃO: "
                        + processMoment.getTempo());
            } else {
                System.out.println("\n\nnenhum processo em execução");
                Thread.sleep(1000);
                continue;
            }

            // se nulo é porque não tem proximo processo
            if (pr.callNextProcess(tempoAtual, processMoment.getNome()) == null) {
                pr.updateProcess(processMoment.getNome());

                for (DataProcess item : pr.getAllProcess().values()) {

                    if (item.getTempoChegada() < tempoAtual || item.getTempoChegada() == tempoAtual) {
                        if (!item.getNome().equalsIgnoreCase(processMoment.getNome())
                                && !item.getStatus().equalsIgnoreCase("F")) {
                            item.setTempoEspera(item.getTempoEspera() + 1);
                        }

                    }
                }
                Thread.sleep(1000);
            }
        }
        System.out.println("FIM EXECUÇÃO\n");
    }
}
