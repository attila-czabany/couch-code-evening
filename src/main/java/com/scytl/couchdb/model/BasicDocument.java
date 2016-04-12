package com.scytl.couchdb.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.ektorp.support.CouchDbDocument;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicDocument extends CouchDbDocument {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("_deleted")
    private boolean deleted;

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("_id", getId())
				.append("_revision", getRevision())
				.toString();
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
