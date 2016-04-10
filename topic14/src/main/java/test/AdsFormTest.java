package test;

import com.courses.spalah.AdsForm;
import com.courses.spalah.ServletConstants;
import db.Car;
import db.CarAdDao;
import db.CarSearchParameter;
import db.UsedCarsTradeDB;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Dima on 10.04.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AdsFormTest {
    static AdsForm adsForm;

    @Mock
    private HttpServletRequest httpServRequestMock;

    @BeforeClass
    public static void initialize(){
        adsForm = new AdsForm();
    }

    @Before
    public void before(){
        httpServRequestMock = Mockito.mock(HttpServletRequest.class);
    }

    @Test
    public void testFormCarParameters(){
        CarSearchParameter[] carSearchParameters = new CarSearchParameter[ServletConstants.NUMBER_OF_SEARCH_CRITERIA];
        carSearchParameters[0] = new CarSearchParameter("Mazda");
        carSearchParameters[1] = new CarSearchParameter("ML");
        carSearchParameters[2] = new CarSearchParameter("1996");
        carSearchParameters[3] = new CarSearchParameter("2010");
        carSearchParameters[4] = new CarSearchParameter("100000");
        carSearchParameters[5] = new CarSearchParameter("250000");

        when(httpServRequestMock.getParameter("manufacturer")).thenReturn(carSearchParameters[0].toString());
        when(httpServRequestMock.getParameter("modelName")).thenReturn(carSearchParameters[1].toString());
        when(httpServRequestMock.getParameter("yearFrom")).thenReturn(carSearchParameters[2].toString());
        when(httpServRequestMock.getParameter("yearTo")).thenReturn(carSearchParameters[3].toString());
        when(httpServRequestMock.getParameter("priceFrom")).thenReturn(carSearchParameters[4].toString());
        when(httpServRequestMock.getParameter("priceTo")).thenReturn(carSearchParameters[5].toString());

        CarSearchParameter[] carSearchParametersFromMethod = null;
        try {
            Method methodFormCarParameters =  AdsForm.class.getDeclaredMethod(METHOD_NAME.formCarParameters.toString(),
                    HttpServletRequest.class);
            methodFormCarParameters.setAccessible(true);
            carSearchParametersFromMethod = (CarSearchParameter[]) methodFormCarParameters.invoke(AdsForm.class,
                    httpServRequestMock);
        } catch (NoSuchMethodException e) {
            System.out.println("Method" + METHOD_NAME.formCarParameters.toString() + " wasn't found");
        } catch (IllegalAccessException e) {
            System.out.println("Method" + METHOD_NAME.formCarParameters.toString() + " is private");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.format(METHOD_NAME.formCarParameters.toString() + " failed: %s%n", cause.getMessage());
        }

        assertArrayEquals(carSearchParameters, carSearchParametersFromMethod);
        assertThat(not(carSearchParameters), is(not(carSearchParametersFromMethod)));
        verify(httpServRequestMock, times(6)).getParameter(any());
    }

    @Test
    public void testGetPostText(){
        String input = "{\n" +
                "\"manufacturer\": \"Mazda\",\n" +
                "\"modelName\": \"Mazda3\",\n" +
                "\"year\": 2011,\n" +
                "\"vin\": \"ABC123456\",\n" +
                "\"description\": \"Test\",\n" +
                "\"price\": 9999,\n" +
                "\"contact_phone\": \"+380-66-22-333\"\n" +
                "}";
            BufferedReader bufferedReaderMock = Mockito.mock(BufferedReader.class);
        try {
            when(bufferedReaderMock.readLine()).thenReturn("{\n").thenReturn("\"manufacturer\": \"Mazda\",\n").
                    thenReturn("\"modelName\": \"Mazda3\",\n").thenReturn("\"year\": 2011,\n").
                    thenReturn("\"vin\": \"ABC123456\",\n").thenReturn("\"description\": \"Test\",\n").
                    thenReturn("\"price\": 9999,\n").thenReturn("\"contact_phone\": \"+380-66-22-333\"\n").
                    thenReturn("}").thenReturn(null);
            when(httpServRequestMock.getReader()).thenReturn(bufferedReaderMock);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String resultString = null;
        try {
            Method methodGetPostText =  AdsForm.class.getDeclaredMethod(METHOD_NAME.getPostText.toString(),
                    HttpServletRequest.class);
            methodGetPostText.setAccessible(true);
            resultString = (String) methodGetPostText.invoke(AdsForm.class, httpServRequestMock);
        } catch (NoSuchMethodException e) {
            System.out.println("Method" + METHOD_NAME.getPostText.toString() + " wasn't found");
        } catch (IllegalAccessException e) {
            System.out.println("Method" + METHOD_NAME.getPostText.toString() + " is private");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.format(METHOD_NAME.getPostText.toString() + " failed: %s%n", cause.getMessage());
        }
        try {
            verify(bufferedReaderMock,times(10)).readLine();
            assertEquals(input,resultString);
            assertThat(not(input), is(not(resultString)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProduceCarObjectFromParameters(){
        Car car = new Car("5654654645HL", "Mersedes", "XL", 1500, "good car", 50000);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("vin",car.getVin());
        jsonMap.put("manufacturer",car.getManufacturer());
        jsonMap.put("modelName", car.getModel());
        jsonMap.put("releaseYear", car.getReleaseYear());
        jsonMap.put("information", car.getInformation());
        jsonMap.put("price", car.getPrice());

        Object carObject = new JSONObject(jsonMap);


        Car resultCar = null;
        try {
            Method methodProduceCarObjectFromParameters =  AdsForm.class.getDeclaredMethod
                    (METHOD_NAME.produceCarObjectFromParameters.toString(),
                    Object.class);
            methodProduceCarObjectFromParameters.setAccessible(true);
            resultCar = (Car) methodProduceCarObjectFromParameters.invoke(AdsForm.class, carObject);
        } catch (NoSuchMethodException e) {
            System.out.println("Method" + METHOD_NAME.produceCarObjectFromParameters.toString() + " wasn't found");
        } catch (IllegalAccessException e) {
            System.out.println("Method" + METHOD_NAME.produceCarObjectFromParameters.toString() + " is private");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.format(METHOD_NAME.produceCarObjectFromParameters.toString() + " failed: %s%n", cause.getMessage());
        }

        assertEquals(car, resultCar);
        assertThat(not(car),is(not(resultCar)));
    }

    @Test
    public void testFormJsonStringToPrint(){
        UsedCarsTradeDB.init();
        List<String> carAdsList;
        try {
            carAdsList = CarAdDao.getAllAds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //must be added

        carAdsList = new ArrayList<>();
        String resultMethodString = null;
        try {
            Method methodFormJsonStringToPrint =  AdsForm.class.getDeclaredMethod
                    (METHOD_NAME.formJsonStringToPrint.toString(), List.class);
            methodFormJsonStringToPrint.setAccessible(true);
            resultMethodString = (String) methodFormJsonStringToPrint.invoke(AdsForm.class, carAdsList);
        } catch (NoSuchMethodException e) {
            System.out.println("Method" + METHOD_NAME.formJsonStringToPrint.toString() + " wasn't found");
        } catch (IllegalAccessException e) {
            System.out.println("Method" + METHOD_NAME.formJsonStringToPrint.toString() + " is private");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.format(METHOD_NAME.formJsonStringToPrint.toString() + " failed: %s%n", cause.getMessage());
        }

        String noResultString = "<h1>" + "No results" + "</h1>";
        assertEquals(noResultString, resultMethodString);
    }

    @Test
    public void testDoGet(){
        UsedCarsTradeDB.init();

        HttpServletResponse httpServletResponseMock = Mockito.mock(HttpServletResponse.class);

        Enumeration enumeration = Mockito.mock(Enumeration.class);
        when (enumeration.hasMoreElements()).thenReturn(true);
        when(httpServRequestMock.getParameterNames()).thenReturn(enumeration);

        try {
            PrintWriter printWriter = Mockito.mock(PrintWriter.class);
            when(httpServletResponseMock.getWriter()).thenReturn(printWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReaderMock = Mockito.mock(BufferedReader.class);
        try {
            when(bufferedReaderMock.readLine()).thenReturn("{\n").thenReturn("\"manufacturer\": \"Mazda\",\n").
                    thenReturn("\"modelName\": \"Mazda3\",\n").thenReturn("\"year\": 2011,\n").
                    thenReturn("\"vin\": \"ABC123456\",\n").thenReturn("\"description\": \"Test\",\n").
                    thenReturn("\"price\": 9999,\n").thenReturn("\"contact_phone\": \"+380-66-22-333\"\n").
                    thenReturn("}").thenReturn(null);
            when(httpServRequestMock.getReader()).thenReturn(bufferedReaderMock);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Method methodDoGet =  AdsForm.class.getDeclaredMethod
                    (METHOD_NAME.doGet.toString(), HttpServletRequest.class, HttpServletResponse.class);
            methodDoGet.setAccessible(true);
            methodDoGet.invoke(new AdsForm(), httpServRequestMock, httpServletResponseMock);
        } catch (NoSuchMethodException e) {
            System.out.println("Method" + METHOD_NAME.doGet.toString() + " wasn't found");
        } catch (IllegalAccessException e) {
            System.out.println("Method" + METHOD_NAME.doGet.toString() + " is private");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            System.err.format(METHOD_NAME.doGet.toString() + " failed: %s%n", cause.getMessage());
        }

        try {
            verify(httpServletResponseMock,times(1)).getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
