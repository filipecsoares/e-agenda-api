package com.filipe.agenda.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.filipe.agenda.model.Agenda;
import com.filipe.agenda.model.User;
import com.filipe.agenda.repository.AgendaRepository;

@SpringBootTest
class AgendaServiceImplTest {

	@Autowired
	private AgendaServiceImpl service;

	@MockBean
	private AgendaRepository repository;

	@Test
	void shouldSaveANewAgenda() {
		LocalTime fromHour = LocalTime.now();
		LocalTime toHour = LocalTime.now();
		LocalTime serviceTime = LocalTime.of(1, 0);
		String daysOfWeek = "2,3,4,5,6";
		Agenda newAgenda = new Agenda(new User(), fromHour, toHour, serviceTime, null, null, daysOfWeek);

		when(repository.save(any(Agenda.class))).thenReturn(newAgenda);
		Agenda agendaCreated = service.create(newAgenda);
		verify(repository, times(1)).save(any(Agenda.class));

		assertNotNull(agendaCreated);
		assertEquals(newAgenda.getFromHour(), agendaCreated.getFromHour());
	}

}
