package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;

@WebService()
public class HelloWorld {

    @WebMethod
    public String sayHelloWorldFrom(String from) {
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }


    @WebMethod
    public List<Account> createAccounts(List<UserData> users) {
        List<Account> accounts = new ArrayList<>();
        users.forEach( u ->{
            Account account = new Account();
            account.setUserdata(u);
            account.setActivated(false);
            account.setLogin(u.getName());
            account.getUserdata().appendToMap("1111", "1111");
            account.getUserdata().appendToMap("2222", "2222");
            account.getUserdata().appendToMap("3333", "3333");
            accounts.add(account);

        });
        return accounts;
    }


    public static void main(String[] argv) {
        Object implementor = new HelloWorld();
        String address = "http://localhost:8080/HelloWorld";
        Endpoint.publish(address, implementor);
    }
}
