package com.excilys.cdb.service;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest( { ComputerDaoImpl.class })
public class ComputerServiceTest {

	private ComputerServiceImpl mock = null;

	private ComputerDao dao;

	/*
	 * public void setUp(){ mock = Mockito.mock(ComputerServiceImpl.class); dao
	 * = ComputerDaoImpl.INSTANCE.getInstance(); }
	 * 
	 * public void tearDown(){ mock = null; }
	 */

	@Ignore
	public void testFind() {
		mock = Mockito.mock(ComputerServiceImpl.class);
		dao = ComputerDaoImpl.getInstance();

		doReturn(null).when(dao).find(1);
		Computer computer = mock.find(1);
		Assert.assertNull(computer);
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
}
