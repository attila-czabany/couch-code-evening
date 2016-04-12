package com.scytl.couchdb.repository;

import com.scytl.couchdb.model.BingoCard;

import org.ektorp.CouchDbConnector;

public class BingoCardRepository{

	public BingoCardRepository(CouchDbConnector db, boolean createIfNotExists) {
		// TODO implement
	}

	public BingoCard getOneNonAssigned(){
		// TODO implement
		return null;
	}
	
	public BingoCard getAssignedCard(String userid){
		// TODO implement
		return null;
	}
	
	public void assignUser(BingoCard bingoCard, String userid){
		// TODO implement
	}
}
