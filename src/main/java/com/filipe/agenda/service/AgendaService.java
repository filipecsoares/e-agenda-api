package com.filipe.agenda.service;

import java.util.List;

import com.filipe.agenda.model.Agenda;

public interface AgendaService {

	public Agenda create(Agenda agenda);

	public List<Agenda> getAll();

	public Agenda getByUserId(Long userId);
}
