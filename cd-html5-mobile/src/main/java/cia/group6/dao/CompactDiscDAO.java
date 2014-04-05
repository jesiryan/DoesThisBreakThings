package cia.group6.dao;

import java.util.Collection;
import java.util.List;

import cia.group6.entities.CompactDisc;

public interface CompactDiscDAO {
	
	Boolean addCompactDisc(CompactDisc disc);
	CompactDisc getCompactDiscByTitle(String title);
	List<CompactDisc> getDiscsByArtist(String artist);
	CompactDisc  findById(int id); 
	List<CompactDisc> getAllDiscs();
}
