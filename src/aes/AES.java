
package aes;
import AddRoundKey.AddRoundKey;
import AddRoundKey.KeyExpansion;
import Hexdecimal_converter.decimalhextable;
import Mixcolumns.invmixcolumns;
import Mixcolumns.mixcolumns;
import Shiftrow.invshiftrows;
import Shiftrow.shiftrows;
import SBox.invSBOX;

import substitution.substitute_bytes;
public class AES {
  
    
    
    public AES(String key,String PlainText, String mode) {
        this.plaintext=PlainText;
         if(keySize==128)
              nk=4;
         else if(keySize==192)
    nk=6;
          if(keySize==256)
    nk=8;
        mykey=key;
        if (mode.equals("enc"))
        {
            
        AesEncryption();
        }
        else if (mode.equals("dec"))
        {
           plaintext1=PlainText;
            AesDecryption();
        }
       // fillDataTable();
        
        
    }
    public String CheckLenPlain(String plain){
         if(plain.length()%blockSize!=0) {
            int len=blockSize-plain.length()%blockSize;
             
           for(int i=0; i<len; i++)
               plain=plain.concat("-");
             
        } else {
            return plain;
        }
        return plain;
         
    }
    public void AesEncryption()
    {
        int start=0;
        int end=blockSize;
      String PlainText;
         PlainText=CheckLenPlain(plaintext);
        System.out.println(PlainText);
        String temp;
        for(int f=0; f< PlainText.length()/16; f++){ 
            temp= PlainText.substring(start,end);
            start+=blockSize;
            end+=blockSize;
            fillDataTable(temp);
            ASE_Cipher_loop();
          
        } 
         
      
    }
    public void AesDecryption()
    {
        int start1=0;
        int end1=blockSize;
      //String PlainText;
         
        String temp;
        for(int f=0; f< plaintext1.length()/16; f++){ 
            temp=plaintext1.substring(start1,end1);
            start1+=blockSize;
            end1+=blockSize;
            fillDataTable(temp);
            ASE_Decipher_loop();
         
        } 
     
    }
    public void ASE_Cipher_loop() {
    
       counterEnc++;
        AESPanel.StepsText.append("************************CIPHER***********************************"+counterEnc+'\n');
        arrayprinter.printarray(state_plain,"Plain Text");
        keytable=keyhex.getHextable( mykey);
        
        arrayprinter.printarray(keytable,"Key Hex");
        keyexpan.keyExpansion(keytable);
        
        int roundEnd=0;
        ROUND=1;
        if (keySize==128)
        roundEnd=9;
        else if (keySize==192)
        roundEnd=11;
        else if (keySize==256)
        roundEnd=13;
        
        
        AESPanel.StepsText.append("****************************************************************"+'\n');
        arrayprinter.printarray(keyexpan.getkeyToRound(),"Round key Value");
        state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
        
        while(ROUND<=roundEnd) {
            arrayprinter.printarray(state_plain,"Start of Round");
            substitute_bytes();
            arrayprinter.printarray(state_plain,"After SubBytes");
            state_plain=shift.shiftrows(state_plain);
            arrayprinter.printarray(state_plain,"After shiftrows");
            state_plain=mixer.mixcolumns(state_plain);
            arrayprinter.printarray(state_plain,"Mix-columns");
            ROUND++;
            arrayprinter.printarray(keyexpan.getkeyToRound(),"Round key Value");
            state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
        }
        substitute_bytes();
        
        arrayprinter.printarray(state_plain,"After SubBytes");
        state_plain=shift.shiftrows(state_plain);
        arrayprinter.printarray(state_plain,"After shiftrows");
        ROUND++;
        state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
        AESPanel.StepsText.append("****************************************************************"+'\n');
        arrayprinter.printarray(state_plain,"Cipher Text");
        
        
        AESPanel.StepsText.append("**************************************************************"+'\n');
        //return plainhex.getString(state_plain);
         AESPanel.CipherText.append(plainhex.getString(state_plain));
    }
    public void ASE_Decipher_loop() {
        counterDec++;
        AESPanel.StepsText.append("************************DECIPHER***********************************"+counterDec+'\n');
       if (keySize==128)
         ROUND=11;
        else if (keySize==192)
         ROUND=13;
        else if (keySize==256)
         ROUND=15;
        keytable=keyhex.getHextable(mykey);
        keyexpan.keyExpansion(keytable);
        arrayprinter.printarray( keyexpan.getWords(),"Key expantintion");
        arrayprinter.printarray(state_plain,"Start of Round");
        arrayprinter.printarray(keyexpan.getkeyToRound(),"Round key Value");
        state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
        arrayprinter.printarray(state_plain,"After Add round key");
        ROUND--;
        while(ROUND>1) {
            
            
            state_plain=invshift.invshiftrows(state_plain);
            arrayprinter.printarray(state_plain,"After invshiftrows");
            
            inverssubstitute_bytes();
            arrayprinter.printarray(state_plain,"After SubBytes");
            arrayprinter.printarray(keyexpan.getkeyToRound(),"Round key Value");
            state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
            arrayprinter.printarray(state_plain,"After Add round key");
            state_plain=invmix.invmixcolumns(state_plain);
            arrayprinter.printarray(state_plain,"invMix-columns");
            ROUND--;
            
        }
        state_plain=invshift.invshiftrows(state_plain);
        arrayprinter.printarray(state_plain,"After invshiftrows");
        inverssubstitute_bytes();
        arrayprinter.printarray(state_plain,"After SubBytes");
        arrayprinter.printarray(keyexpan.getkeyToRound(),"Round key Value");
        state_plain=AddRoundkey.AddRoundKey(state_plain,keyexpan.getkeyToRound());
        
        arrayprinter.printarray(state_plain,"state plain");
        AESPanel.StepsText.append("*******************************************************************"+'\n');
        arrayprinter.printarray(state_plain,"Plain text");
        
        
        //return plainhex.getString(state_plain);
        AESPanel.DeCipherText.append(plainhex.getString(state_plain));
        
    }
    public void fillDataTable( String x) {
        state_plain=plainhex.getHextable(x);
       
    }
    public void substitute_bytes() {
        for(int i=0;i<4;i++) {
            
            for(int j=0;j<4;j++) {
                state_plain[i][j]=substit.getsubstitue_bytes(state_plain[i][j]);
                
            }
            
        }
        
    }
    public void inverssubstitute_bytes() {
        for(int i=0;i<4;i++) {
            
            for(int j=0;j<4;j++) {
                state_plain[i][j]=substit.invgetsubstitue_bytes(state_plain[i][j]);
                
            }
            
        }
        
    }
    
    
    public static int ROUND=1;
    private decimalhextable plainhex=new decimalhextable();
    private decimalhextable keyhex=new decimalhextable();
    private substitute_bytes substit=new substitute_bytes();
    private AddRoundKey AddRoundkey=new AddRoundKey();
    private KeyExpansion keyexpan=new KeyExpansion();
    private mixcolumns mixer=new mixcolumns();
    private shiftrows shift=new shiftrows();
    private invmixcolumns invmix=new invmixcolumns();
    private invshiftrows invshift=new invshiftrows();
    
    private invSBOX invsbox=new invSBOX();
    private String plaintext ;
    private String plaintext1;
    public static int keySize = 128;
    public static  int blockSize=keySize/8;
    public static int nk =4;
   
    private int counterEnc =0;
     private int counterDec=0;
    private String mykey;
    private String[][] state_plain=new String[4][4];
    private   String[][] keytable=new String[4][4];
    
    
    
}
 