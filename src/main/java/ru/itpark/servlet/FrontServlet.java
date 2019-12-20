package ru.itpark.servlet;

import ru.itpark.model.QueryModel;
import ru.itpark.repository.QueryRepository;
import ru.itpark.repository.QueryRepositorySqliteImpl;
import ru.itpark.service.FileService;
import ru.itpark.service.QueryService;
import ru.itpark.service.QueryServiceSimpleImpl;
import ru.itpark.service.QueryServiceThreadPoolImpl;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


public class FrontServlet extends HttpServlet {
    private QueryService service;
    private QueryServiceThreadPoolImpl serviceThread;
    private FileService fileService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().substring(req.getContextPath().length());
        if(url.equals("/")) {
            req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        } else {

            if (url.startsWith("/search")) {
                req.getRequestDispatcher("/WEB-INF/search.jsp").forward(req, resp);
                if (req.getMethod().equals("POST")) {
                    String action = req.getParameter("action");
                    if (action.equals("search")) {
                        req.setCharacterEncoding("UTF-8");
                        var name = req.getParameter("name");
                        serviceThread.search(name);
                    } else {
                        Part file = req.getPart("file");
                        fileService.writeFile(file);

                    }
                }
            }

            if (url.startsWith("/results")) {
                List<QueryModel> listSearch = service.getAll();
                req.setAttribute("listSearch", listSearch);
                req.getRequestDispatcher("/WEB-INF/results.jsp").forward(req, resp);
            }
        }

    }

    @Override
    public void init() {
        try {
            InitialContext context = new InitialContext();
            final DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/db");
            fileService = (FileService) context.lookup("java:/comp/env/bean/file-service");
            final QueryRepository repository = new QueryRepositorySqliteImpl(dataSource);
            service = new QueryServiceSimpleImpl(repository);
            serviceThread = new QueryServiceThreadPoolImpl(repository);
            service.init();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        service.destroy();
    }
}
