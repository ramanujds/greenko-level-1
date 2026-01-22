package lambdas;

class Printer<T>{
    T value;

    T print(T data){
        return data;
    }
}

public class GenericExample {
    static void main() {
        Printer<Integer> p = new Printer<>();
        p.print(10);
        Printer<String> ps = new Printer<>();
        ps.print("Hello");
    }
}
