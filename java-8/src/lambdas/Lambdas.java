package lambdas;

interface Printable{
    void print();
}

interface MathOperation{
    int getSquare(int num);
}


public class Lambdas {
    public static void main(String[] args) {
        Printable printable = () -> System.out.println("Greenko");

        MathOperation operation = n ->  n*n;
        operation.getSquare(10);

        printable.print();
    }
}
