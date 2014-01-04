package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.Constants;

/**
 * This servlet provides a JSON array of triggers based on the trigger file
 * 
 * It was designed to limit the amount of AJAX calls needed to check triggers
 * allowing triggers to easily and quickly be checked. If the trigger file becomes
 * very large, it would be necessary to modify this servlet to support a parameter 
 * to be passed in and force the front-end application to make an AJAX call for each
 * new word.
 */
public class TriggerServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TriggerServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			JSONObject obj=new JSONObject();
			
			BufferedReader br = new BufferedReader(new FileReader(Constants.TRIGGER_FILE_LOCATION));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(",") != -1) {
					String[] parts = line.split(",");
					obj.put(parts[0], parts[1]);
				}
			}
			br.close();
			

			response.getWriter().write(obj.toString());
		    response.setContentType("application/json");
		    response.setHeader("Cache-Control", "no-cache");	
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ServletException(e);
		}

	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		process(request, response);
	}

}
