public class E10_02 {
    
    double[] temps=new double[365];

    public double hottest(){
        double hottest=temps[0];

        for(double day : temps){
            if(day>hottest){
                hottest=day;
            }
        }
        return hottest;
    }

    public double coldest(){
        double coldest=temps[0];

        for(double day : temps){
            if(day<coldest){
                coldest=day;
        }
    }
        return coldest;
    }

    public double mediumTemperature(int month){
        double sum=1;

        for(int i=1;i<month;i++){
            if(i==2){
                sum+=28;
            }else if(i%2==0){
                sum+=31;
            }else{
                sum+=30;
            }
        }
        return sum/month;
    }

    public double diffTemperature(int month){
        int sum=1;

        for(int i=1;i<month;i++){
            if(i==2){
                sum+=28;
            }else if(i%2==0){
                sum+=31;
            }else{
                sum+=30;
            }
        }
        return sum;
        
    }

    
}
