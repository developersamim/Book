package com.book.controller;
//package com.book.service;
import java.io.File;
import java.io.IOException;
//import java.net.URLConnection;
import com.book.service.SimpleFTPClient;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part; 



  
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB 
                 maxFileSize=1024*1024*50,          // 50 MB
                 maxRequestSize=1024*1024*100)      // 100 MB
public class FileUploadServlet extends SimpleFTPClient{
   
     
    private static final long serialVersionUID = 1L;
	/**
     * Directory where uploaded files will be saved, its relative to
     * the web application directory.
     */
    private static final String UPLOAD_DIR = "uploads";
    public  FileUploadServlet()
    {
    	super();
    	//ServerDetails da = new ServerDetails();
    	 
    }
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets absolute path of the web application
<<<<<<< HEAD
    	//SimpleFTPClient da;
=======
    	//SimpleFTPClient ftp = new SimpleFTPClient();
>>>>>>> origin/master
        String applicationPath = request.getServletContext().getRealPath("fileName");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }        
      String fileName = null;     
      boolean chk = false;
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            
            
            part.write(uploadFilePath + File.separator + fileName);
     
            	 chk = uploadFile(uploadFilePath + File.separator + fileName, fileName);
            
            
        }
   	
        if(chk == true)
        {
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/response.jsp").forward(
                request, response);
        }
        else
        {
        	request.setAttribute("message", fileName + " File upload failed!");
            getServletContext().getRequestDispatcher("/response.jsp").forward(
                    request, response);
        	
        }
        
    	
       
    }
  
    /**
     * Utility method to get file name from HTTP header content-disposition
     */
   
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}