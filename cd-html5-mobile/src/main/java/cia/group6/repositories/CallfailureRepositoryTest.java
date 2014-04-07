package cia.group6.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cia.group6.entities.Callfailure;
import cia.group6.entities.Cause;
import cia.group6.entities.Countryoperator;
import cia.group6.entities.Equipment;
import cia.group6.entities.Failureclass;

public class CallfailureRepositoryTest {
	
	@Test
	public final void testFindByBaseDataID() {
		int baseDataID = 1;
		CallfailureRepository cfr = new CallfailureRepository();
		assertSame(cfr.findByBaseDataID(baseDataID).getClass(), Callfailure.class);
		assertNotSame(cfr.findByBaseDataID(baseDataID).getClass(), List.class);
		assertNotSame(cfr.findByBaseDataID(baseDataID).getClass(), Cause.class);
		assertNotSame(cfr.findByBaseDataID(baseDataID).getClass(), Countryoperator.class);
	}
	
	@Test
	public final void testFindByIMSI() {
		String iMSI = "310560000000012";
		CallfailureRepository cfr = new CallfailureRepository();
		assertSame(cfr.findByIMSI(iMSI).getClass(), Callfailure.class);
		assertNotSame(cfr.findByIMSI(iMSI).getClass(), List.class);
		assertNotSame(cfr.findByIMSI(iMSI).getClass(), Cause.class);
		assertNotSame(cfr.findByIMSI(iMSI).getClass(), Countryoperator.class);
	}
	
	@Test
	public final void testMergeCallfailure(){
		CallfailureRepository cfr = new CallfailureRepository();
		Callfailure callfailure = new Callfailure(200099, 123, new Date(), 50, "hier3", "hier32", "hier321", "iMSI", "nEVersion", new Equipment(), new Failureclass(), new Countryoperator(), new Cause());
		assertEquals(cfr.findByBaseDataID(200099), callfailure);
		assertNotSame(cfr.findByBaseDataID(200099), Callfailure.class);
		assertNotSame(cfr.findByBaseDataID(990022), callfailure);
		assertSame(cfr.findByBaseDataID(200099).getClass(), Callfailure.class);
		assertSame(cfr.findByBaseDataID(200099).getClass(), Equipment.class);
		assertSame(cfr.findByBaseDataID(200099).getClass(), Cause.class);
	}
	
	@Test 
	public final void testFindAllOrderedByIMSI(){
		CallfailureRepository cfr = new CallfailureRepository();
		assertSame(cfr.findAllOrderedByIMSI().get(0).getClass(), Callfailure.class);
		assertNotSame(cfr.findAllOrderedByIMSI().get(0).getClass(), List.class);
	}
	
	@Test
	public final void testAllBetween(){
		CallfailureRepository cfr = new CallfailureRepository();
		assertSame(cfr.findAllBetween(new Date(2013-01-01), new Date(2014-01-01)).get(0).getClass(), Callfailure.class);
		assertNotSame(cfr.findAllBetween(new Date(2013-01-01), new Date(2014-01-01)).get(0).getClass(), Cause.class);
	}

}
