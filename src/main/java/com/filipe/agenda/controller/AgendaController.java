package com.filipe.agenda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filipe.agenda.model.Agenda;
import com.filipe.agenda.service.AgendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/agendas")
@Api(value = "Agenda API")
public class AgendaController {

	@Autowired
	private AgendaService agendaService;

	@ApiOperation(value = "Cadastrar um novo usuário")
	@PostMapping
	public ResponseEntity<Agenda> save(@RequestBody @Valid Agenda agenda) {
		Agenda createdAgenda = agendaService.create(agenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenda);
	}
}
