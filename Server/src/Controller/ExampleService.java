package Controller;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by Maciej on 2014-04-24.
 */
@WebService
public class ExampleService {
    @WebMethod
    public String Hello()
    {
        return "Witam";
    }
    /*public static void main(String[] argv) {
        Object implementor = new ExampleService ();
        String address = "http://localhost:8080/services/ExampleService";
        Endpoint.publish(address, implementor);
    }*/
}
