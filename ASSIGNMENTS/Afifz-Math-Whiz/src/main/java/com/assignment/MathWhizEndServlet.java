package com.assignment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/math-whiz/end")
public class MathWhizEndServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("whizPlayer")==null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz");return;
        }
        Integer score=(Integer)session.getAttribute("whizScore");
        if(score==null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz/game");return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.println(MathWhizGameServlet.header("Result"));
        out.println("<h2>AFIFZ - MATH WHIZ</h2><hr><h3>Final Result</h3>");
        out.println("<p>Player: <b>"+MathWhizGameServlet.escape((String)session.getAttribute("whizPlayer"))+"</b></p>");
        out.println("<p>Difficulty: NORMAL</p><h2>Final Score: "+score+" / 10</h2>");
        out.println("<p><a href='"+req.getContextPath()+"/math-whiz/leaderboard'>View Top 10 Leaderboard</a></p>");
        out.println("<p><a href='"+req.getContextPath()+"/math-whiz'>Play Again</a></p>");
        out.println(MathWhizGameServlet.footer());
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        // Four questions must be submitted before a final result exists.
        doGet(req,resp);
    }
}
