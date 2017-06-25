package ru.skysoftlab.skylibs.web.rest;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.skysoftlab.skylibs.common.SimpleQualifierQualifier;
import ru.skysoftlab.skylibs.web.common.UniversalAction;

@WebServlet(name = "UniversalResource", urlPatterns = {"/universal"})
public class UniversalResource extends HttpServlet {

	private static final long serialVersionUID = 5415351503869127177L;

	private Logger LOG = LoggerFactory.getLogger(UniversalResource.class);
	
	private static final String QUALIFIER_PARAM = "qualifier";
	
	private static final String CONTENT_TYPE = "application/json";
	
	private static final String UTF8 = "UTF-8";
	
	@Inject @Any
	private Instance<UniversalAction> uaSource;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UniversalAction action = commonHandle(request, response);
		if (action != null) {
			action.doGet(request, response);
		}
	}	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UniversalAction action = commonHandle(request, response);
		if (action != null) {
			action.doPost(request, response);
		}						
	}
	
	private UniversalAction commonHandle(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(CONTENT_TYPE);
		response.setCharacterEncoding(UTF8);
		String qualifier = request.getParameter(QUALIFIER_PARAM);
		if (qualifier == null || qualifier.equals("")) {
			LOG.error("Parameter 'qualifier' not defined in %s HTTP Request", request.getMethod());
			return null;
		}
		UniversalAction action = getUniversalAction(qualifier);
		return action;
	}
	
	private UniversalAction getUniversalAction(final String qualifier) {
		
		Annotation ann = new SimpleQualifierQualifier() {
			
			private static final long serialVersionUID = 4853692006199949889L;
			
			@Override
			public String value() {
				return qualifier;
			}
			
		};
		
		try {
			return uaSource.select(ann).get();	
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
		
	}
	
}
