package net.webassembletool.aggregator;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.webassembletool.DriverFactory;

/**
 * Servlet used to proxy requests from a remote application.
 * 
 * @author Fran�ois-Xavier Bonnet
 * 
 */
public class AggregatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String provider;

    @Override
    protected void service(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String relUrl = request.getServletPath();
	if (request.getPathInfo() != null)
	    relUrl += request.getPathInfo();
	try {
	    DriverFactory.getInstance(provider).aggregate(relUrl, request,
		    response);
	} catch (AggregationSyntaxException e) {
	    throw new ServletException(e);
	}
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
	provider = config.getInitParameter("provider");
    }
}
