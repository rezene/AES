package rsa;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
 
  
public class MainEncry {
    private Random myrand=new Random();
    /** Creates a new instance of Main */
    public MainEncry(String Message) {
         
        int x=Integer.parseInt(Message);
        M=BigInteger.valueOf(x) ;
          p=(int)(Math.random()*M.longValue());
        while(primeMiller.Test(BigInteger.valueOf(p))==false||p<=1) {
             
            p=(int)(Math.random()*M.longValue());
            System.out.println("P= "+p);
        }
        q=(int)(Math.random()*M.longValue());
        while(primeMiller.Test(BigInteger.valueOf(q))==false||q<=1||p==q) {
            //System.out.println("Q= "+q);
            q=(int)(Math.random()*M.longValue());
        }
        GF=(p-1)*(q-1);
         
        e=(int)(myrand.nextInt(2000));
         
        while(myGCD.get_GCD(GF,e)!=1||e<=1) {
            e=(int)(myrand.nextInt(2000));
        }
        d=Ext_Eul.ExtendedGCD(e,GF);
        n=p*q;
       // System.out.println("n= "+n+" e= "+e+" d="+d+"  M="+M.longValue());
        C=(M.pow(e)).mod(BigInteger.valueOf(n));

       // System.out.println("C ="+C.longValue());
        MM=(C.pow(d)).mod(BigInteger.valueOf(n));
       // System.out.println("M ="+MM.longValue());
        // TODO code application logic here
    }
     
    /**
     * @param args the command line arguments
     */
    public String getC(){
        return C.toString();
         
    }
    public String getM(){
        return MM.toString();
    }
    private Miller primeMiller=new Miller();
    private Extended_Eulidean Ext_Eul=new Extended_Eulidean();
    private GCD myGCD=new GCD();
    private int p=17;
    private int q=11;
    private int GF;
    private int n;
    private int e;
    private int d;
    private BigInteger M;
    private BigInteger MM;
    private BigInteger C;
}


    
