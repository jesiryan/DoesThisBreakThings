//package cia.group6.registration.test.arquillian;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.logging.Logger;
//
//import javax.inject.Inject;
//import javax.persistence.PersistenceUtil;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import cia.group6.entities.AllMasterTableRows;
//import cia.group6.fileimport.CauseReader;
//import cia.group6.fileimport.EquipmentReader;
//import cia.group6.resources.Resources;
//
///**
// * Uses Arquilian to test the JAX-RS processing class for callfailure registration.
// * 
// */
//@RunWith(Arquillian.class)
//public class TestcallfailureRepository {
//   @Deployment
//   public static Archive<?> createTestArchive() {
//      return ShrinkWrap.create(WebArchive.class, "test.war")
//            .addClasses(cia.group6.entities.callfailure.class, 
//            		cia.group6.entities.Cause.class, 
//            		cia.group6.entities.Countryoperator.class, 
//            		cia.group6.entities.Equipment.class, 
//            		cia.group6.entities.Failureclass.class, Resources.class)
//            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
//            .addAsWebInfResource("arquillian-ds.xml")
//            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//   }
//
//   @Inject
//   callfailureService callfailureRegistration;
//
//   @Inject
//   Logger log;
//
//   private failureclassReader failureclassReader;
//	private CauseReader causeReader;
//	private countryoperatorReader countryoperatorReader;
//	private EquipmentReader equipmentReader;
//	private callfailureReader callfailureReader;
//	private callfailure callfailure;
//	private AllMasterTableRows allMasterTableRows;
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		PersistenceUtil.filePath = "test.xls";
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		failureclassReader = new failureclassReader();
//		causeReader = new CauseReader();
//		countryoperatorReader = new countryoperatorReader();
//		equipmentReader = new EquipmentReader();
//		callfailureReader = new callfailureReader();
//		allMasterTableRows = new AllMasterTableRows();
//		allMasterTableRows.setfailureclasses(failureclassReader
//				.getAllfailureclassRows());
//		allMasterTableRows.setCauses(causeReader.getAllEventCauseRows());
//		allMasterTableRows.setcountryoperators(countryoperatorReader
//				.getAllcountryoperatorRows());
//		allMasterTableRows.setEquipment(equipmentReader.getAllEquipmentRows());
//		callfailureReader.setLocalTableLists(allMasterTableRows);
//		callfailure = callfailureReader.getOnecallfailureRow(5);
//		System.out.println("here");
//		System.out.println(callfailure);
//	}
//
//	@Test
//	public void testHier321True() {
//		System.out.println(callfailure);
//		assertEquals("should be equal", "1150444940909480000", callfailure.getHier321());
//	}
//}
