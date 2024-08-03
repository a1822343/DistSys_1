package calculator;

import java.rmi.RemoteException;
import java.util.*;

public class CalculatorImplementation implements Calculator {
    public static Stack<Integer> stack;

    // push val to the top of the stack
    public void pushValue(int val) throws RemoteException {
        // only requires one operation
        stack.push(val);
    }


    public void pushOperation(String operator) throws RemoteException {
        // this is assumed, but just in case!
        if (isEmpty()) throw new RuntimeException("stack is empty!");

        // assign result the appropriate operation result
        // "enhanced switch expression" pretty cool
        int result = switch (operator) {
            case "min" -> getMin();
            case "max" -> getMax();
            case "lcm" -> getLcm();
            case "gcd" -> getGcd();
            default -> 0;
        };

        // push the result of the operation to the stack
        stack.push(result);
    }

    // return the minimum value in the stack
    private int getMin() {
        int min = stack.pop();
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            min = Math.min(temp, min);
        }
        return min;
    }

    // return the maximum value in the stack
    private int getMax() {
        int max = stack.pop();
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            max = Math.max(temp, max);
        }
        return max;
    }

    // smallest number they all naturally meet
    // 21, 49 is abs(21 * 49) / gcd(21, 49) = 1029 / 7 = 147
    // 2, 3, 4 would be 12
    private int getLcm() {
        // lcm of a stack with one element will be that element
        int lcm = stack.pop();

        while (!stack.isEmpty()) {
            int temp = stack.pop();

            // we can leave this as integer division, it will always cleanly divide
            lcm = Math.abs(lcm * temp) / calcGcd(lcm, temp);
        }

        // found it!
        return lcm;
    }



    private int getGcd() {
        return 1;
    }

    private int calcGcd(int x, int y) {
        // it will be at least 1
        int gcd = 1;

        // half the greater of the two

        return gcd;
    }

    public int pop() throws RemoteException {
        return stack.pop();
    }

    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return stack.pop();
    }

    public void printStack() throws RemoteException {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println();
    }
}
