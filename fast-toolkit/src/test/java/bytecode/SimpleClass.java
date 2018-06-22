package bytecode;

public class SimpleClass {

    public void main() {
        System.out.println("java file");
        new InnerClass().print();
    }

    class InnerClass {

        public void print() {
            System.out.println("inner class file");
        }
    }
}
