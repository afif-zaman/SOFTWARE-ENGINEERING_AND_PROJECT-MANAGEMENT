package com.assignment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.math.*;
import java.util.*;

@WebServlet("/math-whiz/game")
public class MathWhizGameServlet extends HttpServlet {
    public static class Question implements Serializable {
        private static final long serialVersionUID = 1L;
        public final String operation, correctAnswer;
        public final double first;
        public final Double second;
        public final List<String> options;
        Question(String operation, double first, Double second, String correctAnswer, List<String> options) {
            this.operation=operation; this.first=first; this.second=second;
            this.correctAnswer=correctAnswer; this.options=new ArrayList<>(options);
        }
    }
    public static String escape(String s) {
        if(s==null)return "";
        return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                .replace("\"","&quot;").replace("'","&#39;");
    }
    public static String header(String title) {
        return "<!doctype html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1'>"
                +"<title>AFIFZ - MATH WHIZ - "+escape(title)+"</title><style>"
                +"body{font-family:Arial,sans-serif;background:white;color:black;margin:20px}"
                +"main{max-width:700px;margin:25px auto;border:2px solid black;padding:16px}"
                +"h2{text-align:center}fieldset{border:1px solid black;margin:14px 0;padding:12px}"
                +"input,select,button{font:inherit;padding:7px;max-width:100%;box-sizing:border-box}"
                +"button{cursor:pointer}label{display:inline-block;margin:5px}"
                +".error{border:1px solid black;padding:8px;color:darkred}"
                +"table{border-collapse:collapse;width:100%}th,td{border:1px solid black;padding:8px;text-align:left}"
                +"</style></head><body><main>";
    }
    public static String footer() { return "</main></body></html>"; }
    public static String format(double d) {
        if(Math.abs(d)<0.0000005)d=0;
        return BigDecimal.valueOf(d).setScale(6,RoundingMode.HALF_UP)
                .stripTrailingZeros().toPlainString();
    }
    private static boolean validOperation(int index,String op) {
        return index==0?"PRIME".equals(op):index==1?"MOD".equals(op)
                                           :Set.of("SIN","COS","TAN").contains(op);
    }
    private Question generate(int index,String op,String aText,String bText) {
        if(!validOperation(index,op))throw new IllegalArgumentException("Invalid operation.");
        double a=Double.parseDouble(aText);
        if(!Double.isFinite(a)||Math.abs(a)>1000000000)
            throw new IllegalArgumentException("Number out of range.");
        Double b=null;
        double result=0;
        String correct;
        switch(op) {
            case "PRIME" -> {
                if(a!=Math.rint(a)||a<Integer.MIN_VALUE||a>Integer.MAX_VALUE)
                    throw new IllegalArgumentException("Prime requires an integer.");
                correct=String.valueOf(MathWhizService.isPrime((int)a));
            }
            case "MOD" -> {
                b=Double.parseDouble(bText);
                if(!Double.isFinite(b)||a!=Math.rint(a)||b!=Math.rint(b)
                        ||a<Integer.MIN_VALUE||a>Integer.MAX_VALUE
                        ||b<Integer.MIN_VALUE||b>Integer.MAX_VALUE)
                    throw new IllegalArgumentException("MOD requires two integers.");
                result=MathWhizService.calculateMod((int)a,b.intValue());
                correct=format(result);
            }
            case "SIN" -> {result=MathWhizService.calculateSin(a);correct=format(result);}
            case "COS" -> {result=MathWhizService.calculateCos(a);correct=format(result);}
            case "TAN" -> {result=MathWhizService.calculateTan(a);correct=format(result);}
            default -> throw new IllegalArgumentException("Invalid operation.");
        }
        List<String> options=new ArrayList<>();
        if(op.equals("PRIME")) {options.add("1");options.add("0");}
        else {
            options.add(correct);
            Random random=new Random();
            int tries=0;
            while(options.size()<3 && tries++<100) {
                double offset=op.equals("MOD")?random.nextInt(19)-9
                        :(random.nextInt(19)-9)*Math.max(0.1,Math.abs(result)*0.2);
                if(offset==0)continue;
                String wrong=format(result+offset);
                if(!options.contains(wrong))options.add(wrong);
            }
            int n=1;
            while(options.size()<3) {
                String wrong=format(result+n++*(op.equals("MOD")?1:0.25));
                if(!options.contains(wrong))options.add(wrong);
            }
            Collections.shuffle(options);
        }
        return new Question(op,a,b,correct,options);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("whizPlayer")==null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz");return;
        }
        synchronized(session) {
            if(session.getAttribute("whizScore")!=null) {
                resp.sendRedirect(req.getContextPath()+"/math-whiz/end");return;
            }
            try {
                int i=Integer.parseInt(req.getParameter("questionIndex"));
                if(i<0||i>3)throw new IllegalArgumentException("Invalid question number.");
                Question[] questions=(Question[])session.getAttribute("whizQuestions");
                if(questions==null)questions=new Question[4];
                if(questions[i]==null) {
                    questions[i]=generate(i,req.getParameter("operation"),
                            req.getParameter("firstNumber"),req.getParameter("secondNumber"));
                    session.setAttribute("whizQuestions",questions);
                }
            } catch(IllegalArgumentException e) {
                session.setAttribute("whizError",e.getMessage()==null?"Invalid input.":e.getMessage());
            } catch(NullPointerException e) {
                session.setAttribute("whizError","Please enter the required numbers.");
            }
        }
        resp.sendRedirect(req.getContextPath()+"/math-whiz/game");
    }
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        HttpSession session=req.getSession(false);
        if(session==null||session.getAttribute("whizPlayer")==null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz");return;
        }
        if(session.getAttribute("whizScore")!=null) {
            resp.sendRedirect(req.getContextPath()+"/math-whiz/end");return;
        }
        Question[] questions=(Question[])session.getAttribute("whizQuestions");
        if(questions==null)questions=new Question[4];
        String error=(String)session.getAttribute("whizError");
        session.removeAttribute("whizError");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out=resp.getWriter();
        out.println(header("Game"));
        out.println("<h2>AFIFZ - MATH WHIZ</h2><hr><p>Player: <b>"+escape((String)session.getAttribute("whizPlayer"))
                +"</b> | Difficulty: <b>NORMAL</b> | Maximum Marks: <b>10</b></p>");
        out.println("<p>Enter values, click Generate Options, then choose an answer for each question.</p>");
        if(error!=null)out.println("<p class='error'>"+escape(error)+"</p>");
        String[] titles={"Q1 - Prime Number (2 Marks)","Q2 - Modulus (2 Marks)",
                "Q3 - Trigonometry (3 Marks)","Q4 - Trigonometry (3 Marks)"};
        boolean all=true;
        String path=req.getContextPath();
        for(int i=0;i<4;i++) {
            out.println("<fieldset><legend><b>"+titles[i]+"</b></legend>");
            Question q=questions[i];
            if(q==null) {
                all=false;
                out.println("<form method='post' action='"+path+"/math-whiz/game'>");
                out.println("<input type='hidden' name='questionIndex' value='"+i+"'>");
                if(i==0)out.println("<input type='hidden' name='operation' value='PRIME'>"
                        +"<label>Integer: <input type='number' name='firstNumber' step='1' required></label>");
                else if(i==1)out.println("<input type='hidden' name='operation' value='MOD'>"
                        +"<label>First integer: <input type='number' name='firstNumber' step='1' required></label>"
                        +"<label>Divisor: <input type='number' name='secondNumber' step='1' required></label>");
                else out.println("<label>Operation: <select name='operation'>"
                            +"<option>SIN</option><option>COS</option><option>TAN</option></select></label>"
                            +"<label>Angle (degrees): <input type='number' name='firstNumber' step='any' required></label>");
                out.println("<p><button type='submit'>Generate Options</button></p></form>");
            } else {
                String expression=q.operation.equals("PRIME")?"Is "+format(q.first)+" prime?"
                        :q.operation.equals("MOD")?format(q.first)+" MOD "+format(q.second)
                         :q.operation+"("+format(q.first)+" degrees)";
                out.println("<p><b>"+escape(expression)+"</b></p>");
                out.println("<label>Answer: <select name='answer"+i+"' form='answers' required>"
                        +"<option value=''>-- Choose --</option>");
                for(String option:q.options) {
                    String label=q.operation.equals("PRIME")
                            ?(option.equals("1")?"1 - Prime":"0 - Not Prime"):option;
                    out.println("<option value='"+escape(option)+"'>"+escape(label)+"</option>");
                }
                out.println("</select></label>");
            }
            out.println("</fieldset>");
        }
        if(all)out.println("<form id='answers' method='post' action='"+path
                +"/math-whiz/check'><button type='submit'>Submit All Answers</button></form>");
        else out.println("<p>Generate all four questions to enable Submit All Answers.</p>");
        out.println(footer());
    }
}
