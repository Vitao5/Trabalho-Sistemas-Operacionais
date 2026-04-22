
package com.mycompany.escalonamentotrabalho;
import Processo.DataProcess;
import Processo.ProcessManipulation;
import SRT.SRT;
import com.mycompany.escalonamentotrabalho.Prioridade.Prioridade;
import java.util.Scanner;

public class EscalonamentoTrabalho {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProcessManipulation pr = new ProcessManipulation();

        boolean sair = false;
        do {
            System.out.println("\n==========================  MENU  ==========================\n");
            System.out.println("1 - Adicionar processos");
            System.out.println("2 - Iniciar escalonamento SRT");
            System.out.println("3 - Iniciar escalonamento Prioridade");
            System.out.println("4 - Sair");
            System.out.print("\nEscolha uma opcao: ");

            if (!sc.hasNextInt()) {
                System.out.println("Valor invalido. Digite apenas nUmeros");
                sc.nextLine();
                continue;
            }
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    System.out.print("\nQuantos processos deseja adicionar? ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Valor invalido. Digite apenas numeros");
                        sc.nextLine();
                        continue;
                    }
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    try {
                        for (int i = 0; i < quantidade; i++) {
                            System.out.print("\nInforme o nome: ");
                            String nome = sc.nextLine();

                            System.out.print("Informe o tempo do processo: ");
                            Integer tempo = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                            System.out.print("Informe o tempo de chegada: ");
                            Integer tempoChegada = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                            System.out.print("Informe a prioridade (Quanto menor o numero, maior a prioridade): ");
                            Integer prioridade = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                            pr.addNewProcess(nome, tempo, tempoChegada, prioridade);
                        }
                        break;
                    }catch (IllegalArgumentException e) {
                        System.out.println();
                        System.out.println("Valor invalido. Digite apenas numeros");
                        System.out.println();
                    }
                case 2:
                    if (pr.getAllProcess().size() == 0) {
                        System.out.println("\nNenhum processo adicionado.\n");
                    } else {
                        try {
                            SRT.runProcess(pr);
                        } catch (InterruptedException e) {
                            System.out.println("Ocorreu um erro");
                        }
                    }
                    break;
                case 3:
                    if (pr.getAllProcess().size() == 0) {
                        System.out.println("\nNenhum processo adicionado.\n");
                    } else {
                        try {
                            Prioridade.runProcess(pr);
                        } catch (InterruptedException e) {
                            System.out.println("Ocorreu um erro");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Saindo do programa");
                    sair = true;
                    break;
                default:
                    System.out.println("Numero incorreto. tente novamente");
                    break;
            }

        } while (!sair);

        sc.close();
    }
}
