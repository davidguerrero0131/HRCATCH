package com.HUSRTbdBiomedica.app.entity;

public class ObjetoCompuesto <T, Y> {
	
	private T objetoA;
	private Y objetoB;
	
	public ObjetoCompuesto(T objetoA, Y objetoB) {
		super();
		this.objetoA = objetoA;
		this.objetoB = objetoB;
	}

	public T getObjetoA() {
		return objetoA;
	}

	public void setObjetoA(T objetoA) {
		this.objetoA = objetoA;
	}

	public Y getObjetoB() {
		return objetoB;
	}

	public void setObjetoB(Y objetoB) {
		this.objetoB = objetoB;
	}
	
}
