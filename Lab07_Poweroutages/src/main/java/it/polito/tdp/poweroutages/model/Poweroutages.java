package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Poweroutages implements Comparable {
	private int id;
	private Nerc nerc;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private long durata;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nerc
	 */
	public Nerc getNerc() {
		return nerc;
	}
	/**
	 * @param nerc the nerc to set
	 */
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	/**
	 * @return the customersAffected
	 */
	public int getCustomersAffected() {
		return customersAffected;
	}
	/**
	 * @param customersAffected the customersAffected to set
	 */
	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}
	/**
	 * @return the dateEventBegan
	 */
	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}
	/**
	 * @param dateEventBegan the dateEventBegan to set
	 */
	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}
	/**
	 * @return the dateEventFinished
	 */
	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}
	/**
	 * @param dateEventFinished the dateEventFinished to set
	 */
	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}
	/**
	 * @param id
	 * @param nerc
	 * @param customersAffected
	 * @param dateEventBegan
	 * @param dateEventFinished
	 */
	public Poweroutages(int id, Nerc nerc, int customersAffected, LocalDateTime dateEventBegan,
			LocalDateTime dateEventFinished) {
		super();
		this.id = id;
		this.nerc = nerc;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.durata=Duration.between(dateEventBegan, dateEventFinished).getSeconds();
	}
	
	/**
	 * @return the durata
	 */
	public long getDurata() {
		return durata;
	}
	/**
	 * @param durata the durata to set
	 */
	public void setDurata(long durata) {
		this.durata = durata;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Poweroutages other = (Poweroutages) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+nerc.getValue()+" "+this.customersAffected+" "+this.dateEventBegan+" "+this.dateEventFinished;
	}
	@Override
	public int compareTo(Object o) {
		Poweroutages p = (Poweroutages) o;
		return -(this.id-p.getId());
	}
	
	
	
	

}
