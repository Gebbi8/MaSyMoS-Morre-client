package de.unirostock.sems.morre.client.dataholder;

import java.io.Serializable;

/**
 * Dataholder Class for a Model
 * @see ModelResult
 *
 */
public class Model implements Serializable {

	private static final long serialVersionUID = -7484517560145746179L;
	
	protected String modelName;
	protected String modelID;
	protected String versionID;
	protected String xmldoc;
	protected String documentURI;
	protected String fileId;
	
	public Model(String modelName, String modelID, String versionID,
			String xmldoc, String documentURI, String fileId) {
		super();
		this.modelName = modelName;
		this.modelID = modelID;
		this.versionID = versionID;
		this.xmldoc = xmldoc;
		this.documentURI = documentURI;
		this.fileId = fileId;
	}

	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelID() {
		return modelID;
	}


	public void setModelID(String modelId) {
		this.modelID = modelId;
	}

	public String getDocumentURI() {
		return documentURI;
	}


	public void setDocumentURI(String documentUri) {
		this.documentURI = documentUri;
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String filename) {
		this.fileId = filename;
	}

	public String getVersionID() {
		return versionID;
	}

	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}

	public String getXmldoc() {
		return xmldoc;
	}

	public void setXmldoc(String xmldoc) {
		this.xmldoc = xmldoc;
	}

	@Override
	public String toString() {
		return "ModelResult [modelName=" + modelName + ", modelId=" + modelID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((documentURI == null) ? 0 : documentURI.hashCode());
		result = prime * result
				+ ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((modelID == null) ? 0 : modelID.hashCode());
		result = prime * result
				+ ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result
				+ ((versionID == null) ? 0 : versionID.hashCode());
		result = prime * result + ((xmldoc == null) ? 0 : xmldoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (documentURI == null) {
			if (other.documentURI != null)
				return false;
		} else if (!documentURI.equals(other.documentURI))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (modelID == null) {
			if (other.modelID != null)
				return false;
		} else if (!modelID.equals(other.modelID))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (versionID == null) {
			if (other.versionID != null)
				return false;
		} else if (!versionID.equals(other.versionID))
			return false;
		if (xmldoc == null) {
			if (other.xmldoc != null)
				return false;
		} else if (!xmldoc.equals(other.xmldoc))
			return false;
		return true;
	}

	
}
