package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class SpecBuilders {

    public static RequestSpecification req;
    public RequestSpecification reqSpec()  {
        try {
            if(req == null) {
                PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
                req = new RequestSpecBuilder().setBaseUri(getGlobal("baseUrl"))
                        .addQueryParam("key", "qaclick123")
                        //Request Log
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        //Response Log
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .setContentType(ContentType.JSON).build();
                return req;
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
        return req;
    }

    public static Properties prop;
    public static String getGlobal(String baseurlKey)  {
        try {
            prop =  new Properties();
            FileInputStream fis = new FileInputStream("C:\\APIFramework\\src\\test\\java\\global.properties");
            prop.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(baseurlKey);
    }

    public static String getJsonPath(Response response, String key){

        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.get(key).toString();

    }
}
