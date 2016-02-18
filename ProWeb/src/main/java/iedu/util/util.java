package iedu.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

public class util {
	
	   public static  String  GetSha512(String str) {

			return GetSha(str,"SHA-512");			   
	   }
	    
	   
	   public static String GetSha(String str,String shatype ) 
	   {
		   String SHA = ""; 

			try{

//				MessageDigest sh = MessageDigest.getInstance("SHA-512"); 
				MessageDigest sh = MessageDigest.getInstance(shatype);
				
				sh.update(str.getBytes()); 

				byte byteData[] = sh.digest();

				StringBuffer sb = new StringBuffer(); 

				for(int i = 0 ; i < byteData.length ; i++){

					sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

				}
				SHA = sb.toString();				

			}catch(NoSuchAlgorithmException e){

				e.printStackTrace(); 

				SHA = null; 

			}

			return SHA;

		   
		   
	   }
	   
	   
	   
	   public static boolean IsMyIp(String ip) throws SocketException
	   {

		   Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

	        while (nienum.hasMoreElements()) {

	            NetworkInterface ni = nienum.nextElement();

	            Enumeration<InetAddress> kk = ni.getInetAddresses();

	            while (kk.hasMoreElements()) {

	    			InetAddress inetAddress = (InetAddress) kk.nextElement();

	    			if (inetAddress.getHostAddress().equals(ip) )
	    				return true;	    				    		
	    		}

	        }

	        return false;
		   
	   }
	   
	   
	   public static void main(String[] args) throws SocketException{

		   /*
			Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

		        while (nienum.hasMoreElements()) {
		            NetworkInterface ni = nienum.nextElement();
		            Enumeration<InetAddress> kk = ni.getInetAddresses();		            

		            while (kk.hasMoreElements()) {

		    			InetAddress inetAddress = (InetAddress) kk.nextElement();

		    			System.out.println(inetAddress.getHostName()+" : "+inetAddress.getHostAddress());

		    		}

		        }
		        */
		        System.out.println("is my ip check=="+IsMyIp("192.168.56.18")); 
		}
	   
	   
   public static  String  GetSha256(String str) {
			
			return GetSha(str,"SHA-256");
							   
   }
}
