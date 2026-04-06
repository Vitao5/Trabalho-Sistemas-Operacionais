import Processo.ProcessManipulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean sair = false;
        do{
            System.out.println("1 - Adicionar novos processos:");
            System.out.println("2 - sair");
            int menu = sc.nextInt();

            if(menu == 1){
                ProcessManipulation pr = new ProcessManipulation();

                sc.nextLine();
                System.out.println();

                System.out.println("Quantos processos voce irar adicionar ?");
                int quantidade = sc.nextInt();

                for(int i = 0; i < quantidade; i++){
                    System.out.println("Informe o nome do processo:");
                    String nome = sc.nextLine();

                    System.out.println("Informe o tempo do processo:");
                    Integer tempo = sc.nextInt();

                    pr.addNewProcess(nome, tempo);
                }
            }

            if(menu == 2){
                sair = true;
            }

            else{
                System.out.println("Numero incorreto, tente novamente!!");
                break;
            }

        }while(!sair);



        sc.close();
    }
}
