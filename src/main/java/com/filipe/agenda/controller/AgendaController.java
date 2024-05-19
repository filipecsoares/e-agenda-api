package com.filipe.agenda.controller;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@ApiOperation(value = "Cadastrar uma nova agenda")
	@PostMapping
	public ResponseEntity<Agenda> save(@RequestBody @Valid Agenda agenda) {
		Agenda createdAgenda = agendaService.create(agenda);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAgenda);
	}

	@ApiOperation(value = "Exibe a lista de todas as agendas")
	@GetMapping
	public ResponseEntity<List<Agenda>> getAll() {
		List<Agenda> agendas = agendaService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(agendas);
	}

	@ApiOperation(value = "Exibe a agenda por usuário")
	@GetMapping("/user/{userId}")
	public ResponseEntity<Agenda> getByUserId(@PathVariable Long userId) {
		Agenda agenda = agendaService.getByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(agenda);
	}

	@ApiOperation(value = "Consulta agenda por ID")
	@GetMapping("/{agendaId}")
	public ResponseEntity<Agenda> getById(@PathVariable Long agendaId) {
		Optional<Agenda> optionalAgenda = agendaService.findById(agendaId);
        return optionalAgenda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

	@ApiOperation(value = "Atualizar uma agenda por Id")
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Agenda> update(@PathVariable Long id, @RequestBody @Valid Agenda agendaForm) {
		Optional<Agenda> optional = agendaService.findById(id);
		if (optional.isPresent()) {
			Agenda agenda = agendaService.update(id, agendaForm);
			return ResponseEntity.ok(agenda);
		}
		return ResponseEntity.notFound().build();
	}
}
