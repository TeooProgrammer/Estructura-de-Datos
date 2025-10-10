class Main2{
    public static void main(String[] args) {
        // AVLTreeString avl= new AVLTreeString();
        // avl.insert("RIO");
        // avl.insert("Rio");
        // avl.insert("rio");
        // System.out.println("Esta 'rio': "+avl.search("rio"));
        // System.out.println("Esta 'RIO': "+avl.search("rio"));
        // System.out.println("Esta 'Rio': "+avl.search("rio"));
        // System.out.println("\nBorrando rio y Rio");
        // avl.delete("rio");
        // avl.delete("Rio");
        // System.out.println("Esta 'rio': "+avl.search("rio"));
        // System.out.println("Esta 'RIO': "+avl.search("RIO"));
        // System.out.println("Esta 'Rio': "+avl.search("Rio"));
        for (int i = 0; i < 10; i++) {
            System.out.print(fib(i) + " ");
        }
    }
    public static int fib(int n){
        if (n == 0 || n == 1) {
            return n;
        }
        else{
            return fib(n-2)+fib(n-1);
        }
    }
}