package com.revature.beans;

public class Bike {

	private int id;
	private String name;
	private String color;
	private String brand;
	private String model;
	private String type;
	private String size;
	private String frame;
	private String material;
	private String wheelSet;
	private int onHand;
	
	public Bike() {
		id = 0;
		name = "";
		type = "";
		color = "sample color";
		brand = "sample brand";
		model = "sample model";
		size = "";
		frame = "";
		material = "";
		wheelSet = "";
			onHand = 0;
	};

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getWheelSet() {
		return wheelSet;
	}

	public void setWheelSet(String wheelSet) {
		this.wheelSet = wheelSet;
	}

	
	public void setName(String name) {
		
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {

		return color;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {

		return model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {

		return brand;
	}
	
	
	public int getOnHand() {
		return onHand;
	}


	public void setOnHand(int onHand) {
		this.onHand = onHand;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((frame == null) ? 0 : frame.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((wheelSet == null) ? 0 : wheelSet.hashCode());
		
		result = prime * result + onHand;
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
		
		Bike other = (Bike) obj;
		
		if (color == null) {
			if (other.color != null)
				return false;
			
		} else if (!color.equals(other.color))
			return false;
		
		if (name == null) {
			if (other.name != null)
				return false;
			
		} else if (!name.equals(other.name))
			return false;
		
		if (id != other.id)
			return false;
		
		if (id != other.onHand)
			return false;
		
		if (brand == null) {
			if (other.brand != null)
				return false;
			
		} else if (!brand.equals(other.brand))
			return false;
		
		if (model == null) {
			if (other.model != null)
				return false;
			
		} else if (!model.equals(other.model))
			return false;
		
		if (type == null) {
			if (other.type != null)
				return false;
			
		} else if (!type.equals(other.type))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
			
		} else if (!material.equals(other.material))
			return false;
		if (frame == null) {
			if (other.frame != null)
				return false;
			
		} else if (!frame.equals(other.frame))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
			
		} else if (!size.equals(other.size))
			return false;
		if (wheelSet == null) {
			if (other.wheelSet != null)
				return false;
			
		} else if (!wheelSet.equals(other.wheelSet))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Bike [id=" + id + ", Model=" + model 
				+ ", brand=" + brand + ", color=" + color
				+ ", name=" + name + ", type=" + type 
				+", material="+ material+", frame="+frame
				+", wheelset="+ wheelSet+ ", size="+size  
				+", onHand=" + onHand+ "]";
	}

	

	
	
}
