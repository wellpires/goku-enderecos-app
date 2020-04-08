package com.goku.enderecos.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.goku.enderecos.annotation.EditarEndereco;
import com.goku.enderecos.dto.EditarEnderecoDTO;

public class EditarEnderecoValidator implements ConstraintValidator<EditarEndereco, EditarEnderecoDTO> {

	@Override
	public boolean isValid(EditarEnderecoDTO editarEnderecoDTO, ConstraintValidatorContext context) {
		return StringUtils.isNotBlank(editarEnderecoDTO.getLogradouro())
				|| Objects.nonNull(editarEnderecoDTO.getNumero())
				|| StringUtils.isNotBlank(editarEnderecoDTO.getBairro())
				|| StringUtils.isNotBlank(editarEnderecoDTO.getCidade())
				|| StringUtils.isNotBlank(editarEnderecoDTO.getEstado())
				|| StringUtils.isNotBlank(editarEnderecoDTO.getPais());
	}

}
