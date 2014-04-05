package cia.group6.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;










import cia.group6.dao.CompactDiscDAO;
import cia.group6.dao.jpa.JPA;
import cia.group6.entities.CompactDisc;
import cia.group6.entities.Member;
@Stateless
//@WebService(endpointInterface="com.conygre.training.services.CompactDiscService")
@Local(CompactDiscService.class)

public class CompactDiscServiceEJB implements CompactDiscService {
	
	private CompactDiscDAO dao;
	
	@Inject
	public void setDao(@JPA CompactDiscDAO dao) {
		this.dao = dao;
	}

	public void addToCatalog(CompactDisc disc) {
		if (!dao.getAllDiscs().contains(disc))
			dao.addCompactDisc(disc);
	}
	
	public List<CompactDisc> getCatalog() {
		List<CompactDisc> cds = dao.getAllDiscs();
		for (CompactDisc cd : cds) {
			System.out.println(cd.getTitle());
		}
		
		return cds;
	}

	public CompactDisc getDiscById(int id) {
		// TODO Auto-generated method stub
		CompactDisc cd = dao.findById(id);
		return cd;
	}



}
