package com.assignment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/math-whiz")
public class MathWhizStartServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(MathWhizGameServlet.header("Welcome"));
        out.println("<h2>AFIFZ - MATH WHIZ</h2><hr><h3>Welcome</h3>");
        out.println("<p>Four questions: Prime (2), Modulus (2), Trigonometry (3 + 3).</p>");
        out.println("<p>Difficulty: NORMAL | Maximum score: 10</p>");
        out.println("<form method='post' action='"+req.getContextPath()+"/math-whiz'>");
        out.println("<label>Player Name: <input name='playerName' maxlength='100' required></label>");
        out.println("<p><button type='submit'>Start Game</button></p></form>");
        out.println("<p><a href='"+req.getContextPath()+"/math-whiz/leaderboard'>Leaderboard</a></p>");
        out.println(MathWhizGameServlet.footer());
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("playerName");
        if (name == null || name.trim().isEmpty() || name.trim().length() > 100) {
            resp.sendError(400, "Enter a player name of 1 to 100 characters."); return;
        }
        HttpSession session = req.getSession();
        synchronized (session) {
            session.setAttribute("whizPlayer", name.trim());
            session.setAttribute("whizQuestions", new MathWhizGameServlet.Question[4]);
            session.removeAttribute("whizScore");
            session.removeAttribute("whizSaved");
            session.removeAttribute("whizError");
            session.removeAttribute("whizGameId");
            session.removeAttribute("whizQuestion");
            session.removeAttribute("whizFeedback");
        }
        resp.sendRedirect(req.getContextPath()+"/math-whiz/game");
    }
}
