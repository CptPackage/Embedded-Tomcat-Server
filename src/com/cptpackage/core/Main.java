package com.cptpackage.core;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import org.apache.catalina.Context;
import org.apache.catalina.Globals;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.scan.StandardJarScanner;

public class Main {

	public static void main(String[] args) throws LifecycleException, IOException, URISyntaxException {
		// createServerEmbeddedByPath(getRootPath());
//		if(directoryExists()) {
			createServerEmbedded();			
//		}else {
//			System.out.println("Server can't startup without 'web-content' sub-directory!");
//		}
//		createServerEmbeddedWithXML();
	}

	@SuppressWarnings("unused")
	private static void tester() throws URISyntaxException, UnsupportedEncodingException {
		System.out.println("ALTERNATIVE:"
				+ URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8"));
	}

	private static String getRootPath() throws URISyntaxException, UnsupportedEncodingException {
		String rawPath = URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8");
		return String.valueOf(rawPath.subSequence(1, rawPath.length()));
	}
	
	private static boolean directoryExists() throws UnsupportedEncodingException, URISyntaxException {
		return new File(getRootPath() + "web-content").exists();
	}

	@SuppressWarnings("unused")
	private static void createServerEmbeddedByPath(String path) throws IOException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("./tmp");
		tomcat.setPort(4097);

		Context ctx = tomcat.addWebapp(tomcat.getHost(), "", path);

		((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);
		Tomcat.addServlet(ctx, "mainController", new Controller());
		ctx.addServletMapping("/hello", "mainController");
		ctx.addWelcomeFile("index.jsp");

		tomcat.start();

		tomcat.getServer().await();
	}

	private static void createServerEmbedded() throws IOException, LifecycleException, URISyntaxException {
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("./tmp");
		tomcat.setPort(8773);

//		String webappDirLocation = "web-content/";
//		String rootWebAppDirectoryPath = new File(webappDirLocation).getCanonicalPath();
//		Context ctx = tomcat.addWebapp(tomcat.getHost(), "", rootWebAppDirectoryPath);
		String webappDirLocation = getRootPath();

		System.out.println("ROOT PATH: " + webappDirLocation);

		Context ctx = tomcat.addWebapp(tomcat.getHost(), "", webappDirLocation + "web-content");

		((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);
		((StandardJarScanner) ctx.getJarScanner()).setScanClassPath(true);
		((StandardJarScanner) ctx.getJarScanner()).setScanBootstrapClassPath(true);
		((StandardJarScanner) ctx.getJarScanner()).setScanAllFiles(true);

		Tomcat.addServlet(ctx, "mainController", new Controller());
		ctx.addServletMapping("/hello", "mainController");
		ctx.addWelcomeFile("index.jsp");

		tomcat.start();

		tomcat.getServer().await();

	}

	@SuppressWarnings("unused")
	private static void createServerEmbeddedWithXML() throws IOException, LifecycleException {

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(".");
		tomcat.setPort(4080);

		String webappDirLocation = "web-content/";
		String rootWebAppDirectoryPath = new File(webappDirLocation).getCanonicalPath();
//		String webXmlFilePath = rootWebAppDirectoryPath + "/WEB-INF/web.xml";
		String xmlLocation = "C:/Users/CPTPackage/Desktop/final-built-project/web-content/WEB-INF/web.xml";
		Context ctx = tomcat.addWebapp(tomcat.getHost(), "", rootWebAppDirectoryPath);
		ctx.getServletContext().setAttribute(Globals.ALT_DD_ATTR, xmlLocation);
		((StandardJarScanner) ctx.getJarScanner()).setScanAllDirectories(true);

		tomcat.start();
		tomcat.getServer().await();
	}

}
