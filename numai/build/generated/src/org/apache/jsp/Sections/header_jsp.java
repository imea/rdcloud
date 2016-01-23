package org.apache.jsp.Sections;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.photopartage.tp.maximfluieraru.model.User;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<header>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"span12\">\r\n");
      out.write("                    <div class=\"head\">\r\n");
      out.write("                        <div class=\"row-fluid\">\r\n");
      out.write("                            <div class=\"span12\">\r\n");
      out.write("                                <div class=\"span6\">\r\n");
      out.write("                                    <h1 class=\"muted active\">photopartage.com</h1>\r\n");
      out.write("                                </div>\r\n");
      out.write("\r\n");
      out.write("                                <nav class=\"navbar\">\r\n");
      out.write("                                    <div class=\"container-fluid\">\r\n");
      out.write("                                        <ul class=\"navbar-form\">\r\n");
      out.write("\r\n");
      out.write("                                            <li class=\"btn pull-left alert-success\"><a href=\"./Acceuil?pageId=1\">Acceuil</a></li>\r\n");
      out.write("\r\n");
      out.write("                                            ");
 User user = (User) session.getAttribute("user");
                                                if (user == null) {
                                            
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                            <li class=\"btn pull-right alert-success\" ><a href=\"./sign_in?pageId=2\">Sign In</a></li>\r\n");
      out.write("                                            <li class=\"btn pull-right alert-success\" ><a href=\"./sign_up?pageId=3\">Sign Up</a></li>\r\n");
      out.write("\r\n");
      out.write("                                            ");
} else {
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                            <li class=\"btn pull-right alert-success\" ><a href=\"./log_out?pageId=5\">Log Out</a></li>\r\n");
      out.write("                                            <li class=\"btn pull-right alert-success\" ><a href=\"./user_area?pageId=4\">");
      out.print( user.getUser_first_name());
      out.write("</a></li>\r\n");
      out.write("\r\n");
      out.write("                                            ");
}
      out.write("\r\n");
      out.write("                                        </ul>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </nav>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <hr />\r\n");
      out.write("\r\n");
      out.write("</header>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
