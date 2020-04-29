package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.createList("ERCOT"));
		
		System.out.println(model.getAnalysis("WECC", 3, 5));
		
		

	}

}
