package iedu.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class resposeSetChar
 */
public class resposeSetChar implements Filter {

    /**
     * Default constructor. 
     */
	
	private String encode="UTF-8";
	
    public resposeSetChar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
       try {
    	    
		response.setCharacterEncoding(encode);
		chain.doFilter(request, response);
       } catch(Exception ex) {
    	     ex.printStackTrace();    	   
       }
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	   encode = fConfig.getInitParameter("Encode");
		
	   if (encode == null) 
			 encode ="UTF-8";
	   
	}
	
}
