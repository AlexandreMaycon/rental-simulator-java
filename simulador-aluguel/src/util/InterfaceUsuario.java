package util;

import java.util.Scanner;
import java.util.InputMismatchException;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        scanner = new Scanner(System.in);
    }

    public double pedirValorImovel() {
        double valorImovel = 0;
        do {
            try {
                System.out.print("Digite o valor do imóvel: ");
                valorImovel = scanner.nextDouble();
                if (valorImovel <= 0) {
                    System.out.println("O valor do imóvel deve ser positivo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("O valor informado não é válido");
                scanner.next();
            }
        } while (valorImovel <= 0);
        return valorImovel;
    }

    public int pedirPrazoFinanciamento() {
        int prazoFinanciamento = 0;
        do {
            try {
                System.out.print("Digite o prazo do financiamento em anos: ");
                prazoFinanciamento = scanner.nextInt();
                if (prazoFinanciamento <= 0) {
                    System.out.println("O prazo do financiamento deve ser positivo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("O valor informado não é válido");
                scanner.next();
            }
        } while (prazoFinanciamento <= 0);
        return prazoFinanciamento;
    }

    public double pedirTaxaJuros() {
        double taxaJurosAnual = 0;
        do {
            try {
                System.out.print("Digite a taxa de juros anual (em porcentagem): ");
                taxaJurosAnual = scanner.nextDouble();
                if (taxaJurosAnual <= 0) {
                    System.out.println("A taxa de juros deve ser positiva. Tente novamente.");
                }

                if (taxaJurosAnual >= 1000) {
                    System.out.println("A taxa de juros deve ser menor que 1000%. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("O valor informado não é válido");
                scanner.next();
            }
        } while (taxaJurosAnual <= 0 || taxaJurosAnual >= 1000);
        return taxaJurosAnual;
    }
    
    public String lerTipoFinanciamento() {
        String escolha = "";
        do {
            try {
                System.out.println("Escolha o tipo de financiamento:");
                System.out.println("1 - Casa");
                System.out.println("2 - Apartamento");
                System.out.println("3 - Terreno");
                System.out.print("Opção: ");
                escolha = scanner.next();
                if (!escolha.matches("[1-3]")) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next();
                escolha = "";
            }
        } while (!escolha.matches("[1-3]"));
        return escolha;
    }
    
    public double pedirTamanhoAreaConstruida() {
        double tamanhoAreaConstruida = 0;
        do {
            try {
                System.out.print("Digite o tamanho da área construída: ");
                tamanhoAreaConstruida = scanner.nextDouble();
                if (tamanhoAreaConstruida <= 0) {
                    System.out.println("O tamanho da área construída deve ser positivo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor informado não é válido");
                scanner.next();
            }
        } while (tamanhoAreaConstruida <= 0);
        return tamanhoAreaConstruida;
    }

    public double pedirTamanhoTerreno() {
        double tamanhoTerreno = 0;
        do {
            try {
                System.out.print("Digite o tamanho do terreno: ");
                tamanhoTerreno = scanner.nextDouble();
                if (tamanhoTerreno <= 0) {
                    System.out.println("O tamanho do terreno deve ser positivo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor informado não é válido");
                scanner.next();
            }
        } while (tamanhoTerreno <= 0);
        return tamanhoTerreno;
    }

    public int pedirNumeroVagasGaragem() {
        int numeroVagasGaragem = 0;
        do {
            try {
                System.out.print("Digite o número de vagas de garagem: ");
                numeroVagasGaragem = scanner.nextInt();
                if (numeroVagasGaragem < 0) {
                    System.out.println("O número de vagas de garagem deve ser não negativo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor informado não é válido");
                scanner.next();
            }
        } while (numeroVagasGaragem < 0);
        return numeroVagasGaragem;
    }

    public int pedirNumeroAndar() {
        int numeroAndar = 0;
        do {
            try {
                System.out.print("Digite o número do andar: ");
                numeroAndar = scanner.nextInt();
                if (numeroAndar < 0) {
                    System.out.println("O número do andar deve ser não negativo. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor informado não é válido");
                scanner.next();
            }
        } while (numeroAndar < 0);
        return numeroAndar;
    }

    public String pedirTipoZona() {
        String tipoZona = "";
        do {
            try {
                System.out.print("Digite o tipo de zona (residencial ou comercial): ");
                tipoZona = scanner.next().toLowerCase();
                if (!tipoZona.equals("residencial") && !tipoZona.equals("comercial")) {
                    System.out.println("Tipo de zona inválido. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Valor informado não é válido");
                scanner.next();
            }
        } while (!tipoZona.equals("residencial") && !tipoZona.equals("comercial"));
        return tipoZona;
    }
}
