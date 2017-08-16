package com.reza.importdata.model;

import java.time.LocalDateTime;

public class MarketPrice {
	private LocalDateTime originalDateTime;
	private int hubname;
	private int lmp;
	private int loss;
	private int congestion;
	
	
	public MarketPrice() {
		
	}
	
	public MarketPrice(LocalDateTime originalDateTime, int hubname, int lmp, int loss, int congestion){
		this.originalDateTime = originalDateTime;
		this.hubname = hubname;
		this.lmp = lmp;
		this.loss = loss;
		this.congestion = congestion;
	}
	
	public LocalDateTime getOriginalDateTime() {
		return originalDateTime;
	}
	public void setOriginalDateTime(LocalDateTime originalDateTime) {
		this.originalDateTime = originalDateTime;
	}
	public int getHubname() {
		return hubname;
	}
	public void setHubname(int hubname) {
		this.hubname = hubname;
	}
	public int getLmp() {
		return lmp;
	}
	public void setLmp(int lmp) {
		this.lmp = lmp;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public int getCongestion() {
		return congestion;
	}
	public void setCongestion(int congestion) {
		this.congestion = congestion;
	}
	
}
