package rsa;
import java.util.Scanner;
 
public class Extended_Eulidean {
     
    public int ExtendedGCD(int e,int GF){
        int m=e,b=GF,Q=0;
        int T1,T2,T3;
         
         
       RSAPanel.StepsText.append("You want to know MI for "+ m + " in " + "GF("+b+")");
         
        A1=1;
        A2=0;
        A3=b;
        B1=0;
        B2=1;
        B3=m;
        RSAPanel.StepsText.append("Q"+"  "+"A1"+"  "+"A2"+"  "+"A3"+"  "+"B1"+"  "+"B2"+"  "+"B3");
       RSAPanel.StepsText.append(Q+" | "+A1+" | "+A2+" | "+A3+" | "+B1+" | "+B2+" | "+B3);
        while(B3!=1){
             
            if(B3==0){
                System.out.println("No Multiplicative Inverse "+ A3);
            }
             
            Q=A3/B3;
             
            T1=(A1-Q*B1);
            T2=(A2-Q*B2);
            T3=(A3-Q*B3);
            A1=B1;
            A2=B2;
            A3=B3;
            B1=T1;
            B2=T2;
            B3=T3;
            RSAPanel.StepsText.append(Q+" | "+A1+" | "+A2+" | "+A3+" | "+B1+" | "+B2+" | "+B3);
        }
        if(B3==1){
            RSAPanel.StepsText.append("The Multiplicative Inverse "+ B2);
            int z=0;
            float y=0.0f;
            if(B2<0) {
               RSAPanel.StepsText.append("Negative");
                y=(float)B2/GF;
                z=(int)y;
                z--;
                z*=GF;
                B2-=z;
                //
                //    B2*=-1;
                //      B2=(B2%Q);
                //   B2 =( B2-(B2/GF)*GF);
            }
            return B2;
        }
        System.out.println("The Multiplicative Inverse error !!" );
        return 1;
    }
     
     
     
    private int A1=0;
    private int A2=0;
    private int A3=0;
    private int B1=0;
    private int B2=0;
    private int B3=0;
     
}
