package com.book.service;
import java.net.*;

import javax.servlet.http.Part;

import java.io.*;

public class SimpleFTPClient
{

  /** The URL connection object */
  private URLConnection m_client;

  /** The FTP host/server to be connected */
  private String host = "orderbook.comlu.com" ;

  /** The FTP user */
  private String user = "a5388597";

  /** The FTP user’s password */
  private String password = "sunit12345";

  /** The remote file that needs to be uploaded or downloaded */
  private String remoteFile = "";

  /** The previous error message triggered after a method is called */
  private String erMesg = "Error Message";

  /** The previous success message after any method is called */
  private String succMesg = "Connection Succes!";
  public String uploadpath;

  public SimpleFTPClient()
  {
	  
  }

  /** Setter method for the FTP host/server */
//  public void setHost (String host)
//  {
//    this.host = host;
//  }
//
//  /** Setter method for the FTP user */
//  public void setUser (String user)
//  {
//    this.user = user;
//  }
//
//  /** Setter method for the FTP user’s password */
//  public void setPassword (String p)
//  {
//    this.password = p;
//  }
//

  public void setRemoteFile (String d)
  {
    this.remoteFile = d;
    System.out.println("filename:" + d);
  }
  /*method to uploadfile in server*/
  public synchronized boolean uploadFile (String localfilename, String fname)
  {
    try{
    	setRemoteFile(fname);
    	boolean chk = connect();
    	if(chk == true)
    	{
    		
    		System.out.println("uploading...");
    		//File file = new File(localfilename);
      InputStream is = new FileInputStream(localfilename);
      OutputStream os = m_client.getOutputStream();
      System.out.println("uploading...os" + os);
      //byte[] buffer = new byte[(int) file.length()];
      int i=0;

      while( (i = is.read()) !=-1)
      {
    	  
            os.write((byte)i);
            
      }
      
      os.close();
      is.close();

      this.succMesg = "Uploaded!";
      System.out.println("uploaded...");
      return true;
    	}
    	else
    		{
    		this.erMesg = "Connection unsuccesful";
    		System.out.println("uploadingfailed...");
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
  public synchronized boolean downloadFile (String localfilename)
  {
    try{
    	System.out.println("uploading...");
      InputStream is = m_client.getInputStream();
      BufferedInputStream bis = new BufferedInputStream(is);

      OutputStream os = new FileOutputStream(localfilename);
      BufferedOutputStream bos = new BufferedOutputStream(os);

      byte[] buffer = new byte[1024];
      int readCount;

      while( (readCount = bis.read(buffer)) > 0)
      {
        bos.write(buffer, 0, readCount);
      }
      bos.close();
      is.close (); // close the FTP inputstream
      this.succMesg = "Downloaded!";
      System.out.println("upload sucess...");
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
    
    uploadpath = url.getPath();
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
  

}