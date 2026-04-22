package com.mycompany.escalonamentotrabalho.Prioridade;

import Processo.DataProcess;
import Processo.ProcessManipulation;
import java.util.ArrayList;
import java.util.List;

public class Prioridade {

    public static void runProcess(ProcessManipulation pr) throws InterruptedException {
        DataProcess processMoment;
        List<String> historico = new ArrayList<>();

        System.out.println("\nINICIANDO O PROCESSO DE ESCALONAMENTO POR PRIORIDADE\n");

        Integer tempoAtual = 0;
        while (!pr.hasNoProcesses()) {
            processMoment = pr.getNextProcessPrioridade(tempoAtual);


            if (processMoment == null) {
                historico.add("-");
                Thread.sleep(500);
                tempoAtual++;
                continue;
            }

            historico.add(processMoment.getNome());

            if (pr.callNextProcessPrioridade(tempoAtual, processMoment.getNome()) == null) {
                pr.updateProcess(processMoment.getNome());
                
                for (DataProcess item : pr.getAllProcess().values()) {
                    if (item.getTempoChegada() <= tempoAtual) {
                        if (!item.getNome().equalsIgnoreCase(processMoment.getNome())
                                && !item.getStatus().equalsIgnoreCase("F")) {
                            
                            item.setTempoEspera(item.getTempoEspera() + 1);

                            // A cada três tempos se está ainda em fila, ganha - 1 de prioridade, ganhnado mais prioridade
                            if (item.getTempoEspera() % 3 == 0 && item.getPrioridade() > 1) {
                                item.setPrioridade(item.getPrioridade() - 1);
                            }
                        }
                    }
                }
                Thread.sleep(500);
            }
            tempoAtual++;
        }
        
        pr.printGantt("TABELAO GERAL - PRIORIDADE", historico);
        System.out.println("FIM EXECUCAO PRIORIDADE\n");
    }
}