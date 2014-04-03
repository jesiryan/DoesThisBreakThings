package org.jboss.as.quickstarts.html5_mobile.data;

import org.jboss.as.quickstarts.html5_mobile.model.Member;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class MemberRepository {
	Connection connection = null;
	PreparedStatement loginStatement = null;
	ResultSet loginResultSet = null;


    //@Inject
    @PersistenceContext(unitName="conygreChapter8")
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(member).where(cb.equal(member.get("name"), name));
        return em.createQuery(criteria).getSingleResult();
    }
    public Member findByNameAndPass(String name, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.name), email));
        criteria.select(member).where(cb.equal(member.get("name"), name)).where(cb.equal(member.get("password"), password));
        return em.createQuery(criteria).getSingleResult();
    }
    public String[] getUserByNameAndPass(String name, String password){
		String loginQueryString = "SELECT username, password, userType FROM user WHERE username = ? and password=?";
		String[] result = new String[3];
		//		String loginQueryString = "SELECT username, password, userType FROM User WHERE username = ? and password=?";
		try {
		connection = ConnectionFactory.getInstance().getConnection();
		loginStatement = connection.prepareStatement(loginQueryString);
		loginStatement.setString(1, name);
		loginStatement.setString(2, password);
		loginResultSet = loginStatement.executeQuery();		

		while (loginResultSet.next()) {			
			System.out.println("=========================================||||||||||||||||||||||||||||||||||||||||||||||||||---------------------------------------------------------------------");
			System.out.println(loginResultSet.getString(1));
			System.out.println(loginResultSet.getString(2));
			System.out.println(loginResultSet.getString(3));
			System.out.println("=========================================||||||||||||||||||||||||||||||||||||||||||||||||||---------------------------------------------------------------------");
			result[0] = loginResultSet.getString(1);
			result[1] = loginResultSet.getString(2);
			result[2] = loginResultSet.getString(3);
			return result;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;   	
    }
    public List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }
}
