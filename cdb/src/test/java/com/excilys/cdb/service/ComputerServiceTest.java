package com.excilys.cdb.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Assert;
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

@RunWith(PowerMockRunner.class)
@PrepareForTest( { ComputerDaoImpl.class })
public class ComputerServiceTest {
/*  http://stackoverflow.com/questions/15939023/how-to-mock-an-enum-singleton-class-using-mockito-powermock
 * */
	private ComputerService service = null;

	private ComputerDao mock = null;

	public void setUp(){ 
		mock = Mockito.mock(ComputerDao.class); 
		Whitebox.setInternalState(ComputerDao.class, "INSTANCE", mock);
		service = ComputerServiceImpl.INSTANCE.getInstance(); 
	}

	public void tearDown(){ mock = null; }


	@Test
	public void testFindReturnNull() {
		mock = Mockito.mock(ComputerDao.class);

		when(mock.find(1)).thenReturn(null);
		Computer computer = mock.find(1);
		Assert.assertNull(computer);
		verify(mock, times(1)).find(eq(1));
	}
	
	@Test
	public void testFindReturnValue() {
		mock = Mockito.mock(ComputerDao.class);

		when(mock.find(1)).thenReturn(getComputer());
		Computer computer = mock.find(1);
		assertEquals(getComputer() , computer);
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

	@Ignore
	public void testUpdate() {

	}
	
	private static Computer getComputer(){
		return new Computer(1, "TEST", LocalDateTime.of(1991, 01, 01, 00, 00, 00),
		LocalDateTime.of(1993, 02, 01, 00, 00, 00), new Company(1, "Apple Inc."));
	}
}
