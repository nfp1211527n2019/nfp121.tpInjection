package chainofresponsability;


public class HandlerNeutre extends Handler {

    public boolean handleRequest(int number) {

            System.out.println("HandlerNeutre : " + number);

        return super.handleRequest(number);
    }
}