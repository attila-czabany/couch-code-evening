package com.scytl.couchdb.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Holds CouchDB configuration parameters.
 */
@ConfigurationProperties(prefix = CouchDbIntegrationSettings.COUCH_DB_PREFIX)
public class CouchDbIntegrationSettings {

	public static final String COUCH_DB_PREFIX = "couchdb";
	private boolean enabled;
	private String url;
	private String host = "localhost";
	private int port = 5984;
	private int maxConnections = 20;
	private int connectionTimeout = 1000;
	private int socketTimeout = 10000;

	/**
	 * Automatically updates the views of the databases if they have been changed.
	 */
	private boolean autoUpdateViewOnChange = true;
	private String username = null;
	private String password = null;
	private boolean testConnectionAtStartup = enabled;
	private boolean cleanupIdleConnections = false;
	private boolean enableSSL = false;
	private boolean relaxedSSLSettings;
	private boolean caching = true;
	private int maxCacheEntries = 1000;
	private int maxObjectSizeBytes = 8192;
	private boolean useExpectContinue = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public boolean isAutoUpdateViewOnChange() {
		return autoUpdateViewOnChange;
	}

	public void setAutoUpdateViewOnChange(boolean autoUpdateViewOnChange) {
		this.autoUpdateViewOnChange = autoUpdateViewOnChange;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTestConnectionAtStartup() {
		return testConnectionAtStartup;
	}

	public void setTestConnectionAtStartup(boolean testConnectionAtStartup) {
		this.testConnectionAtStartup = testConnectionAtStartup;
	}

	public boolean isCleanupIdleConnections() {
		return cleanupIdleConnections;
	}

	public void setCleanupIdleConnections(boolean cleanupIdleConnections) {
		this.cleanupIdleConnections = cleanupIdleConnections;
	}

	public boolean isEnableSSL() {
		return enableSSL;
	}

	public void setEnableSSL(boolean enableSSL) {
		this.enableSSL = enableSSL;
	}

	public boolean isRelaxedSSLSettings() {
		return relaxedSSLSettings;
	}

	public void setRelaxedSSLSettings(boolean relaxedSSLSettings) {
		this.relaxedSSLSettings = relaxedSSLSettings;
	}

	public boolean isCaching() {
		return caching;
	}

	public void setCaching(boolean caching) {
		this.caching = caching;
	}

	public int getMaxCacheEntries() {
		return maxCacheEntries;
	}

	public void setMaxCacheEntries(int maxCacheEntries) {
		this.maxCacheEntries = maxCacheEntries;
	}

	public int getMaxObjectSizeBytes() {
		return maxObjectSizeBytes;
	}

	public void setMaxObjectSizeBytes(int maxObjectSizeBytes) {
		this.maxObjectSizeBytes = maxObjectSizeBytes;
	}

	public boolean isUseExpectContinue() {
		return useExpectContinue;
	}

	public void setUseExpectContinue(boolean useExpectContinue) {
		this.useExpectContinue = useExpectContinue;
	}
	
}
