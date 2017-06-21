package com.pervasive.entities;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PervasiveInventory implements java.io.Serializable {

	private String Itemkey;
	private String Itemdescription1;
	private String Itemdescription2;
	private String stockuom;
	

	public PervasiveInventory() {
	}

	public PervasiveInventory(String Itemkey) {
		this.Itemkey = Itemkey;
	}

	public PervasiveInventory(String Itemkey, String Itemdescription1, String Itemdescription2, String stockuom) {
		this.Itemkey = Itemkey;
		this.setItemdescription1(Itemdescription1);
		this.setItemdescription2(Itemdescription2);
		this.setStockuom(stockuom);
	}

	
	public String getItemkey() {
		return this.Itemkey;
	}

	public void setItemkey(String Itemkey) {
		this.Itemkey = Itemkey;
	}

	public String getItemdescription1() {
		return Itemdescription1;
	}

	public void setItemdescription1(String itemdescription1) {
		Itemdescription1 = itemdescription1;
	}

	public String getItemdescription2() {
		return Itemdescription2;
	}

	public void setItemdescription2(String itemdescription2) {
		Itemdescription2 = itemdescription2;
	}

	public String getStockuom() {
		return stockuom;
	}

	public void setStockuom(String stockuom) {
		this.stockuom = stockuom;
	}


}
