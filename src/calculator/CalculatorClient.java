package calculator;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    static Calculator stub;

    private CalculatorClient() {}

    public static void main(String[] args) {

        int test = (args.length < 1) ? 0 : Integer.parseInt(args[0]);
        int valueToPush = (args.length < 2) ? 0 : Integer.parseInt(args[1]);
        String operator = (args.length < 2) ? null : args[1];
        int millis = (args.length < 2) ? 0 : Integer.parseInt(args[1]);
        try {
            Registry registry = LocateRegistry.getRegistry();
            stub  = (Calculator) registry.lookup("Calculator");

            switch (test) {
                case 1:
                    simpleTestPush(valueToPush);
                case 2:
                    if (!stub.isEmpty()) {
                        simpleTestOperator(operator);
                    } else {
                        System.out.println("Stack is empty!");
                    }
                case 3:
                    if (!stub.isEmpty()) {
                        simpleTestPop();
                    } else {
                        System.out.println("Stack is empty!");
                    }
                case 4:
                    if (!stub.isEmpty()) {
                        simpleTestDelayPop(millis);
                    } else {
                        System.out.println("Stack is empty!");
                    }
                default:
                    stub.printStack();
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }

    static void simpleTestPush(int val) {
        try {
            stub.pushValue(val);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static void simpleTestOperator(String operator) {
        try {
            stub.pushOperation(operator);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static void simpleTestPop() {
        try {
            System.out.println(stub.pop());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static void simpleTestDelayPop(int millis) {
        try {
            System.out.println(stub.delayPop(millis));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
