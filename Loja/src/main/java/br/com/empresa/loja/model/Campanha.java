package br.com.empresa.loja.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Campanha {

	@Id
	private String id;
	private String nome;
	private String idTimeDoCoracao;
	private LocalDate dataIniVigencia;
	private LocalDate dataFimVigencia;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdTimeDoCoracao() {
		return idTimeDoCoracao;
	}
	public void setIdTimeDoCoracao(String idTimeDoCoracao) {
		this.idTimeDoCoracao = idTimeDoCoracao;
	}
	public LocalDate getDataIniVigencia() {
		return dataIniVigencia;
	}
	public void setDataIniVigencia(LocalDate dataIniVigencia) {
		this.dataIniVigencia = dataIniVigencia;
	}
	public LocalDate getDataFimVigencia() {
		return dataFimVigencia;
	}
	public void setDataFimVigencia(LocalDate dataFimVigencia) {
		this.dataFimVigencia = dataFimVigencia;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
		
	}

}
