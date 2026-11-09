package com.assignment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/math-whiz/leaderboard")
public class MathWhizLeaderboardServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp)
            throws IOException,ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.println(MathWhizGameServlet.header("Leaderboard"));
        out.println("<h2>AFIFZ - MATH WHIZ</h2><hr><h3>Top 10 Leaderboard</h3>");
        out.println("<table><tr><th>Rank</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Played At</th></tr>");
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "SELECT player_name,score,difficulty,played_at FROM game_scores "
                            +"ORDER BY score DESC,played_at ASC,id ASC LIMIT 10");
            ResultSet rs=ps.executeQuery()) {
            int rank=0;
            while(rs.next()) {
                out.println("<tr><td>"+(++rank)+"</td><td>"
                        +MathWhizGameServlet.escape(rs.getString("player_name"))+"</td><td>"
                        +rs.getInt("score")+" / 10</td><td>"
                        +MathWhizGameServlet.escape(rs.getString("difficulty"))+"</td><td>"
                        +MathWhizGameServlet.escape(rs.getString("played_at"))+"</td></tr>");
            }
            if(rank==0)out.println("<tr><td colspan='5'>No scores yet.</td></tr>");
        } catch(SQLException e) {
            throw new ServletException("Could not load leaderboard. Run the provided SQL first.",e);
        }
        out.println("</table><p><a href='"+req.getContextPath()+"/math-whiz'>Back to Welcome</a></p>");
        out.println(MathWhizGameServlet.footer());
    }
}
