package com.book.service;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

import java.io.*;

public abstract class SimpleFTPClient extends HttpServlet
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/** The URL connection object */
  private URLConnection m_client;

  /** The FTP host/server to be connected */
  private String host = "" ;

  /** The FTP user */
  private String user;

  /** The FTP user’s password */
  private String password;

  /** The remote file that needs to be uploaded or download */
  private String remoteFile;
  private String addressPath ;//= "ftp://" + user + ":" + password + "@" + host + File.separator + remoteFile;
  public void setAddressPath(String a)
  {
	  this.addressPath = a;
  }
  /** The previous error message triggered after a method is called */
  private String erMesg;
  public String uploadpath;

  public SimpleFTPClient()
  {
	  	this.setHost("orderbook.comlu.com");
		this.setUser("a5388597");
		this.setPassword("sunit12345");
		this.setRemoteFile("public_html");
		this.setAddressPath("ftp://" + user + ":" + password + "@" + host + File.separator + remoteFile);
  }

  /** Setter method for the FTP host/server */
  public void setHost (String host)
  {
    this.host = host;
  }

  /** Setter method for the FTP user */
  public void setUser (String user)
  {
    this.user = user;
  }

  /** Setter method for the FTP user’s password */
  public void setPassword (String p)
  {
    this.password = p;
  }

  public void setRemoteFile (String d)
  {
    this.remoteFile = d;
  }
  /*method to upload file in server*/
  public synchronized boolean uploadFile (String localfilePath, String fname)
  {
    try{
    	setRemoteFile("public_html/book/"+ fname);
    	boolean chk = connect();
    	if(chk == true)
    	{
      InputStream is = new FileInputStream(localfilePath);
      OutputStream os = m_client.getOutputStream();
      System.out.println("uploading...os" + os);
      int i=0;
      while( (i = is.read()) !=-1)
      {
    	  
            os.write((byte)i);
            
      }      
      os.close();
      is.close();
      return true;
    	}
    	else
    		{
    		this.erMesg = "Connection unsuccesful";
    		return false;
    		}
    }
    catch(Exception ex)
    {
      StringWriter sw0= new StringWriter ();
      PrintWriter p0= new PrintWriter ( sw0, true );
      ex.printStackTrace ( p0 );
      erMesg = sw0.getBuffer().toString ();

      return false;
    }
  }

  /** The method to download a file and save it onto the local drive of the client in the specified absolut path
   @param localfilename – the local absolute file name that the file needs to be saved as */
  public synchronized boolean downloadFile (String localfilePath)
  {
    try{
      InputStream is = new FileInputStream("");
      OutputStream os = new FileOutputStream(localfilePath);
      int i=0;

      while( (i = is.read()) !=-1)
      {
    	  
            os.write((byte)i);
            
      }
      
      os.close();
      is.close ();
      return true;
    }catch(Exception ex)
    {
      StringWriter sw0= new StringWriter ();
      PrintWriter p0= new PrintWriter ( sw0, true );
      ex.printStackTrace ( p0 );
      erMesg = sw0.getBuffer().toString ();
      System.out.println("upload failed...");
      return false;
    }
  }

  /** The method that connects to the remote FTP server */
  public synchronized boolean connect()
  {
    try{
    	System.out.println("connecting...");
    URL url = new URL("ftp://" + user + ":" + password + "@" + host + File.separator + remoteFile);//"ftp://” + user + ":" + password + "@" + host + "/" + remoteFile
    m_client = url.openConnection();
    System.out.println("connection success...");
    return true;

    }
    catch(Exception ex)
    {
      StringWriter sw0= new StringWriter ();
      PrintWriter p0= new PrintWriter ( sw0, true );
      ex.printStackTrace ( p0 );
      erMesg = sw0.getBuffer().toString ();
      return false;
    }
  }
  /**Find list of items in server*/
public synchronized List<String> fileItem()
  {	  
	/*find list of files from server start*/
	  setRemoteFile("public_html/book;type=d");
	  List<String> filelist = new ArrayList<String>();
	  try{		
	    	boolean chk = connect();
	    	InputStream inputStream = m_client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            System.out.println("--- START ---");
            while ((line = reader.readLine()) != null) {
            	if(!line.startsWith("."))
            	{
            		File folder = new File(m_client.getURL().toExternalForm() +File.separator + line);
            		System.out.println(folder);
            		filelist.add(folder.toString());
                    System.out.println(folder.toString());
            	}
            	
            }
            System.out.println("--- END ---");
            inputStream.close();  	
	    	return filelist;
	  }catch(Exception ex)
	    {
	      StringWriter sw0= new StringWriter ();
	      PrintWriter p0= new PrintWriter ( sw0, true );
	      ex.printStackTrace ( p0 );
	      erMesg = sw0.getBuffer().toString ();
	      return filelist;
	    }
  }

}