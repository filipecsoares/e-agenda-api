package com.filipe.agenda.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Agenda getByUserId(Long userId) {
		List<Agenda> agendasByUser = agendaRepository.getByUserId(userId);
		if (agendasByUser != null && !agendasByUser.isEmpty()) {
			return agendasByUser.get(0);
		}
		return null;
	}

	@Override
	public Optional<Agenda> findById(Long agendaId) {
		return agendaRepository.findById(agendaId);
	}

	@Override
	public Agenda update(Long id, Agenda agendaForm) {
		Agenda agenda = getOne(id);
		agenda.setAddress(agendaForm.getAddress());
		agenda.setDaysOfWeek(agendaForm.getDaysOfWeek());
		agenda.setFromHour(agendaForm.getFromHour());
		agenda.setLunchBreakFrom(agendaForm.getLunchBreakFrom());
		agenda.setLunchBreakTo(agendaForm.getLunchBreakTo());
		agenda.setServiceTime(agendaForm.getServiceTime());
		agenda.setName(agendaForm.getName());
		agenda.setToHour(agendaForm.getToHour());
		return agenda;
	}

	@Override
	public Agenda getOne(Long agendaId) {
		return agendaRepository.getOne(agendaId);
	}
}
