package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<Poweroutages> listaPower;
	private List<Poweroutages> bestSoluzione = null;
	private int bestNumPersone = 0;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	public Map<Integer,Nerc> createMap(){
		Map<Integer,Nerc> map = new TreeMap<>();
		for(Nerc n : this.getNercList()) {
			map.put(n.getId(), n);
		}
		return map;
	}
	public List<Poweroutages> getPoweroutages(){
		return podao.getPoweroutages(this.createMap());
	}
	public List<Poweroutages> createList(String nercValue){
		List<Poweroutages> l = new ArrayList<>();
		for(Poweroutages p : this.getPoweroutages()) {
			if(p.getNerc().getValue().compareTo(nercValue)==0) {
				l.add(p);
			}
		}
		return l;
	}

	/**
	 * metodo che chiama la ricorsione
	 * @param nercScelto
	 * @param years
	 * @param hours
	 * @return
	 */
	public List<Poweroutages> getAnalysis(String nercScelto, int years, int hours) {
		this.listaPower=this.createList(nercScelto);
		List<Poweroutages> parziale = new ArrayList<>();
		cerca(parziale,years,hours);
		return bestSoluzione;
	}

	/**
	 * ricorsione
	 * @param parziale
	 * @param years
	 * @param hours
	 */
	private void cerca(List<Poweroutages> parziale, int years, int hours) {
		if(parziale.size()!=0 && calcolaPersone(parziale)>this.bestNumPersone) {
			this.bestNumPersone=calcolaPersone(parziale);
			this.bestSoluzione= new ArrayList<>(parziale);
		}
		
		for(Poweroutages p : listaPower) {
			if(valida(parziale,p,years,hours)==true && !parziale.contains(p)) {
				parziale.add(p);
				cerca(parziale,years,hours);
				parziale.remove(parziale.size()-1);
			}
			
		}
		return;
		
	}

	private boolean valida(List<Poweroutages> parziale, Poweroutages p, int years, int hours) {
		if(parziale.size()<1)
			return true;
		Collections.sort(parziale);
		int anno1=parziale.get(0).getDateEventBegan().getYear();
		int anno2 =p.getDateEventBegan().getYear();
		int diff = anno2-anno1;
		long l = hours*3600;
		if(diff>years || calcolaOre(parziale,p)>l)
			return false;
		return true;
	}

	private long calcolaOre(List<Poweroutages> parziale, Poweroutages p) {
		long somma = 0;
		somma+= p.getDurata();
		for(Poweroutages pw : parziale) {
			//Duration d = Duration.between(pw.getDateEventBegan(), pw.getDateEventFinished());
			somma+=pw.getDurata();
		}
		
		return somma;
	}
	

	public int calcolaPersone(List<Poweroutages> parziale) {
		int somma = 0;
		for(Poweroutages p: parziale) {
			somma+=p.getCustomersAffected();
		}
		return somma;
	}

}
