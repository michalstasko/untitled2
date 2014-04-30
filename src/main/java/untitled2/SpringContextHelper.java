package untitled2;

/**
 * Created by epic on 23.4.2014.
 */

import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import javax.servlet.ServletContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringContextHelper {

    private ApplicationContext context;

    public SpringContextHelper() {
        ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
    }

    public void autowire(Object bean) {
        context.getAutowireCapableBeanFactory().autowireBean(bean);
    }

    public Object getBean(final String beanRef) {
        return context.getBean(beanRef);
    }
}
