package com.excilys.cdb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
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
	
//	
//	private ComputerService service = null;
//
//	private ComputerDao mock = null;
//
//	@Before
//	public void setUp() {
//		mock = Mockito.mock(ComputerDaoImpl.class);
//		Whitebox.setInternalState(ComputerServiceImpl.class, "dao", mock);
//		service = ComputerServiceImpl.INSTANCE.getInstance();
//	}
//
//	@After
//	public void tearDown() {
//		mock = null;
//	}
//
//	@Test
//	public void testFindReturnNull() {
//		when(mock.find(2)).thenReturn(null);
//		ComputerDTO computer = service.find(2);
//		assertNull(computer);
//		verify(mock, times(1)).find(eq(2));
//	}
//
//	@Test
//	public void testFindReturnValue() {
//		when(mock.find(1)).thenReturn(getComputer());
//		ComputerDTO computer = service.find(1);
//		assertEquals(getComputerDTO(), computer);
//		verify(mock, times(1)).find(eq(1));
//	}
//
//	@Test
//	public void testUpdateWithNullParam() {
//		when(mock.update(any(Computer.class))).thenReturn(null);
//		ComputerDTO computer = service.update(null);
//		assertNull(computer);
//		verify(mock, times(1)).update(any(Computer.class));
//	}
//
//	@Test
//	public void testUpdateReturnNull() {
//		when(mock.update(any(Computer.class))).thenReturn(null);
//		ComputerDTO computer = service.update(getComputer());
//		assertNull(computer);
//		verify(mock, times(1)).update(any(Computer.class));
//	}
//
//	@Test
//	public void testUpdate() {
//		when(mock.update(any(Computer.class))).thenReturn(getComputer());
//		ComputerDTO computer = service.update(getComputer());
//		assertEquals(getComputerDTO(), computer);
//		verify(mock, times(1)).update(any(Computer.class));
//
//	}
//
//	/**
//	 * Gets specific computer to mock dao.
//	 *
//	 * @return the computer
//	 */
//	private static Computer getComputer() {
//		return new Computer(1, "TEST", LocalDateTime.of(1991, 01, 01, 00, 00,
//				00), LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1,
//				"Apple Inc."));
//	}
//
//	private static ComputerDTO getComputerDTO() {
//		return ComputerDTO.toDTO(getComputer());
//	}
}
