package com.dipi.model;

public class DipiVO {
	private Integer dipi_id;
	private Integer dish_id;
	private String dipi_name;
	private String dipi_imgfmt;
	//private Byte[] dipi_img;
	private byte[] dipi_img;
	
	
	
	
	public byte[] toPrimitives(Byte[] oBytes)
	{
	    byte[] bytes = new byte[oBytes.length];

	    for(int i = 0; i < oBytes.length; i++) {
	        bytes[i] = oBytes[i];
	    }

	    return bytes;
	}
	
	 public Byte[] toObjects(byte[] bytesPrim) {
	    Byte[] bytes = new Byte[bytesPrim.length];

	    int i = 0;
	    for (byte b : bytesPrim) bytes[i++] = b; // Autoboxing

	    return bytes;
	}
	
	
	
	
	
	
	public DipiVO(){
		super();
	}
	
	
	
	public DipiVO(Integer dipi_id, Integer dish_id, String dipi_name, String dipi_imgfmt, byte[] dipi_img) {
		super();
		this.dipi_id = dipi_id;
		this.dish_id = dish_id;
		this.dipi_name = dipi_name;
		this.dipi_imgfmt = dipi_imgfmt;
		this.dipi_img = dipi_img;
	}

	public Integer getDipi_id() {
		return dipi_id;
	}

	public void setDipi_id(Integer dipi_id) {
		this.dipi_id = dipi_id;
	}

	public Integer getDish_id() {
		return dish_id;
	}

	public void setDish_id(Integer dish_id) {
		this.dish_id = dish_id;
	}

	public String getDipi_name() {
		return dipi_name;
	}

	public void setDipi_name(String dipi_name) {
		this.dipi_name = dipi_name;
	}

	public String getDipi_imgfmt() {
		return dipi_imgfmt;
	}

	public void setDipi_imgfmt(String dipi_imgfmt) {
		this.dipi_imgfmt = dipi_imgfmt;
	}

	public byte[] getDipi_img() {
		return dipi_img;
	}

	public void setDipi_img(byte[] dipi_img) {
		this.dipi_img = dipi_img;
	}
	
	
}
