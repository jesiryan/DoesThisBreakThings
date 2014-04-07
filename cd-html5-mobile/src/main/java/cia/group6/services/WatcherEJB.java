package cia.group6.services;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class WatcherEJB {

	public WatcherEJB() {

	}

	@PostConstruct
	void atStartup() {
		new WatcherThread().start();
	}
	
}
