public class Main {
    public static void main(String[] args) {
        SLLStack<Integer> myStack = new SLLStack<>();

        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        while(!myStack.empty()){
            myStack.peek();
            System.out.println(myStack.toString());
        }
        myStack.peek();
    }
}
