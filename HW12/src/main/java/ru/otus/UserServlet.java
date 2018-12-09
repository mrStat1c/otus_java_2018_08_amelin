package ru.otus;

import org.hibernate.cfg.Configuration;
import ru.otus.DataSets.UserDataSet;
import ru.otus.DbService.DbService;
import ru.otus.DbService.DbServiceImpl;
import ru.otus.DbService.DefaultConfigurationHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private Configuration configuration = DefaultConfigurationHolder.getConfiguration();
    private DbService dbService = new DbServiceImpl(configuration);

    private TemplateProcessor templateProcessor = new TemplateProcessor();
    private Map<String, String[]> requestParameters = new HashMap<>();

    public UserServlet() throws IOException {}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        requestParameters = request.getParameterMap();
        if (requestParameters.containsKey("name")
                && requestParameters.containsKey("age")){
            String userName = requestParameters.get("name")[0];
            String userAge = requestParameters.get("age")[0];
            if(!userName.isEmpty() && !userAge.isEmpty()){
                UserDataSet user = new UserDataSet();
                user.setName(userName);
                user.setAge(Integer.parseInt(userAge));
                dbService.save(user);
                setOK(response);//хз, нужно ли
                response.getWriter().println(getAdminPage(new HashMap<>()));
            } else {
                response.sendError(400, "Parameter Name or Age not filled!");
            }
        } else {
            response.sendError(400, "Parameter Name or Age not sended!");
        }

    }

    private String getAdminPage(Map<String, String> pageVariables) throws IOException {
        return templateProcessor.getPage("admin.html", pageVariables);
    }

    //здесь пока ничего не делалаось
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
