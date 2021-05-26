package utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Shell {
    protected String prompt;
    protected List<String> tokens;

    public Shell() {
        Scanner scanner = new Scanner(System.in);
        prompt = scanner.nextLine();
    }

    public List<String> tokenizer() {
        StringTokenizer t = new StringTokenizer(this.prompt, " ");
        int i = 0;
        tokens = new LinkedList<String>();
        while (t.hasMoreTokens()) {
            tokens.add(t.nextToken(" "));
            i++;
        }
        return tokens;
    }
}
