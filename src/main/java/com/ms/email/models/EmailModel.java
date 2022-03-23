package com.ms.email.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ms.email.enums.StatusEmail;

import lombok.Data;

@Entity
@Data
@Table(name = "TB_EMAIL")
public class EmailModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long emailId;
	private String proprietarioRef;
	private String emailDe;
	private String emailPara;
	private String titulo;
	@Column(columnDefinition = "text")
	private String texto;
	private LocalDateTime dataEnvio;
	private StatusEmail statusEmail;
}