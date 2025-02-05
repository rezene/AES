 
package rsa;

import rsa.RSAPanel;
 
public class arrayprinter {
    
    /** Creates a new instance of arrayprinter */
    public arrayprinter() {
    }
    public static void printarray(String[][] arr,String label) {
        RSAPanel.StepsText.append("-- "+label+" -- "+'\n');
     
        for(int i=0;i<arr.length ;i++) {
            RSAPanel.StepsText.append("| ");
            for(int j=0;j<arr[0].length;j++) {
                
               RSAPanel.StepsText.append(arr[i][j]+" ");
            }
            RSAPanel.StepsText.append("| "+'\n');
        }
    }
}
 