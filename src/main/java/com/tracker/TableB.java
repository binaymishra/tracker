package com.tracker;

public class TableB {
	
	String userIp;
	String userMac;
	String apName;
	String apMac;
	Long startTime;
	Long endTime;
	
	public TableB() {
		// TODO Auto-generated constructor stub
	}

	public TableB(String userIp, String userMac, String apName, String apMac, Long startTime, Long endTime) {
		super();
		this.userIp = userIp;
		this.userMac = userMac;
		this.apName = apName;
		this.apMac = apMac;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getUserMac() {
		return userMac;
	}

	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}

	public String getApMac() {
		return apMac;
	}

	public void setApMac(String apMac) {
		this.apMac = apMac;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TrackerRecord [userIp=" + userIp + ", userMac=" + userMac + ", apName=" + apName + ", apMac=" + apMac
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
