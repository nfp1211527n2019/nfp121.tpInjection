package chainofresponsability;



public abstract class Handler{

    private Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    

    public boolean handleRequest(int number) {

        if(successor != null) {
            return successor.handleRequest(number);
        }
        return false;
    }

}
