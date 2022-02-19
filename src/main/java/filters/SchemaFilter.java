package filters;

import service.DbService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(initParams = @WebInitParam(name = "createSchema", value = "true"),
        urlPatterns = "/*")
public class SchemaFilter implements Filter {
    private DbService dbService = DbService.getInstance();

    private boolean createSchema;
    private boolean created;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        createSchema = Boolean.parseBoolean(filterConfig.getInitParameter("createSchema"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (createSchema && !created) {
            try {
                dbService.createSchema();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            created = true;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
