package com.reza.importdata.model;

import java.time.LocalDateTime;

/**
 * This class is the main DTO of the application 
 * @author PRSHM
 *
 */
public class MarketPrice {
	private LocalDateTime originalDateTime;
	private String hubname;
	private Float lmp;
	private Float loss;
	private Float congestion;
	
	
	public MarketPrice() {
	}
	
	public MarketPrice(LocalDateTime originalDateTime, String hubname, Float lmp, Float loss, Float congestion){
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
	public String getHubname() {
		return hubname;
	}
	public void setHubname(String hubname) {
		this.hubname = hubname;
	}
	public Float getLmp() {
		return lmp;
	}
	public void setLmp(Float lmp) {
		this.lmp = lmp;
	}
	public Float getLoss() {
		return loss;
	}
	public void setLoss(Float loss) {
		this.loss = loss;
	}
	public Float getCongestion() {
		return congestion;
	}
	public void setCongestion(Float congestion) {
		this.congestion = congestion;
	}

	@Override
	public String toString() {
		return "MarketPrice [originalDateTime=" + originalDateTime + ", hubname=" + hubname + ", lmp=" + lmp + ", loss="
				+ loss + ", congestion=" + congestion + "]";
	}
	
	
}
