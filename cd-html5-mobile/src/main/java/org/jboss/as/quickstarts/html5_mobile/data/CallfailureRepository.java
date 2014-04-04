package org.jboss.as.quickstarts.html5_mobile.data;

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

import com.conygre.training.entities.Callfailure;
import com.conygre.training.entities.query.UserStory05Structure;
import com.conygre.training.entities.query.UserStory06Structure;
import com.conygre.training.entities.query.UserStory07Structure;
import com.conygre.training.entities.query.UserStory09Structure;

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

}