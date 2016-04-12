package com.scytl.couchdb.spring;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.impl.StdObjectMapperFactory;
import org.ektorp.spring.HttpClientFactoryBean;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.scytl.couchdb.repository.BallRepository;
import com.scytl.couchdb.repository.BingoCardRepository;
import com.scytl.couchdb.repository.UserRepository;

@Configuration
@EnableConfigurationProperties(CouchDbIntegrationSettings.class)
public class CouchDbCrudContext {

	public static final String COUCH_DB_CONNECTOR_CARDS = "cards";

	public static final String COUCH_DB_CONNECTOR_USERS = "users";

	public static final String COUCH_DB_CONNECTOR_BALLS = "balls";

	@Autowired
	private CouchDbIntegrationSettings couchDbIntegrationSettings;

	@Bean
	public HttpClientFactoryBean httpClientFactoryBean(CouchDbIntegrationSettings settings) throws Exception {
		HttpClientFactoryBean factoryBean = new HttpClientFactoryBean();
		factoryBean.setHost(settings.getHost());
		factoryBean.setPort(settings.getPort());
		factoryBean.setMaxConnections(settings.getMaxConnections());
		factoryBean.setConnectionTimeout(settings.getConnectionTimeout());
		factoryBean.setSocketTimeout(settings.getSocketTimeout());
		factoryBean.setAutoUpdateViewOnChange(settings.isAutoUpdateViewOnChange());
		factoryBean.setUsername(settings.getUsername());
		factoryBean.setPassword(settings.getPassword());
		factoryBean.setTestConnectionAtStartup(settings.isTestConnectionAtStartup());
		factoryBean.setCleanupIdleConnections(settings.isCleanupIdleConnections());
		factoryBean.setEnableSSL(settings.isEnableSSL());
		factoryBean.setRelaxedSSLSettings(settings.isRelaxedSSLSettings());
		factoryBean.setCaching(settings.isCaching());
		factoryBean.setMaxCacheEntries(settings.getMaxCacheEntries());
		factoryBean.setMaxObjectSizeBytes(settings.getMaxObjectSizeBytes());
		factoryBean.setUseExpectContinue(settings.isUseExpectContinue());
		factoryBean.afterPropertiesSet();
		return factoryBean;
	}

	@Bean
	public CouchDbInstance couchDbInstance(HttpClientFactoryBean httpClientFactoryBean) {
		HttpClient httpClient;
		try {
			httpClient = httpClientFactoryBean.getObject();
		} catch (Exception e) {
			throw new BeanCreationException("Error creating CouchDbInstance", e);
		}
		return new StdCouchDbInstance(httpClient);
	}

	@Bean
	public StdObjectMapperFactory stdObjectMapperFactory()  {
		return new StdObjectMapperFactory();
	}

	@Bean(name = COUCH_DB_CONNECTOR_CARDS)
	public CouchDbConnector cardsConnector(CouchDbInstance instance, StdObjectMapperFactory factory) {
		return new StdCouchDbConnector(COUCH_DB_CONNECTOR_CARDS, instance, factory);
	}

	@Bean(name = COUCH_DB_CONNECTOR_USERS)
	public CouchDbConnector userConnector(CouchDbInstance instance, StdObjectMapperFactory factory) {
		return new StdCouchDbConnector(COUCH_DB_CONNECTOR_USERS, instance, factory);
	}

	@Bean
	public BingoCardRepository bingoCardRepository(
			@Qualifier(COUCH_DB_CONNECTOR_CARDS) CouchDbConnector outputsCouchDbConnector) {
		return new BingoCardRepository(outputsCouchDbConnector, true);
	}

	@Bean
	public UserRepository userRepository(
			@Qualifier(COUCH_DB_CONNECTOR_USERS) CouchDbConnector outputsCouchDbConnector) {
		return new UserRepository(outputsCouchDbConnector, true);
	}

	@Bean(name = COUCH_DB_CONNECTOR_BALLS)
	public CouchDbConnector ballsConnector(CouchDbInstance instance, StdObjectMapperFactory factory) {
		return new StdCouchDbConnector(COUCH_DB_CONNECTOR_BALLS, instance, factory);
	}

	@Bean
	public BallRepository ballRepository(
			@Qualifier(COUCH_DB_CONNECTOR_BALLS) CouchDbConnector outputsCouchDbConnector) {
		return new BallRepository(outputsCouchDbConnector, true);
	}

}
