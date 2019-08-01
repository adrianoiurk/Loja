package br.com.empresa.loja.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Campanha {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String nome;
	private String idTimeDoCoracao;
	private LocalDateTime dataIniVigencia;
	private LocalDateTime dataFimVigencia;
	
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
	public LocalDateTime getDataIniVigencia() {
		return dataIniVigencia;
	}
	public void setDataIniVigencia(LocalDateTime dataIniVigencia) {
		this.dataIniVigencia = dataIniVigencia;
	}
	public LocalDateTime getDataFimVigencia() {
		return dataFimVigencia;
	}
	public void setDataFimVigencia(LocalDateTime dataFimVigencia) {
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