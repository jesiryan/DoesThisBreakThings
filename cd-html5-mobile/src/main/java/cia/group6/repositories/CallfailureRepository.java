package cia.group6.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;






import cia.group6.entities.Callfailure;
import cia.group6.story.structures.UserStory05Structure;
import cia.group6.story.structures.UserStory06Structure;
import cia.group6.story.structures.UserStory07Structure;
import cia.group6.story.structures.UserStory08Structure;
import cia.group6.story.structures.UserStory09Structure;
import cia.group6.story.structures.UserStory10Structure;
import cia.group6.story.structures.UserStory11Structure;
import cia.group6.story.structures.UserStory12Structure;
import cia.group6.story.structures.UserStory13Structure;
import cia.group6.story.structures.UserStory14Structure;

@ApplicationScoped
public class CallfailureRepository {
	
	Connection connection = null;
	PreparedStatement loginStatement = null;
	PreparedStatement toDoStatement = null;
	ResultSet loginResultSet = null;
	ResultSet toDoResultSet = null;

    //@Inject
    @PersistenceContext(unitName="sprint2")
    private EntityManager em;

    public Callfailure findByBaseDataID(int baseDataID) {
        return em.find(Callfailure.class, baseDataID);
    }
    
	public void mergeCallfailure(Callfailure callfailure) {
		em.merge(callfailure);
	}

    public Callfailure findByIMSI(String iMSI) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Callfailure> criteria = cb.createQuery(Callfailure.class);
        Root<Callfailure> callfailure = criteria.from(Callfailure.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(callfailure).where(cb.equal(callfailure.get("iMSI"), iMSI));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Callfailure> findAllOrderedByIMSI() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Callfailure> criteria = cb.createQuery(Callfailure.class);
        Root<Callfailure> callfailure = criteria.from(Callfailure.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(callfailure).orderBy(cb.asc(callfailure.get("iMSI")));
        return em.createQuery(criteria).getResultList();
    }
    
    public List<Callfailure> findAllBetween(Date startDateTime, Date endDateTime) {
   		List<Callfailure> callFailures = (List<Callfailure>) em
				.createNamedQuery("CallFailure.findAllBetween")
				.setParameter("startDateTime", startDateTime)
				.setParameter("endDateTime", endDateTime).getResultList();
		if (callFailures.size() == 0)
			return null;
		else
			return callFailures;
    }
    
    // User story 4
    public List<Callfailure> findCauseCode_EventIDByIMSI(String IMSI) {
		List<Callfailure> callfailures = (List<Callfailure>) 
				em.createNamedQuery("Callfailure.findByIMSI").setParameter("IMSI", IMSI).getResultList();
		if (callfailures.size() == 0)
			return null;
		else
			return callfailures;
	}
    
    // User story 5
	public List<UserStory05Structure> findCallByIMSIBetweenDate(String IMSI, String startDateTime, String endDateTime) {
		List<UserStory05Structure> us05List = new ArrayList<UserStory05Structure>();
		
		String loginQueryString = "SELECT iMSI, COUNT(*) FROM callfailure WHERE dateTime > ? AND dateTime < ? AND iMSI=?";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, startDateTime);
			loginStatement.setString(2, endDateTime);
			loginStatement.setString(3, IMSI);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				us05List.add(new UserStory05Structure(startDateTime, endDateTime, (String)loginResultSet.getString(1), Integer.parseInt(loginResultSet.getString(2))));
			}
		} catch (SQLException e) { e.printStackTrace(); }		

		if (us05List.size() == 0)
			return null;
		else
			return us05List;
	}
	
	// User story 6
	public List<UserStory06Structure> findUserStory06(String imsi) {
		List<UserStory06Structure> userStory06Structures = new ArrayList<UserStory06Structure>();
		
		String loginQueryString = "SELECT DISTINCT iMSI, cause_causeCode, cause_eventid, cause.description FROM callfailure, cause where iMSI = ? and callfailure.cause_causeCode = cause.causeCode and callfailure.cause_eventid = cause.eventid ORDER BY cause_causeCode";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, imsi);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				userStory06Structures.add(new UserStory06Structure((String)loginResultSet.getString(1), Double.parseDouble(loginResultSet.getString(2)), Double.parseDouble(loginResultSet.getString(3)), (String)loginResultSet.getString(4)));
			}
		} catch (SQLException e) { e.printStackTrace(); }	
		
		
		if (userStory06Structures.size() == 0)
			return null;
		else
			return userStory06Structures;
	}
	
	
	// User story 7
	public List<UserStory07Structure> findAllCallFailuresBetween(String startDateTime, String endDateTime) {
		List<UserStory07Structure> us07List = new ArrayList<UserStory07Structure>();

		
		String loginQueryString = "SELECT DISTINCT iMSI, COUNT(*) FROM callfailure WHERE dateTime > ? AND dateTime < ? GROUP BY iMSI";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, startDateTime);
			loginStatement.setString(2, endDateTime);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				us07List.add(
						new UserStory07Structure((String)loginResultSet.getString(1), Integer.parseInt(loginResultSet.getString(2)), startDateTime, endDateTime));
			}
		} catch (SQLException e) { e.printStackTrace(); }	
		
		
		if (us07List.size() == 0)
			return null;
		else
			return us07List;
	}
	
	
	// User story 8
		public List<UserStory08Structure> findAllCallFailuresBetweenDatesByModel(String startDateTime, String endDateTime, String model) {

			List<UserStory08Structure> us08List = new ArrayList<UserStory08Structure>();

			String loginQueryString ="SELECT count(*) FROM Callfailure, Equipment WHERE dateTime > ? AND dateTime < ? AND Equipment.tAC = Callfailure.equipment_tAC  AND Equipment.model = ?";	
			try {
					connection = ConnectionFactory.getInstance().getConnection();
					loginStatement = connection.prepareStatement(loginQueryString);
				loginStatement.setString(1, startDateTime);
				loginStatement.setString(2, endDateTime);
				loginStatement.setString(3, model);
		
				loginResultSet = loginStatement.executeQuery();
					while (loginResultSet.next()){
						us08List.add(new UserStory08Structure(model, loginResultSet.getInt(1),startDateTime,endDateTime));
								
				}
				} catch (SQLException e) { e.printStackTrace(); }	
			
			
			if (us08List.size() == 0)
				return null;
			else
				return us08List;
		}

    	
    // User story 9
    public List<UserStory09Structure> findCountBetweenTimesTotalDuration(String startDateTime, String endDateTime) {
		List<UserStory09Structure> us09List = new ArrayList<UserStory09Structure>();

		String loginQueryString = "SELECT iMSI, COUNT(*), SUM(duration) FROM callfailure WHERE dateTime > ? AND dateTime < ? GROUP BY iMSI";
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, startDateTime);
			loginStatement.setString(2, endDateTime);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				us09List.add(new UserStory09Structure((String)loginResultSet.getString(1), Integer.parseInt(loginResultSet.getString(2)), Integer.parseInt(loginResultSet.getString(3))));
			}
		} catch (SQLException e) { e.printStackTrace(); }		

		if (us09List.size() == 0)
			return null;
		else
			return us09List;
	}
    
    // User story 11
    public List<UserStory11Structure> findTop10failsForENodeB(String startDateTime, String endDateTime) {
		List<UserStory11Structure> us11List = new ArrayList<UserStory11Structure>();

		String loginQueryString = "SELECT count(*) as totalFAILS,countryOperator_mCC, countryOperator_mNC, cellId, country, operator, "
				+" count(case when failureClass_failureClass = 0 then 1 else null end) as class0,"
				+" count(case when failureClass_failureClass = 1 then 1 else null end) as class1,"
				+" count(case when failureClass_failureClass = 2 then 1 else null end) as class2,"
				+" count(case when failureClass_failureClass = 3 then 1 else null end) as class3,"
				+" count(case when failureClass_failureClass = 4 then 1 else null end) as class4"
				+" FROM callfailure,countryoperator WHERE countryOperator_mCC = mCC "
		+"AND countryOperator_mNC = mNC AND dateTime > ? AND dateTime < ? GROUP BY countryOperator_mCC, countryOperator_mNC, cellId ORDER BY totalFAILS DESC LIMIT 10;";
				
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, startDateTime);
			loginStatement.setString(2, endDateTime);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				us11List.add(new UserStory11Structure(loginResultSet.getInt(1), loginResultSet.getInt(2), loginResultSet.getInt(3), loginResultSet.getInt(4), 
						loginResultSet.getString(5), loginResultSet.getString(6),loginResultSet.getInt(7),loginResultSet.getInt(8),loginResultSet.getInt(9),
						loginResultSet.getInt(10),loginResultSet.getInt(11)));
			}
			
			
			
		} catch (SQLException e) { e.printStackTrace(); }		

		if (us11List.size() == 0)
			return null;
		else
			return us11List;
	}
    
    // User story 12
    public List<UserStory12Structure> findTop10CountBetweenTimesTotalDuration(String startDateTime, String endDateTime) {
		List<UserStory12Structure> us12List = new ArrayList<UserStory12Structure>();

		String loginQueryString = "SELECT iMSI, COUNT(*),"
				+ " count(case when failureClass_failureClass = 0 then 1 else null end) as class0,"
				+" count(case when failureClass_failureClass = 1 then 1 else null end) as class1,"
				+" count(case when failureClass_failureClass = 2 then 1 else null end) as class2,"
				+" count(case when failureClass_failureClass = 3 then 1 else null end) as class3,"
				+" count(case when failureClass_failureClass = 4 then 1 else null end) as class4"
				+" FROM callfailure WHERE dateTime > ? AND dateTime < ? GROUP BY iMSI ORDER BY count(*) DESC LIMIT 10";

				
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, startDateTime);
			loginStatement.setString(2, endDateTime);
			loginResultSet = loginStatement.executeQuery();
			
			while (loginResultSet.next()) {
				us12List.add(new UserStory12Structure((String)loginResultSet.getString(1), Integer.parseInt(loginResultSet.getString(2)), Integer.parseInt(loginResultSet.getString(3)), Integer.parseInt(loginResultSet.getString(4)), Integer.parseInt(loginResultSet.getString(5)), Integer.parseInt(loginResultSet.getString(6)), Integer.parseInt(loginResultSet.getString(7))));
			}
			
			
			
		} catch (SQLException e) { e.printStackTrace(); }		

		if (us12List.size() == 0)
			return null;
		else
			return us12List;
	}
    

 // User story 13
     public List<UserStory13Structure> findAllTimeTop(String startDateTime, String endDateTime) {
 		List<UserStory13Structure> us13List = new ArrayList<UserStory13Structure>();

 		String loginQueryString = "SELECT count(*) as totalFAILS,countryOperator_mCC, countryOperator_mNC, cellId, country, operator, "
 				+"count(case when failureClass_failureClass = 0 then 1 else null end) as class0,"
 				+"count(case when failureClass_failureClass = 1 then 1 else null end) as class1,"
 				+"count(case when failureClass_failureClass = 2 then 1 else null end) as class2,"
 				+"count(case when failureClass_failureClass = 3 then 1 else null end) as class3,"
 				+"count(case when failureClass_failureClass = 4 then 1 else null end) as class4,"
 				+"(COUNT(*)/ T.total * 100) AS percent"
 				+" FROM callfailure,countryoperator, (SELECT COUNT(*) AS total FROM callfailure) AS T WHERE countryOperator_mCC = mCC "
 		+"AND countryOperator_mNC = mNC AND dateTime > ? AND dateTime < ? GROUP BY countryOperator_mCC, countryOperator_mNC, cellId ORDER BY totalFAILS DESC LIMIT 10;";
 				
 		try {
 			connection = ConnectionFactory.getInstance().getConnection();
 			loginStatement = connection.prepareStatement(loginQueryString);
 			loginStatement.setString(1, startDateTime);
 			loginStatement.setString(2, endDateTime);
 			loginResultSet = loginStatement.executeQuery();
 			
 			while (loginResultSet.next()) {
 				us13List.add(new UserStory13Structure(loginResultSet.getInt(1), loginResultSet.getDouble(2), loginResultSet.getDouble(3), loginResultSet.getInt(4), 
 						loginResultSet.getString(5), loginResultSet.getString(6),loginResultSet.getInt(7),loginResultSet.getInt(8),loginResultSet.getInt(9),
 						loginResultSet.getInt(10),loginResultSet.getInt(11), loginResultSet.getDouble(12)));
 			}
 			
 			
 			
 		} catch (SQLException e) { e.printStackTrace(); }		

 		if (us13List.size() == 0)
 			return null;
 		else
 			return us13List;
 	}

    
    // User story 14
 	public List<UserStory14Structure> findAffectedIMSIsGivenCauseClass(double causeCode, double eventId) {
 		List<UserStory14Structure> us14List = new ArrayList<UserStory14Structure>();

 		
 		String loginQueryString = "SELECT DISTINCT callfailure.iMSI, COUNT(*), callfailure.cause_causeCode, callfailure.cause_eventId, cause.description FROM callfailure, cause WHERE callfailure.cause_causeCode = ? AND callfailure.cause_eventId = ? AND cause.eventId = callfailure.cause_eventId AND cause.causeCode = callfailure.cause_causeCode GROUP BY iMSI;";
 		try {
 			connection = ConnectionFactory.getInstance().getConnection();
 			loginStatement = connection.prepareStatement(loginQueryString);
 			loginStatement.setString(1, ""+causeCode);
 			loginStatement.setString(2, ""+eventId);
 			loginResultSet = loginStatement.executeQuery();
 			
 			while (loginResultSet.next()) {
 				us14List.add(
 						new UserStory14Structure(loginResultSet.getString(1), Integer.parseInt(loginResultSet.getString(2)), Double.parseDouble(loginResultSet.getString(3)), Double.parseDouble(loginResultSet.getString(4)), loginResultSet.getString(5)));
 			}
 		} catch (SQLException e) { e.printStackTrace(); }	
 		 		
 		if (us14List.size() == 0)
 			return null;
 		else
 			return us14List;
 	}

	public List<UserStory10Structure> findEventCauseForModel(String modelString) {
		List<UserStory10Structure> us10List = new ArrayList<UserStory10Structure>();

		String loginQueryString = "SELECT cause_causeCode, cause_eventid, cause.description, count(*)" 
				+" FROM callfailure, cause, equipment where equipment_tac = tac"  
				+" AND callfailure.cause_causeCode = cause.causeCode AND callfailure.cause_eventid = cause.eventid" 
				+" AND model = ? group by cause_causeCode, cause_eventid;";			
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			loginStatement = connection.prepareStatement(loginQueryString);
			loginStatement.setString(1, modelString);
			loginResultSet = loginStatement.executeQuery();
			
			
			while (loginResultSet.next()) {
				us10List.add(new UserStory10Structure(loginResultSet.getDouble(1),loginResultSet.getDouble(2),loginResultSet.getString(3),loginResultSet.getInt(4)));
			}
						
			
		} catch (SQLException e) { e.printStackTrace(); }		

		if (us10List.size() == 0)
			return null;
		else
			return us10List;
	}
}



