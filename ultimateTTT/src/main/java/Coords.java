public class Coords {
    int x, y;
    public Coords(int xc, int yc){
        x = xc;
        y = yc;
    }
    public Coords(String str){
        String temp = str.toLowerCase();
        String tempIntString = ""; //silly variable name
        for(int i = 0; i<10; i++){
            if(temp.indexOf('0'+i)>=0){
                tempIntString = temp.substring(temp.indexOf('0'+i));
                temp = temp.substring(0, temp.indexOf('0'+i));
            }
        }
        int sum = 0;
        for(int i = 0;temp.length()>0; i++){
            sum+=(int)Math.pow(26, i)*(temp.charAt(temp.length()-1)-'`');
            temp = temp.substring(0, temp.length()-1);
        }
        y = sum;
        if(tempIntString.length()>0)
            x = Integer.parseInt(tempIntString);
        else
            x = -1;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public static String intToString(int i){
        if(i<=0)
            return "";
        if(i%26==0)
            return intToString((i-26)/26)+'z';
        return intToString(i/26)+(char)(i%26+'`'); 
    }
    public String toString(){
        
        return intToString(y)+x;
    }
}