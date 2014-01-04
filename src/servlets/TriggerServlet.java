package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import util.Constants;
import util.DbUtils;
import util.QuestionFile;

/**
 * This servlet is called when the user wants a new question.
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
