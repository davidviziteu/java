public class lab1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1000000);
        n *= 3;
        String binaryString = "10101";
        n += Integer.parseInt(binaryString, 2);
        String hexaString = "FF";
        n += Integer.parseInt(hexaString, 16);
        n *= 6;
        int result = n;
        while (result != result % 10) {
            n = result;
            result = 0;
            while (n != 0) {
                result += n % 10;
                n /= 10;
            }
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}
