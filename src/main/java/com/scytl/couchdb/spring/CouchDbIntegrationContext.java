package com.scytl.couchdb.spring;

import com.scytl.couchdb.feed.BallFeed;

import org.ektorp.CouchDbConnector;
import org.ektorp.impl.StdObjectMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(CouchDbIntegrationSettings.class)
@Import({CouchDbCrudContext.class})
public class CouchDbIntegrationContext {


	@Autowired
	private CouchDbIntegrationSettings couchDbIntegrationSettings;

	@Bean
	public BallFeed ballFeed(
			@Qualifier(CouchDbCrudContext.COUCH_DB_CONNECTOR_BALLS) CouchDbConnector configurationsCouchDbConnector,
			StdObjectMapperFactory stdObjectMapperFactory) {
		return new BallFeed(configurationsCouchDbConnector, stdObjectMapperFactory);
	}

}
