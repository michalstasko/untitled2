package ws1.ws;

/**
 * Created by epic on 26.4.2014.
 */
public class HelloWorldClient{

    public static void main(String[] args) {

        HelloWorldImplService helloService = new HelloWorldImplService();
        HelloWorld hello = helloService.getHelloWorldImplPort();

        System.out.println(hello.getHelloWorldAsString("james bond"));

    }

}