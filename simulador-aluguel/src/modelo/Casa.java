package modelo;

public class Casa extends Financiamento {
	
	private double areaConstruida;
    private double tamanhoTerreno;
	
	 public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
	     super(valorImovel, prazoFinanciamento, taxaJurosAnual);
	     this.areaConstruida = areaConstruida;
	     this.tamanhoTerreno = tamanhoTerreno;
	 }
	 
	 @Override
	 public double calcularPagamentoMensal() {
	     double taxaMensal = getTaxaJurosAnual() / 12;
	     int meses = getPrazoFinanciamento() * 12;
	     double pagamentoMensal = (getValorImovel() * (1 + taxaMensal) / meses);

	     return pagamentoMensal + 80;
	 }
	 
	 @Override
	 public String getTipoFinanciamento() {
		 return "Casa";
	 }
	 
	 public String getInfoEspecifica() {
		 return String.format("Área Construída: %.2f \nTamanho do Terreno: %.2f", areaConstruida, tamanhoTerreno);
	 }
	 
	 public String toStringFile() {
		    return "Casa" + "," + getValorImovel() + "," + calcularTotalPagamento() + "," + getTaxaJurosAnual() + "," + getPrazoFinanciamento() + "," + areaConstruida + "," + tamanhoTerreno;
		}
}