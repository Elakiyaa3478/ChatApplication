
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


class MyServer
{
    ServerSocket ss;
    Socket s;
    BufferedReader brkey=new BufferedReader(new InputStreamReader(System.in));
    BufferedReader br;
    PrintWriter out;
    String msg,data;
    MyServer() 
    {
    	try {
        ss=new ServerSocket(9999);//start a server on port no 9999
        System.out.println("Listening on port 9999");
        s=ss.accept();//for the client to accept connection
        if(s!=null)
        {
            System.out.println("Connected with client on port 9999");
            //on the server receive connection from client
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
            out=new PrintWriter(s.getOutputStream(),true);
            readMessage();
            writeMessage();
           
          
        }
    	}
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    void readMessage() 
    {
    	Thread t=new Thread(()->{
    	 boolean check=true;
         while(check)
         {
             System.out.println("<<");
             try {
				msg=brkey.readLine();
				out.println(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              
             
            
         }
    	});
    	t.start(); 
    	}
    void writeMessage() {
    	Thread t=new Thread(()->{
    		boolean check=true;
    		while(check)
            {
            
                try {
					data=br.readLine();
					System.out.println("Client:"+data);
					  if(data.equalsIgnoreCase("bye"))
		                {
		                    check=false;
		                } 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
               
            }
    	});
    	t.start();
    	
    }
}
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       new MyServer();
	}

}
