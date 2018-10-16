package bytecode;

public class SimpleClass {

    public void main() {
        System.out.println("java io");
        new InnerClass().print();
    }

    class InnerClass {

        public void print() {
            System.out.println("inner class io");
        }
    }
}
