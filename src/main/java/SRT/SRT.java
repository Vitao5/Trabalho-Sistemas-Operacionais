package SRT;

import Processo.DataProcess;
import Processo.ProcessManipulation;
import java.util.ArrayList;
import java.util.List;

public class SRT {

    public static void runProcess(ProcessManipulation pr) throws InterruptedException {
        DataProcess processMoment;
        List<String> historico = new ArrayList<>();

        System.out.println("\nINICIANDO O PROCESSO DE ESCALONAMENTO SRT\n");

        Integer tempoAtual = 0;
        while (!pr.hasNoProcesses()) {
            processMoment = pr.getNextProcess(tempoAtual);

            pr.imprimirEstadoTabela(tempoAtual, processMoment);

            if (processMoment == null) {
                historico.add("-");
                Thread.sleep(500);
                tempoAtual++;
                continue;
            }

            historico.add(processMoment.getNome());

            if (pr.callNextProcess(tempoAtual, processMoment.getNome()) == null) {
                pr.updateProcess(processMoment.getNome());

                for (DataProcess item : pr.getAllProcess().values()) {
                    if (item.getTempoChegada() <= tempoAtual) {
                        if (!item.getNome().equalsIgnoreCase(processMoment.getNome())
                                && !item.getStatus().equalsIgnoreCase("F")) {
                            item.setTempoEspera(item.getTempoEspera() + 1);
                        }
                    }
                }
                Thread.sleep(500);
            }
            tempoAtual++;
        }
        
        pr.printGantt("STATUS GERAL", historico);
        System.out.println("FIM EXECUCAO SRT\n");
    }
}
