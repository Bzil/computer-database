package com.excilys.cdb.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.util.dto.ComputerDTO;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ComputerDaoImpl.class })
public class ComputerServiceTest {

	private ComputerService service = null;

	private ComputerDao mock = null;

	@Before
	public void setUp() {
		mock = Mockito.mock(ComputerDaoImpl.class);
		Whitebox.setInternalState(ComputerServiceImpl.class, "dao", mock);
		service = ComputerServiceImpl.INSTANCE.getInstance();
	}

	@After
	public void tearDown() {
		mock = null;
	}

	@Test
	public void testFindReturnNull() {
		when(mock.find(2)).thenReturn(null);
		ComputerDTO computer = service.find(2);
		assertNull(computer);
		verify(mock, times(1)).find(eq(2));
	}

	@Test
	public void testFindReturnValue() {
		when(mock.find(1)).thenReturn(getComputer());
		ComputerDTO computer = service.find(1);
		assertEquals(ComputerDTO.toDTO(getComputer()), computer);
		verify(mock, times(1)).find(eq(1));
	}

	@Ignore
	public void testfindAll() {

	}

	@Ignore
	public void testFindAllWithParam() {

	}

	@Ignore
	public void testAdd() {

	}

	@Ignore
	public void testDelete() {

	}

	@Test
	public void testUpdateWithNullParam() {
		when(mock.update(any(Computer.class))).thenReturn(null);
		ComputerDTO computer = service.update(null);
		assertNull(computer);
		verify(mock, times(1)).find(eq(2));
	}

	@Test
	public void testUpdateReturnNull() {
		when(mock.update(any(Computer.class))).thenReturn(null);
		ComputerDTO computer = service.update(getComputer());
		assertNull(computer);
		verify(mock, times(1)).find(eq(2));
	}

	@Test
	public void testUpdate() {
		when(mock.update(any(Computer.class))).thenReturn(null);
		ComputerDTO computer = service.update(getComputer());
		assertEquals(ComputerDTO.toDTO(getComputer()), computer);
		verify(mock, times(1)).find(eq(2));

	}

	/**
	 * Gets specific computer to mock dao.
	 *
	 * @return the computer
	 */
	private static Computer getComputer() {
		return new Computer(1, "TEST", LocalDateTime.of(1991, 01, 01, 00, 00,
				00), LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1,
				"Apple Inc."));
	}
}
