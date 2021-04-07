package com.filipe.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filipe.agenda.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

	public List<Agenda> getByUserId(Long userId);
}
