
//package de.webapp.Examples.HttpServer;


import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Server, which is capable of performing 
 * the <code>GET</code> command. Compared to {@link SimpleHttp}
 * some improvements are found. For instance, the error- and
 * status codes are set correctly. The Content-Type is set,
 * if a file <code>mime.types</code> is located in the current
 * directory. It should conform the following format:
 * <xmp>
 *	.ra=audio/x-realaudio
 *	.wav=audio/x-wav
 *	.gif=image/gif
 *	.jpeg=image/jpeg
 *	.jpg=image/jpeg
 *	.png=image/png
 *	.tiff=image/tiff
 *	.html=text/html
 *	.htm=text/html
 *	.txt=text/plain
 * </xmp>
 *
 * @author Hendrik Schreiber, Peter Rossbach
 * @version $Id: SimpleHttpd2.java,v 1.7 2000/07/15 12:23:39 Hendrik Exp $
 * @see OneShotHttpd
 * @see SimpleHttpd
 */
public class ServeurWeb8086 extends Thread 
{
   /**
    * Version
    */
   public static String vcid = "$Id: SimpleHttpd2.java,v 1.7 2000/07/15 12:23:39 Hendrik Exp $";

   /**
    * Socket of a request.
    */
   protected Socket s = null;

   /**
    * Document root.
    */
   protected static File docRoot;

   /**
    * Canonical document root.
    */
   protected static String canonicalDocRoot;

   /**
    * The port the server will listen to
    */
   public final static int HTTP_PORT = 8086;

   /**
    * CRLF
    */
   public final static String CRLF = "\r\n";

   /**
    * Protocol out server understands.
    */
   public final static String PROTOCOL = "HTTP/1.0 ";

   /**
    * Status code: All OK.
    */
   public final static String SC_OK = "200 OK";

   /**
    * Status code: Bad request.
    */
   public final static String SC_BAD_REQUEST = "400 Bad Request";

   /**
    * Status code: Forbidden request.
    */
   public final static String SC_FORBIDDEN = "403 Forbidden";

   /**
    * Status code: Resource not found.
    */
   public final static String SC_NOT_FOUND = "404 Not Found";

   /**
    * Content type map.
    */
   protected static Properties typeMap = new Properties();

   /**
    * Current status code.
    */
   protected String statusCode = SC_OK;

   /**
    * Current header.
    */
   protected Hashtable myHeaders = new Hashtable();

   /**
    * Waits for an <code>GET</code> request and performs it.
    * Objects are searched for relatively to the current directory
    * (Document root = "./"). If no file is specified, only
    * a directory, the file <code>index.html</code> is delivered.<br>
    * If the request is not a <code>GET</code> request, the error
    * message 400, <code>Bad Request</code>, is sent to the client.
    */
   public static void main(String argv[])
   {
      try
      {
         typeMap.load(new FileInputStream("mime.types"));
         docRoot = new File(".");
         canonicalDocRoot = docRoot.getCanonicalPath();
         ServerSocket listen = new ServerSocket(HTTP_PORT);
         System.out.println("serveur HTTP en " + listen.getInetAddress().getLocalHost().getHostAddress()  + ":8086 est en attente d'une requete...");
         while(true)
         {
            ServeurWeb8086 aRequest = new ServeurWeb8086(listen.accept());
         }
      }
      catch(IOException e)
      {
         System.err.println("Error: " + e.toString());
      }
   }

   /**
    * Sets the socket of this request and starts the thread.
    *
    * @param s Socket of a request
    */
   public ServeurWeb8086(Socket s)
   {
      this.s = s;
      start();
   }

   /**
    * The actually slogger of this class. The request is parsed
    * and the method {@link #getDocument()} is called
    */
   public void run()
   {
      try
      {
         setHeader("Server","ServeurWeb8086");
         BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
         DataOutputStream os = new DataOutputStream(s.getOutputStream());
         String request = is.readLine();
         System.out.println("Request: " + request);
         StringTokenizer st = new StringTokenizer(request);
         if((st.countTokens() == 3) && st.nextToken().equals("GET"))
         {
            String filename = docRoot.getPath() + st.nextToken();
            if(filename.endsWith("/") || filename.equals(""))
               filename += "index.html";
            File file = new File(filename);
            if(file.getCanonicalPath().startsWith(canonicalDocRoot))
               sendDocument(os,file);
            else
               sendError(SC_FORBIDDEN,os);
         }
         else
         {
            sendError(SC_BAD_REQUEST,os);
         }
         is.close();
         os.close();
         s.close();
      }
      catch(IOException ioe)
      {
         System.err.println("Error: " + ioe.toString());
      }
   }

   /**
    * Reads the file, specified in <code>request</code> and writes it to
    * the OutputStream.<br>
    * If the file could not be found, the error message 404,
    * <code>Not Found</code>, is returned.
    *
    * @exception IOException in case writing to the <code>DataOutputStream</code>
    *    fails.
    * @param os Stream, where the requested object is to be copied to.
    * @param file file to copy.
    */
   protected void sendDocument(DataOutputStream os, File file) throws IOException
   {
      try
      {
         BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
         sendStatusLine(os);
         setHeader("Content-Length",(new Long(file.length())).toString());
         setHeader("Content-Type",guessType(file.getPath()));
         sendHeader(os);
         os.writeBytes(CRLF);
         byte[] buf = new byte[1024];
         int len;
         while((len = in.read(buf,0,1024)) != -1)
         {
            os.write(buf,0,len);
         }
         in.close();
      }
      catch(FileNotFoundException fnfe)
      {
         sendError(SC_NOT_FOUND,os);
      }
   }

   /**
    * Sets a status code.
    *
    * @param statusCode status code
    */
   protected void setStatusCode(String statusCode)
   {
      this.statusCode = statusCode;
   }

   /**
    * Gets the status code.
    *
    * @return status code
    */
   protected String getStatusCode()
   {
      return statusCode;
   }

   /**
    * Writes the status line to the consigned <code>DataOutputStream</code>.
    *
    * @param out DataOutputStream where the line is to be written.
    * @exception IOException in case writing fails.
    */
   protected void sendStatusLine(DataOutputStream out) throws IOException
   {
      out.writeBytes(PROTOCOL + getStatusCode() + CRLF);
   }

   /**
    * Sets an header value.
    *
    * @param key key of the header value.
    * @param value the header value.
    */
   protected void setHeader(String key, String value)
   {
      myHeaders.put(key,value);
   }

   /**
    * Writes the header to the consigned <code>DataOutputStream</code>.
    *
    * @param out DataOutputStream where the header is to be written.
    * @exception IOException in case writing fails.
    */
   protected void sendHeader(DataOutputStream out) throws IOException
   {
      String line;
      String key;
      Enumeration e = myHeaders.keys();
      while(e.hasMoreElements())
      {
         key = (String)e.nextElement();
         out.writeBytes(key + ": " + myHeaders.get(key) + CRLF);
      }
   }

   /**
    * Writes an error message to the consigned <code>DataOutputStream</code>.
    *
    * @param out DataOutputStream where the error message is to be written.
    * @param statusCode status code.
    * @exception IOException in case writing fails.
    */
   protected void sendError(String statusCode, DataOutputStream out) throws IOException
   {
      setStatusCode(statusCode);
      sendStatusLine(out);
      out.writeBytes(CRLF + "<html>" + "<head><title>" + getStatusCode() + "</title></head>" + "<body><h1>" + getStatusCode() + "</h1></body>" + "</html>");
      System.err.println(getStatusCode());
   }

   /**
    * Surmise the <code>Content-Type</code> of the file by means
    * of the file extension.
    *
    * @param filename file name
    * @return Content-Type or "unknown/unknown" in case no
    * 	appropriate type is found.
    */
   public String guessType(String filename)
   {
      String type = null;
      int i = filename.lastIndexOf(".");
      if(i > 0)
         type = typeMap.getProperty(filename.substring(i));
      if(type == null)
         type = "unknown/unknown";
      return type;
   }

}

// end of class
