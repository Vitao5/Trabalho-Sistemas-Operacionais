import Processo.DataProcess;
import Processo.ProcessManipulation;
import java.util.Scanner;

public class Main {
    static ProcessManipulation pr = new ProcessManipulation();

    public static void runProcess() throws InterruptedException {
        DataProcess processMoment;

        System.out.println("INICIANDO O PROCESSO DE ESCALONAMENTO SRT\n");

        Integer tempoAtual = 1;
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

                    // if (!item.getNome().equalsIgnoreCase(processMoment.getNome()) &&
                    // !item.getStatus().equalsIgnoreCase("F")) {
                    // item.setTempoEspera(item.getTempoEspera() + 1);
                    // }
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

            if (!sc.hasNextInt()) {
                System.out.println("Valor inválido. Digite apenas números");
                sc.nextLine();
                continue;
            }
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    System.out.println();

                    System.out.print("Quantos processos deseja adicionar ?");
                    if (!sc.hasNextInt()) {
                        System.out.println("Valor inválido. Digite apenas números");
                        sc.nextLine();
                        continue;
                    }
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < quantidade; i++) {
                        System.out.print("\nInforme o nome:");
                        String nome = sc.nextLine();

                        System.out.print("Informe o tempo do processo:");
                        Integer tempo = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                        System.out.print("Informe o tempo de chegada:");
                        Integer tempoChegada = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                        pr.addNewProcess(nome, tempo, tempoChegada);
                    }
                    break;
                case 2:
                    if (pr.getAllProcess().size() == 0) {
                        System.out.println("\nNenhum processo adicionado. Digite 1 para adicionar processos\n");
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
                        System.out.println(
                                "\nNenhuma execução encontrada. Digite 2 para realizar o processo de escalonamento\n");
                        continue;
                    } else {
                        System.out.println(
                                "\n==========================  RELATÓRIO DE ESPERA  ==========================\n\n");

                        for (DataProcess item : pr.getReportFinishedProcess().values()) {
                            System.out.println("Processo: " + item.getNome().toUpperCase() + " - Esperou por "
                                    + item.getTempoEspera() + " segundos para finalizar");
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
