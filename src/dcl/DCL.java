package dcl;

public class DCL {
    private static final DCL instance = new DCL();

    private DCL(){}

    public static DCL getInstance(){
        return instance;
    }
}
