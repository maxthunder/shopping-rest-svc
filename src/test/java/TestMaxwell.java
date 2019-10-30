import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import static java.util.Comparator.comparing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestMaxwell {

    private class Car {
        public void assemble() {
            System.out.println("Car");
        }
    }

    private class SportsCar extends Car {
        public void assemble() {
            System.out.println("Sports Car");
        }
    }

    @Test
    public void test() {
        Car car = new SportsCar();
        car.assemble();
    }

    @Test
    public void whenGetRequest_thenOk() throws IOException {
        URL url = new URL("http://localhost:8080/shopping/customers");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

//        Map<String, String> parameters = new HashMap<>();
//        parameters.put("param1", "val");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
//        ParameterStringBuilder psb = new ParameterStringBuilder();
//        out.writeBytes(psb.getParamsString(parameters));
        out.flush();
        out.close();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        System.out.println("content:\n" + content);

        assertEquals("status code incorrect", status, 200);
        assertTrue("content incorrect", content.toString()
                .contains("Example Domain"));
    }

    public class ParameterStringBuilder {
        public String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
        }
    }

    @Test
    public void upperBoundWildcard() {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        takesNumbers(numberList);
//        takesNumbers(integerList);// won't compile
        takesNumbersAndSubtypes(integerList);
    }

    private void takesNumbers(List<Number> numbers) {
        numbers.forEach(System.out::println);
    }

    private void takesNumbersAndSubtypes(List<? extends Number> numbers) {
        numbers.forEach(System.out::println);
    }

    @Test(expected = NoSuchElementException.class)
    public void optionalTests() {
        Optional<String> opt = Optional.empty();
        opt.get();
    }
}
