package ru.skysoftlab.skylibs.web.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UniversalAction extends Serializable {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response);

}
