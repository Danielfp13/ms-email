package com.ms.email.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmailDto {

	@NotBlank(message = "Campo obrigatório")
	private String proprietarioRef;
	
	@Email
	@NotBlank(message = "Campo obrigatório")
	private String emailDe;
	
	@Email
	@NotBlank(message = "Campo obrigatório")
	private String emailPara;
	
	@NotBlank(message = "Campo obrigatório")
	private String titulo;
	
	@NotBlank(message = "Campo obrigatório")
	private String texto;

}
