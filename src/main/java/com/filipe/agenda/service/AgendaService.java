package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;

import com.filipe.agenda.model.Agenda;

public interface AgendaService {

	public Agenda create(Agenda agenda);

	public List<Agenda> getAll();

	public Agenda getByUserId(Long userId);

	public Optional<Agenda> findById(Long agendaId);

	public Agenda update(Long id, Agenda agenda);

	public Agenda getOne(Long agendaId);
}
