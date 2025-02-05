package rsa;
 
import java.util.Scanner;
 
  
public class GCD {
     
    /** Creates a new instance of GCD */
     
     
    public int get_GCD(int a,int b) {
        int remmender;
        while(b>0) {
            remmender=a%b;
            a=b;
            b=remmender;
        }
        return a;
    }  
}
