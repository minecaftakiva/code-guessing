import java.util.ArrayList;
public class cg86{
    public static void main(String[] args){
        ArrayList<Integer> sequence = recaman(50);
        for(int a : sequence){
            System.out.println(a);
        }
    }
    private static ArrayList<Integer> recaman(int n){
        if(n<0)
            throw new NegativeArraySizeException();
        ArrayList<Integer> sequence = new ArrayList<Integer>();
        if(n<1)
            return sequence;
        sequence.add(0);
        for(int i = 1; i<n; i++){
            int test = sequence.get(i-1)-i;
            if(test>0){
                boolean repeats = false;
                for(int a : sequence){
                    if(a==test){
                        repeats = true;
                        break;
                    }
                }
                if(!repeats)
                    sequence.add(test);
            }
            if(sequence.size()==i)
                sequence.add(sequence.get(i-1)+i);
        }
        return sequence;
    }
}