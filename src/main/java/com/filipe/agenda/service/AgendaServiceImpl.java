package com.filipe.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filipe.agenda.model.Agenda;
import com.filipe.agenda.repository.AgendaRepository;

@Service
public class AgendaServiceImpl implements AgendaService {

	@Autowired
	private AgendaRepository agendaRepository;

	@Override
	public Agenda create(Agenda agenda) {
		return agendaRepository.save(agenda);
	}

	@Override
	public List<Agenda> getAll() {
		return agendaRepository.findAll();
	}
}
