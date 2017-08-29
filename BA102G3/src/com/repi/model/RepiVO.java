package com.repi.model;

public class RepiVO {
	private Integer repi_id;
	private Integer rest_id;
	private String repi_name;
	private String repi_imgfmt;
	//private Byte[] repi_img;
	private byte[] repi_img;
	
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
	
	 
	 
	public RepiVO() {
		super();
	}
	public RepiVO(Integer repi_id, Integer rest_id, String repi_name, String repi_imgfmt, byte[] repi_img) {
		super();
		this.repi_id = repi_id;
		this.rest_id = rest_id;
		this.repi_name = repi_name;
		this.repi_imgfmt = repi_imgfmt;
		this.repi_img = repi_img;
	}

	public Integer getRepi_id() {
		return repi_id;
	}

	public void setRepi_id(Integer repi_id) {
		this.repi_id = repi_id;
	}

	public Integer getRest_id() {
		return rest_id;
	}

	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}

	public String getRepi_name() {
		return repi_name;
	}

	public void setRepi_name(String repi_name) {
		this.repi_name = repi_name;
	}

	public String getRepi_imgfmt() {
		return repi_imgfmt;
	}

	public void setRepi_imgfmt(String repi_imgfmt) {
		this.repi_imgfmt = repi_imgfmt;
	}

	public byte[] getRepi_img() {
		return repi_img;
	}

	public void setRepi_img(byte[] repi_img) {
		this.repi_img = repi_img;
	}
	
	
}
