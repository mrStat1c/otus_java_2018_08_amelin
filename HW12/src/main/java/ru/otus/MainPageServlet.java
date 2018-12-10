package ru.otus;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainPageServlet  extends HttpServlet {

    private TemplateProcessor templateProcessor = new TemplateProcessor();
    private Map<String, String[]> requestParameters = new HashMap<>();

    public MainPageServlet() throws IOException {}


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        requestParameters = request.getParameterMap();
        if(requestParameters.containsKey("linkToAdminPage")){
            response.getWriter().println(templateProcessor.getPage("admin.html", new HashMap<>()));
        } else {
            response.getWriter().println(templateProcessor.getPage("index.html", new HashMap<>()));
        }
    }
}
