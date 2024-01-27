package modelo;

public class Terreno extends Financiamento {
	
	private String tipoZona;
	
	 public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
	     super(valorImovel, prazoFinanciamento, taxaJurosAnual);
	     this.tipoZona = tipoZona;
	 }

	 @Override
	 public double calcularPagamentoMensal() {
		 double taxaMensal = getTaxaJurosAnual() / 12;
	     int meses = getPrazoFinanciamento() * 12;

	     return (getValorImovel() * (1 + taxaMensal) / meses) * 1.02;
	 }
	 
	 @Override
	 public String getTipoFinanciamento() {
		 return "Terreno";
	 }
	 
	 public String getInfoEspecifica() {
		 return String.format("Tipo de Zona: %s", tipoZona);
	 }
	 
	 public String toStringFile() {
		    return "Terreno" + "," + getValorImovel() + "," + calcularTotalPagamento() + "," + getTaxaJurosAnual() + "," + getPrazoFinanciamento() + "," + tipoZona;
		}
}