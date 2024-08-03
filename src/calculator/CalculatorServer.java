package calculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

public class CalculatorServer extends CalculatorImplementation {
    public CalculatorServer() {}

    public static void main(String[] args) {
        try {
            CalculatorServer obj = new CalculatorServer();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);

            // static stack object from CalculatorImplementation
            stack = new Stack<Integer>();

            System.out.println("Server ready!");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }

}
