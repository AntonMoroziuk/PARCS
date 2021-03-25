import java.io.*;
import parcs.*;
import java.util.*;

public class App implements AM {

    public static void main(String[] args) {
        task curtask = new task();
        curtask.addJarFile("App.jar");
        (new App()).run(new AMInfo(curtask, (channel)null));
        curtask.end();
    }

    

    public void run(AMInfo info) {
      long n;
      try {
          BufferedReader in = new BufferedReader(new FileReader(info.curtask.findFile("input.txt")));
          n = new Long(in.readLine()).longValue();
      } 
      catch (IOException e) {e.printStackTrace(); return;}

      point p1 = info.createPoint();
      channel c1 = p1.createChannel();
      p1.execute("Algo");
      c1.write(n);
      c1.write(0);
      c1.write(true);

      System.out.println("Waiting for result...");
      Boolean res = (Boolean)c1.readObject();
      System.out.println("Result found: " + (res ? "prime" : "not prime"));
    }
}
