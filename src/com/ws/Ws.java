package com.ws;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ws
 */
@WebServlet(urlPatterns = "/wsServlet", asyncSupported = true)
public class Ws extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ws() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		if (msg != "" && msg != null && !msg.equals("")) {
			AsyncContext ac = request.startAsync();
			ac.setTimeout(1 * 60 * 1000);

			response.setContentType("text/event-stream");
			response.setHeader("Cache-Control", "no-cache");
			final HttpServletResponse Threadresponse = response;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Threadresponse.getOutputStream().write(("data:" + msg + "\n\n").getBytes("UTF-8"));
						Threadresponse.getOutputStream().flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
