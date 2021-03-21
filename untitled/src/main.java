import java.io.*;
import java.util.*;
import java.util.stream.*;

public class main {
    static boolean isChar(char a){
        if(a <= 'z' && a >= 'a')
            return true;
        if(a <= 'Z' && a >= 'A')
            return true;
        return false;
    }
    static boolean isNewline(char a){
        if(a == '\n')
            return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        // Reading input in the field input
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(System.in));
        String input = inputBuffer.lines().collect(Collectors.joining("\n"));

        String result = null;

        // This is a debug message
        String[] splitted = input.split(" ");
        Arrays.stream(splitted).forEach(w -> {
            if (w.charAt(0) == '\n') System.out.print("ok");
        });

        // Print result
        System.out.println(result);

    }

}
