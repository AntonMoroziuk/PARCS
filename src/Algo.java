import parcs.*;
import java.util.*;

public class Algo implements AM{
    public long mul(long a, long b, long m){
      if(b==1)
        return a;
      if(b%2==0){
        long t = mul(a, b/2, m);
        return (2 * t) % m;
      }
      return (mul(a, b-1, m) + a) % m;
    }

    public long pows(long a, long b, long m){
      if(b==0)
        return 1;
      if(b%2==0){
        long t = pows(a, b/2, m);
        return mul(t , t, m) % m;
      }
      return ( mul(pows(a, b-1, m) , a, m)) % m;
    }

    public bool ferma(long x) {
      if(x == 2)
        return true;
      for(int i=0;i<100;i++){
        long a = ((Math.random() * (10000000))  % (x - 2)) + 2;
        if (gcd(a, x) != 1)
          return false;     
        if(pows(a, x-1, x) != 1)   
          return false;     
      }
      return true;
    }

    public long long gcd(long a, long b) {
      if(b==0)
        return a;
      return gcd(b, a%b);
    }

    public void run(AMInfo info){
        long n = info.parent.readLong();
        long step = info.parent.readLong();

        if (step == 7) info.parent.write(true);
        else {
            point p1 = info.createPoint();
            channel c1 = p1.createChannel();

            p1.execute("Algo");
            c1.write(n);
            c1.write(step + 1);
            point p2 = info.createPoint();
            channel c2 = p2.createChannel();
            p2.execute("Algo");
            c2.write(n);
            c2.write(step + 1);
            Boolean res = ferma(n);
            Boolean res1 = (Boolean)c1.readObject();
            Boolean res2 = (Boolean)c2.readObject();
            if (!res1 || !res2 || !res)
                info.parent.write(false);
            else
                info.parent.write(true);
        }
    }
}
