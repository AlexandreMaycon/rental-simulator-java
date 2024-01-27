package modelo;

public class Apartamento extends Financiamento {
	
	private int numeroVagasGaragem;
    private int numeroAndar;
	
	 public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numeroVagasGaragem, int numeroAndar) {
	     super(valorImovel, prazoFinanciamento, taxaJurosAnual);
	     this.numeroVagasGaragem = numeroVagasGaragem;
	     this.numeroAndar = numeroAndar;
	 }

	 @Override
	 public double calcularPagamentoMensal() {
	     double taxaMensal = (this.getTaxaJurosAnual() / 12)/100;
	     int meses = this.getPrazoFinanciamento() * 12;
	     return ((getValorImovel() * taxaMensal) * (Math.pow((1 + taxaMensal) , (meses)))) /
	    		 (Math.pow((1 + taxaMensal), meses) - 1);
	 }
	 
	 @Override
	 public String getTipoFinanciamento() {
		 return "Apartamento";
	 }
	 
	 public String getInfoEspecifica() {
		 double valorVagasGaragem = (double) numeroVagasGaragem;
	     double valorAndar = (double) numeroAndar;
	     
		 return String.format("Número de vagas na garagem: %.2f \nNúmero total de andar: %.2f", valorVagasGaragem, valorAndar);
	 }
	 
	 public String toStringFile() {
		    return "Apartamento" + "," + getValorImovel() + "," + calcularTotalPagamento() + "," + getTaxaJurosAnual() + "," + getPrazoFinanciamento() + "," + numeroVagasGaragem + "," + numeroAndar;
		}
}