package com.goku.enderecos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;

public class EnderecoCEPDetalheResponse {

	@JsonProperty("endereco-detalhe")
	private EnderecoCEPDetalheDTO enderecoCEPDetalheDTO;

	public EnderecoCEPDetalheResponse(EnderecoCEPDetalheDTO enderecoCEPDetalheDTO) {
		this.enderecoCEPDetalheDTO = enderecoCEPDetalheDTO;
	}

	public EnderecoCEPDetalheDTO getEnderecoCEPDetalheDTO() {
		return enderecoCEPDetalheDTO;
	}

	public void setEnderecoCEPDetalheDTO(EnderecoCEPDetalheDTO enderecoCEPDetalheDTO) {
		this.enderecoCEPDetalheDTO = enderecoCEPDetalheDTO;
	}

}
