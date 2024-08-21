package kr.co.Zzac.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		// System.out.println("on start up");
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();

		servletAppContext.register(ServletAppContext.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");

		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);

		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);

		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
	}

	/*
	 * public class SpringConfigClass extends
	 * AbstractAnnotationConfigDispatcherServletInitializer{ // DispatcherServlet��
	 * ������ ��û �ּҸ� �����Ѵ�.
	 * 
	 * @Override protected String[] getServletMappings() { // TODO Auto-generated
	 * method stub return new String[] {"/"}; }
	 * 
	 * // Spring MVC ������Ʈ ������ ���� Ŭ������ �����Ѵ�.
	 * 
	 * @Override protected Class<?>[] getServletConfigClasses() { // TODO
	 * Auto-generated method stub return new Class[] {ServletAppContext.class}; }
	 * 
	 * // ������Ʈ���� ����� Bean���� ���Ǳ� ���� Ŭ������ �����Ѵ�.
	 * 
	 * @Override protected Class<?>[] getRootConfigClasses() { // TODO
	 * Auto-generated method stub return new Class[] {RootAppContext.class}; }
	 * 
	 * // �Ķ���� ���ڵ� ���� ����
	 * 
	 * @Override protected Filter[] getServletFilters() { // TODO Auto-generated
	 * method stub CharacterEncodingFilter encodingFilter = new
	 * CharacterEncodingFilter(); encodingFilter.setEncoding("UTF-8"); return new
	 * Filter[] {encodingFilter}; }
	 */
}
