import java.io.*;
import java.net.*;

public class TCPServer {

  public static void main(String argv[]) throws Exception
  {

    InetAddress addr = InetAddress.getByName(argv[0]);
    try (ServerSocket listenSocket = new ServerSocket(Integer.parseInt(argv[1]), 50, addr)) {

      System.out.println("Listening on " + argv[0] + " port " + Integer.parseInt(argv[1]));
      while (true) {

        Socket connectionSocket = listenSocket.accept();
        System.out.println("Accepted...");

        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

        System.out.println("Reading...");
        Thread worker = new WorkerThread(inFromClient);
        worker.start();
      }
    }
  }

}


class WorkerThread extends Thread {
  BufferedReader inFromClient;

  public WorkerThread(BufferedReader in) {
    this.inFromClient = in;
    this.setDaemon(true);
  }

  public void run()
  {
    try {
      String line;
      while ((line = inFromClient.readLine()) != null) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}

