package chainofresponsability;


public class HandlerA extends Handler {

    public boolean handleRequest(int number) {
        if(number % 2 == 0) {
            System.out.println("HandlerA : " + number + " : pair");
            return true;
        }
        return super.handleRequest(number);
    }
}