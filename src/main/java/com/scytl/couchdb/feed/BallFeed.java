package com.scytl.couchdb.feed;


import org.ektorp.CouchDbConnector;
import org.ektorp.changes.ChangesCommand;
import org.ektorp.impl.StdObjectMapperFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BallFeed implements Runnable{

	private final CouchDbConnector db;
	private ChangesCommand command;
	private final ObjectMapper objectMapper;
	
	public BallFeed(CouchDbConnector db, StdObjectMapperFactory objectMapperFactory) {
		super();
		this.db = db;
		this.objectMapper = objectMapperFactory.createObjectMapper();
	}

	public void run() {
		// TODO implement
		
	}

	public void setCommand(ChangesCommand command) {
		this.command = command;
	}
	
}
