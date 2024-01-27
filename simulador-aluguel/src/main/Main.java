package main;

import java.util.ArrayList;
import java.util.List;
import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;
import modelo.Financiamento;
import util.InterfaceUsuario;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        List<Financiamento> financiamentos = new ArrayList<>();

        System.out.println("-> BEM VINDO AO SISTEMA DE FINANCIAMENTO <- \n");

        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazoFinanciamento = interfaceUsuario.pedirPrazoFinanciamento();
        double taxaJurosAnual = interfaceUsuario.pedirTaxaJuros();

        // Escolha do tipo de financiamento
        String tipoFinanciamento = interfaceUsuario.lerTipoFinanciamento();

        // Adicionando financimento escolhido
	        switch (tipoFinanciamento) {
	            case "1":
	            	double tamanhoAreaConstruidaCasa = interfaceUsuario.pedirTamanhoAreaConstruida();
	                double tamanhoTerrenoCasa = interfaceUsuario.pedirTamanhoTerreno();
	                financiamentos.add(new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, tamanhoAreaConstruidaCasa, tamanhoTerrenoCasa));
	                break;
	            case "2":
	            	int numeroVagasGaragemApartamento = interfaceUsuario.pedirNumeroVagasGaragem();
	                int numeroAndarApartamento = interfaceUsuario.pedirNumeroAndar();
	                financiamentos.add(new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, numeroVagasGaragemApartamento, numeroAndarApartamento));
	                break;
	            case "3":
	            	String tipoZonaTerreno = interfaceUsuario.pedirTipoZona();
	                financiamentos.add(new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZonaTerreno));
	                break;
	            default:
	                System.out.println("Tipo de financiamento não reconhecido. Encerrando o programa.");
	                return;
	        }

        financiamentos.add(new Casa(200000, 20, 8.0, 150, 300));
        financiamentos.add(new Casa(250000, 25, 7.5, 180, 350));

        financiamentos.add(new Apartamento(300000, 15, 6.5, 2, 10));
        financiamentos.add(new Apartamento(350000, 18, 7.0, 3, 15));

        financiamentos.add(new Terreno(150000, 10, 5.0, "residencial"));
        
        salvarEmArquivoTexto("financiamentos.txt", financiamentos);

        salvarEmArquivoSerializado("financiamentos.dat", financiamentos);

        lerEExibirArquivoTexto("financiamentos.txt");

        lerEExibirArquivoSerializado("financiamentos.dat");
        
        double totalValorImoveis = 0.0;
        double totalPagamentos = 0.0;

        for (Financiamento financiamento : financiamentos) {
            totalValorImoveis += financiamento.getValorImovel();
            totalPagamentos += financiamento.calcularTotalPagamento();
        }

        System.out.printf("Total dos valores dos imóveis: R$ %.2f\n", totalValorImoveis);
        System.out.printf("Total dos pagamentos: R$ %.2f\n", totalPagamentos);
    }
    
    

    private static void salvarEmArquivoTexto(String nomeArquivo, List<Financiamento> financiamentos) {
        try {
            FileWriter writer = new FileWriter(nomeArquivo);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Financiamento financiamento : financiamentos) {
                bufferedWriter.write(financiamento.toStringFile());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Dados salvos em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados no arquivo de texto.");
            e.printStackTrace();
        }
    }

    private static void salvarEmArquivoSerializado(String nomeArquivo, List<Financiamento> financiamentos) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            outputStream.writeObject(financiamentos);
            outputStream.close();
            System.out.println("Dados salvos em " + nomeArquivo);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados em arquivo de dados serializados.");
            e.printStackTrace();
        }
    }

    private static void lerEExibirArquivoTexto(String nomeArquivo) {
        try {
            FileReader reader = new FileReader(nomeArquivo);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            System.out.println("\n-> DADOS DOS FINANCIAMENTOS LIDOS DO ARQUIVO DE TEXTO <-\n");
            while ((line = bufferedReader.readLine()) != null) {
                String[] dados = line.split(",");
                exibirDadosFormatados(dados);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler dados do arquivo de texto.");
            e.printStackTrace();
        }
    }

    private static void lerEExibirArquivoSerializado(String nomeArquivo) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
            List<Financiamento> financiamentosLidos = (List<Financiamento>) inputStream.readObject();
            inputStream.close();

            DecimalFormat df = new DecimalFormat("#.00");

            System.out.println("\n-> DADOS DOS FINANCIAMENTOS LIDOS DO ARQUIVO DE DADOS SERIALIZADOS <-\n");
            for (Financiamento financiamento : financiamentosLidos) {
                System.out.println(financiamento.getTipoFinanciamento());
                System.out.println("Valor do imóvel: R$ " + df.format(financiamento.getValorImovel()));
                System.out.println("Valor do financiamento: R$ " + df.format(financiamento.calcularTotalPagamento()));
                System.out.println("Taxa de juros: " + df.format(financiamento.getTaxaJurosAnual()) + "%");
                System.out.println("Prazo de financiamento: " + financiamento.getPrazoFinanciamento() + " anos");
                System.out.println(financiamento.getInfoEspecifica());
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler dados do arquivo de dados serializados.");
            e.printStackTrace();
        }
    }

    private static void exibirDadosFormatados(String[] dados) {
        System.out.printf(dados[0] + ":\n");
        System.out.printf("Valor do imóvel: R$ %.2f\n", Double.parseDouble(dados[1]));
        System.out.printf("Valor do financiamento: R$ %.2f\n", Double.parseDouble(dados[2]));
        System.out.printf("Taxa de juros: %.2f%%\n", Double.parseDouble(dados[3]));
        System.out.printf("Prazo de financiamento: %d anos\n", Integer.parseInt(dados[4]));

        if (dados[0].equals("Terreno")) {
            System.out.println("Tipo de Zona: " + dados[5]);
        }

        if (dados[0].equals("Casa")) {
            System.out.println("Área Construída: " + Double.parseDouble(dados[5]));
            System.out.println("Tamanho do Terreno: " + Double.parseDouble(dados[6]));
        }

        if (dados[0].equals("Apartamento")) {
            System.out.println("Número de vagas na garagem: " + Integer.parseInt(dados[5]));
            System.out.println("Número total de andar: " + Integer.parseInt(dados[6]));
        }

        System.out.println();
    }

}
