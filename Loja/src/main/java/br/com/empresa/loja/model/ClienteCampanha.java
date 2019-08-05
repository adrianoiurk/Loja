package br.com.empresa.loja.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class ClienteCampanha {
	
	@Id 
	private String id;
	private String idCliente;
	private String idCampanha;
	
	public ClienteCampanha() {

	}	

	public ClienteCampanha(String idCliente, String idCampanha) {
		super();
		this.idCliente = idCliente;
		this.idCampanha = idCampanha;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdCampanha() {
		return idCampanha;
	}
	public void setIdCampanha(String idCampanha) {
		this.idCampanha = idCampanha;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCampanha == null) ? 0 : idCampanha.hashCode());
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		ClienteCampanha other = (ClienteCampanha) obj;
		if (idCampanha == null) {
			if (other.idCampanha != null)
				return false;
		} else if (!idCampanha.equals(other.idCampanha))
			return false;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}




}
