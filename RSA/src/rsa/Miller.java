package rsa;
 
import java.math.BigInteger;
import java.util.Random;
 
  
public class Miller {
     
    /** Creates a new instance of Miller */
    public Miller() {
    }
    public boolean Test(BigInteger n) {
        k=0;
        //   System.out.println("I am here");
        if(n.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0))==0) {
           // System.out.println("1");
            return false;
        }
        n_1=n.subtract(BigInteger.valueOf(1));
        System.out.println(n_1.longValue());
        while(n_1.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0))==0) {
            n_1=n_1.divide(BigInteger.valueOf(2));
            System.out.println(n_1.longValue());
            k++;
            if(k>30)return false;
        }
        q=n_1;
         
        n_1=n.subtract(BigInteger.valueOf(1));
         
        a=BigInteger.valueOf((int)(Math.random()*n_1.longValue()));
        System.out.println("a= "+a.longValue());
        int i=0;
        while(a.compareTo(BigInteger.valueOf(1))<=0||a.compareTo(n_1)>=0) {
            System.out.println("a= "+a.longValue());
            a=BigInteger.valueOf(Math.abs((int)(Math.random()*n_1.longValue())));
            i++;
            System.out.println("a= "+a.longValue());
            if(i>10)return false;
        }
         
        if(((BigInteger)(a.pow((int)q.longValue()))).mod(n).compareTo(BigInteger.valueOf(1))==0) {
            return true;
        }
        for (long j=0;j<k;j++) {
            System.out.println("j= "+j+" k="+k);
            if(((BigInteger)(a.pow((int)Math.pow(2,j)*(int)q.longValue()))).mod(n).compareTo(n_1)==0) {
                return true;
            }
             
        }
        return false;
    }
    long k;
    BigInteger a;
    Random rand=new Random();
    BigInteger q;
    BigInteger n_1;
    class  myThread extends Thread {
        BigInteger n;
        public myThread(BigInteger newn) {
            n=newn;
        }
        public void run() {
             
            Test(n);
        }
         
    }
}
