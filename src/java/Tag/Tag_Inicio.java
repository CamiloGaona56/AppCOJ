package Tag;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_Inicio extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession sesion = pageContext.getSession();
        String Nombres = pageContext.getSession().getAttribute("Nombres").toString();
        JspWriter out = pageContext.getOut();

        try {
            out.print("<section class='section'>");
            out.print("<div class='section-header' style='justify-content: center;'>");
            out.print("<div class='text-center'><h1>Bienvenida a la aplicación<br/>" + Nombres + "</h1></div>");
            out.print("</div>");
            out.print("</section>");
        } catch (Exception ex) {
            Logger.getLogger(Tag_Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return super.doStartTag(); //To change body of generated methods, choose Tools | Templates.
    }
}
