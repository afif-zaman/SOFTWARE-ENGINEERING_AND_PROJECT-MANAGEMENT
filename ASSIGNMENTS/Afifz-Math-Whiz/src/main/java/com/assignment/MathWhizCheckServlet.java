package com.assignment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/math-whiz/check")
public class MathWhizCheckServlet extends HttpServlet {
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp)
            throws IOException,ServletException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("whizPlayer")==null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz");return;
        }
        synchronized(session) {
            if(session.getAttribute("whizScore")!=null) {
                resp.sendRedirect(req.getContextPath()+"/math-whiz/end");return;
            }
            MathWhizGameServlet.Question[] questions=(MathWhizGameServlet.Question[])
                    session.getAttribute("whizQuestions");
            if(questions==null||questions.length!=4) {
                resp.sendRedirect(req.getContextPath()+"/math-whiz/game");return;
            }
            int score=0;
            int[] marks={2,2,3,3};
            for(int i=0;i<4;i++) {
                if(questions[i]==null) {
                    resp.sendError(400,"Generate all four questions first.");return;
                }
                String answer=req.getParameter("answer"+i);
                if(answer==null||!questions[i].options.contains(answer)) {
                    resp.sendError(400,"Choose an answer for every question.");return;
                }
                if(answer.equals(questions[i].correctAnswer))score+=marks[i];
            }
            String player=(String)session.getAttribute("whizPlayer");
            try(Connection con=DatabaseConnection.getConnection();
                PreparedStatement ps=con.prepareStatement(
                        "INSERT INTO game_scores (player_name, score, difficulty) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,player);
                ps.setInt(2,score);
                ps.setString(3,"NORMAL");
                ps.executeUpdate();
                try(ResultSet keys=ps.getGeneratedKeys()) {
                    if(keys.next())session.setAttribute("whizGameId",keys.getInt(1));
                }
            } catch(SQLException e) {
                throw new ServletException("Could not save game score. Run the provided SQL first.",e);
            }
            session.setAttribute("whizScore",score);
            session.setAttribute("whizSaved",Boolean.TRUE);
        }
        resp.sendRedirect(req.getContextPath()+"/math-whiz/end");
    }
}
