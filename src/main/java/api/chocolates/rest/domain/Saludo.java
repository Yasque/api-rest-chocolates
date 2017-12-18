package api.chocolates.rest.domain;

public class Saludo {

	private long incrementador;

	private String descripcion;

	public long getIncrementador() {
		return incrementador;
	}

	public void setIncrementador(long incrementador) {
		this.incrementador = incrementador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Saludo(long incrementado, String formato) {
		this.incrementador = incrementado;
		this.descripcion = formato;
	}

}
