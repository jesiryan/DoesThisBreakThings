package cia.group6.dao.jpa;

import cia.group6.entities.CompactDisc;

public class TestJPACompactDiscDAO {

	public static void main(String[] args) {
		CompactDisc cd = new CompactDisc("title1", 14.99, "Albumn");
		JPACompactDiscDAO jpa = new JPACompactDiscDAO();
		if(jpa.addCompactDisc(cd)){
			System.out.println("persisted");
		}
		else{
			System.out.println("didn't persist");
		}
	}

}
