import Processo.ProcessManipulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProcessManipulation pr = new ProcessManipulation();

        boolean sair = false;
        do {
            System.out.println("\n==========================MENU==========================\n\n");
            System.out.println("1 - Adicionar novos processos:");
            System.out.println("2 - Iniciar o processo de escalonamento");
            System.out.println("3 - Sair");
            System.out.print("\nEscolha uma opcao:");

            int menu = sc.nextInt();

            switch (menu) {
                case 1:

                    sc.nextLine();
                    System.out.println();

                    System.out.print("Quantos processos voce deseja adicionar ?");
                    int quantidade = Integer.parseInt(sc.nextLine());

                    for (int i = 0; i < quantidade; i++) {
                        System.out.print("Informe o nome do processo:");
                        String nome = sc.nextLine();

                        System.out.print("Informe o tempo do processo:");

                        Integer tempo = Integer.parseInt(sc.nextLine().replaceAll("\\D+", ""));

                        pr.addNewProcess(nome, tempo);
                    }
                    break;
                case 2:
                    if(pr.getAllProcess().size() == 0){
                        System.out.println("Nenhum processo adicionado. Digite 1 para adicionar processos\n");
                        continue;
                    }
                    /////////////////
                    break;
                case 3:
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
