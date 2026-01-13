import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class self {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("self.java");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
            System.out.println(sc.next());
    }
}
