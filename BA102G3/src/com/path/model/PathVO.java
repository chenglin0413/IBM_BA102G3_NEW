package com.path.model;

import java.io.Serializable;

public class PathVO implements Serializable {
	
	private Integer path_id;
	private Integer path_term;
	private Double path_fromlon;
	private Double path_fromlat;
	private Double path_tolon;
	private Double path_tolat;	
	private String path_fromplace;
	private String path_toplace;
	private byte[] path_img;
	private String path_imgfmt;
	
	public PathVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PathVO(Integer path_id, Integer path_term, Double path_fromlon, Double path_fromlat, Double path_tolon,
			Double path_tolat, String path_fromplace, String path_toplace, byte[] path_img, String path_imgfmt) {
		super();
		this.path_id = path_id;
		this.path_term = path_term;
		this.path_fromlon = path_fromlon;
		this.path_fromlat = path_fromlat;
		this.path_tolon = path_tolon;
		this.path_tolat = path_tolat;
		this.path_fromplace = path_fromplace;
		this.path_toplace = path_toplace;
		this.path_img = path_img;
		this.path_imgfmt = path_imgfmt;
	}

	public Integer getPath_id() {
		return path_id;
	}

	public void setPath_id(Integer path_id) {
		this.path_id = path_id;
	}

	public Integer getPath_term() {
		return path_term;
	}

	public void setPath_term(Integer path_term) {
		this.path_term = path_term;
	}

	public Double getPath_fromlon() {
		return path_fromlon;
	}

	public void setPath_fromlon(Double path_fromlon) {
		this.path_fromlon = path_fromlon;
	}

	public Double getPath_fromlat() {
		return path_fromlat;
	}

	public void setPath_fromlat(Double path_fromlat) {
		this.path_fromlat = path_fromlat;
	}

	public Double getPath_tolon() {
		return path_tolon;
	}

	public void setPath_tolon(Double path_tolon) {
		this.path_tolon = path_tolon;
	}

	public Double getPath_tolat() {
		return path_tolat;
	}

	public void setPath_tolat(Double path_tolat) {
		this.path_tolat = path_tolat;
	}

	public String getPath_fromplace() {
		return path_fromplace;
	}

	public void setPath_fromplace(String path_fromplace) {
		this.path_fromplace = path_fromplace;
	}

	public String getPath_toplace() {
		return path_toplace;
	}

	public void setPath_toplace(String path_toplace) {
		this.path_toplace = path_toplace;
	}

	public byte[] getPath_img() {
		return path_img;
	}

	public void setPath_img(byte[] path_img) {
		this.path_img = path_img;
	}

	public String getPath_imgfmt() {
		return path_imgfmt;
	}

	public void setPath_imgfmt(String path_imgfmt) {
		this.path_imgfmt = path_imgfmt;
	}
	
}
