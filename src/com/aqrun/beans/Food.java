package com.aqrun.beans;

public class Food {
	private int id;
	private String name;
	//香辣 微辣 麻辣 不辣
	private int favor;
	private String thumb;
	private double price;
	private String description;
	
	public Food(int id, String name, int favor, String thumb, double price,
			String description) {
		this.id = id;
		this.name = name;
		this.favor = favor;
		this.thumb = thumb;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFavor() {
		return favor;
	}

	public void setFavor(int favor) {
		this.favor = favor;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toStirng() {
		return "[Food] id: " + id + "  name: " + name + "  favor: " + favor 
				+ "   thumb: " + thumb + "   price: " + price + "   description: "
				+ description;
	}
	
}
