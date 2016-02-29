package com.rxn1d.courses;

public class RouletteField {
    private int number;
    private String color;

    
    
    public RouletteField(int number, String color) {
		this.number = number;
		this.color = color;
	}
    
    public String getColor() {
        return color;
    }


    public int getNumber() {
        return number;

    }

	public String toString(){
    	return number + "-" + color;
    }
	
	//override for object equality of HashMap
	
	public boolean equals(Object rouletteField){
		RouletteField rField = (RouletteField) rouletteField;
		return number == rField.getNumber();
		
	}
	
	public int hashCode(){
		return number;
	}

}
