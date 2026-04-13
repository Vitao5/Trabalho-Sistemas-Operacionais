import Processo.DataProcess;
import Processo.ProcessManipulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static ProcessManipulation pr = new ProcessManipulation();

    public static void runProcess() throws InterruptedException {
        DataProcess processMoment;

        System.out.println("INICIANDO O PROCESSO DE ESCALONAMENTO\n");

        Integer tempoAtual = 0;
        while (!pr.hasNoProcesses()) {
            tempoAtual++;
            processMoment = pr.getNextProcess();
            System.out.println(
                    "\n\n==========================  TEMPO ATUAL: " + tempoAtual + "  ==========================");

            System.out.println("\n\nPROCESSO: " + processMoment.getNome() + " - TEMPO PARA CONCLUSÃO: "
                    + processMoment.getTempo());

            // se nulo é porque não tem proximo processo
            if (pr.callNextProcess(processMoment.getNome()) == null) {
                pr.updateProcess(processMoment.getNome());

                for (DataProcess item : pr.getAllProcess().values()) {
                    if (!item.getNome().equalsIgnoreCase(processMoment.getNome())
                            && !item.getStatus().equalsIgnoreCase("F")) {
                        item.setTempoEspera(item.getTempoEspera() + 1);
                    }
                }

                Thread.sleep(1000);
            }

        }

        System.out.println("FIM EXECUÇÃO\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean sair = false;
        do {
            System.out.println("\n==========================  MENU  ==========================\n\n");
            System.out.println("1 - Adicionar novos processos:");
            System.out.println("2 - Iniciar o processo de escalonamento");
            System.out.println("3 - Visualizar relatório de espera");
            System.out.println("4 - Sair");
            System.out.print("\nEscolha uma opcao:");

            int menu = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

            switch (menu) {
                case 1:

                    sc.nextLine();
                    System.out.println();

                    System.out.print("Quantos processos voce deseja adicionar ?");
                    int quantidade = Integer.parseInt(sc.nextLine());

                    for (int i = 0; i < quantidade; i++) {
                        System.out.print("\nInforme o nome do processo:");
                        String nome = sc.nextLine();

                        System.out.print("Informe o tempo do processo:");

                        Integer tempo = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                        pr.addNewProcess(nome, tempo);
                    }
                    break;
                case 2:
                    if (pr.getAllProcess().size() == 0) {
                        System.out.println("Nenhum processo adicionado. Digite 1 para adicionar processos\n");
                        continue;
                    } else {
                        try {
                            runProcess();
                        } catch (InterruptedException e) {
                            System.out.println("Ocorreu um erro");
                        }
                    }
                    break;
                case 3:

                    if (pr.getReportFinishedProcess().size() == 0) {
                        System.out.println( "\nNenhuma execução encontrada. Digite 2 para realizar o processo de escalonamento\n");
                        continue;
                    } else {
                        System.out.println(
                                "\n==========================  RELATÓRIO DE ESPERA  ==========================\n\n");
                        System.out.println("1 - Visualizar tudo");
                        System.out.println("2 - Visualizar por menor tempo");
                        System.out.println("3 - Visualizar por maior tempo");
                        System.out.print("\nEscolha uma opcao:");

                        Integer menuReport = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));
                        if (menuReport == 1) {
                            for (DataProcess item : pr.getReportFinishedProcess().values()) {
                                System.out.println("Processo: " + item.getNome().toUpperCase() + " - Esperou por " + item.getTempoEspera() + " segundos para finalizar");
                            }
                        } else if (menuReport == 2) {
                            /////////////// lógica do menor tempo
                        } else if (menuReport == 3) {
                            /////////////// lógica do maior tempo
                        } else {
                            System.out.println("Número incorreto, tente novamente");
                            continue;
                        }

                    }
                    continue;
                case 4:
                    System.out.println("Saindo do programa");
                    sair = true;
                    break;
                default:
                    System.out.println("Número incorreto, tente novamente");
                    continue;
            }

        } while (!sair);

        sc.close();
    }
}
