public class QuadraticEquation2 {
    public static void quadraticEquation2(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phương trình có vô số nghiệm");
                } else {
                    System.out.println("Phương trình vô nghiệm");
                }
            } else {
                System.out.println("Phương trình có 1 nghiệm: " + (-c / b));
            }
        } else {
            double del = b * b - 4 * a * c;
            if (del < 0) {
                System.out.println("Phương trình vô nghiệm");
            } else if (del == 0) {
                System.out.println("Phương trình có 1 nghiệm kép: " + (-b / 2 * a));
            } else {
                System.out.println("Phương trình có 2 nghiệm : " + ((-b + Math.sqrt(del)) / 2 * a)
                        + " và " + ((-b - Math.sqrt(del)) / 2 * a));
            }
        }
    }

    public static void main(String[] args) {

    }
}
