package com.mycompany.escalonamentotrabalho.Prioridade;

import Processo.DataProcess;
import Processo.ProcessManipulation;

public class Prioridade {

    public static void runProcess(ProcessManipulation pr) throws InterruptedException {
        DataProcess processMoment;

        System.out.println("\nINICIANDO O PROCESSO DE ESCALONAMENTO POR PRIORIDADE\n");

        Integer tempoAtual = 0;
        while (!pr.hasNoProcesses()) {
            processMoment = pr.getNextProcessPrioridade(tempoAtual);

            pr.printTimeline(tempoAtual, processMoment);

            if (processMoment == null) {
                Thread.sleep(1000);
                tempoAtual++;
                continue;
            }

            if (pr.callNextProcessPrioridade(tempoAtual, processMoment.getNome()) == null) {
                pr.updateProcess(processMoment.getNome());

                for (DataProcess item : pr.getAllProcess().values()) {
                    if (item.getTempoChegada() <= tempoAtual) {
                        if (!item.getNome().equalsIgnoreCase(processMoment.getNome())
                                && !item.getStatus().equalsIgnoreCase("F")) {
                            item.setTempoEspera(item.getTempoEspera() + 1);
                        }
                    }
                }
                Thread.sleep(1000);
            }
            tempoAtual++;
        }
        System.out.println("\nFIM EXECUÇÃO PRIORIDADE\n");
    }
}