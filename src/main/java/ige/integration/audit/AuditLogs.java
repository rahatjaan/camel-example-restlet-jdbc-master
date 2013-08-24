package ige.integration.audit;

import java.util.Date;

public class AuditLogs implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer auditlogsId;
    private String createdBy;
    private Date createdDate;
    private String actionDescription;
    private String actionResult;
    private String userName;
    private String tenantId;
    private String requestPayload;
    private String responsePayload;

    public AuditLogs() {
    }

    public AuditLogs(String createdBy, Date createdDate, String actionDescription, String actionResult, String userName, String tenantId, String requestPayload, String responsePayload) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.actionDescription = actionDescription;
        this.actionResult = actionResult;
        this.userName = userName;
        this.tenantId = tenantId;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
    }

	public Integer getAuditlogsId() {
		return auditlogsId;
	}

	public void setAuditlogsId(Integer auditlogsId) {
		this.auditlogsId = auditlogsId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getActionDescription() {
		return actionDescription;
	}

	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	public String getActionResult() {
		return actionResult;
	}

	public void setActionResult(String actionResult) {
		this.actionResult = actionResult;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}

	public String getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
    
}
