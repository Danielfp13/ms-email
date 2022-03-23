package com.ms.email.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/envios")
	public ResponseEntity<EmailModel> enviarEmail(@RequestBody @Valid EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.enviarEmail(emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
	}

	@GetMapping("/emails")
	public ResponseEntity<Page<EmailModel>> getAllEmails(
			@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
		return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
	}

	@GetMapping("/emails/{emailId}")
	public ResponseEntity<Object> getOneEmail(@PathVariable(value = "emailId") UUID emailId) {
		Optional<EmailModel> emailModelOptional = emailService.findById(emailId);
		if (!emailModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
		}
	}
}
