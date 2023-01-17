public Calc{
    
    static int calc01(int n){
         int count=1;
         int final1=0;
         while(count<=n){
             final1=final1+count;
             count++;
         }
         return final1;
     }
     
     static int calc02(int n){
         int count=5;
         int final1=0;
         while(count<=n){
             final1=final1+count;
             count=count+5;
         }
         return final1;
     }
     
     static long calc03(int n){
         long count=1;
         long final1=0;
         while(count<=n){
             final1=final1+(Math.pow(2,count)-1);
             count++;
         }
         return final1;
     }
     
     static double calc04(int n){
         double count=1;
         double final1=0;
         while(count<=n){
             final1=final1+(1/count);
             count++;
         }
         return final1;
     }
     
     static long calc05(int n){
         long count=1;
         long final1=1;
         while(count<=n){
             final1=final1*count;
             count++;
         }
         return final1;
         
     }
 }