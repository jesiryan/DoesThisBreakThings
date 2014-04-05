package cia.group6.services;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.jws.WebService;

import cia.group6.entities.CompactDisc;
import cia.group6.entities.Member;
//@WebService
@Local
public interface CompactDiscService {

	public List<CompactDisc> getCatalog() ;
	public CompactDisc getDiscById(int id);
	public void addToCatalog(CompactDisc disc);
	
}
