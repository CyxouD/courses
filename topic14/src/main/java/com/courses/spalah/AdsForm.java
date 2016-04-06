package com.courses.spalah;

import db.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

/**
 * Created by Dima on 04.04.2016.
 */
public class AdsForm extends HttpServlet {

    @Override
    public void init() throws ServletException {
        UsedCarsTradeDB.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> carAds;
            if (req.getParameterNames().hasMoreElements()){  // check if has any parameter
                carAds = CarAdDao.getSpecificAds(formCarParameters(req));
            }
            else {
                carAds = CarAdDao.getAllAds();
            }

            int count = 0;
            JSONObject resultJson = new JSONObject();
            PrintWriter out = resp.getWriter();
            boolean noResult = true;
            for (Object s : carAds){
                resultJson.put(ServletConstants.TABLE_COLUMN_NAMES[count], s);
                count++;
                if (count == ServletConstants.NUMBER_OF_COLUMNS){
                    out.println("<h1>" + resultJson.toJSONString() + "</h1>");
                    System.out.println(resultJson.toJSONString());
                    noResult = false;
                    count = 0;
                }
            }
            if (noResult) out.print("<h1>" + "No results" + "</h1>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(getPostText(req));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int phone = - new Random().nextInt()*10000;
        User user = new User("lol", "vasya", phone, new char[] {113});

        JSONObject jsonObj = (JSONObject) obj;
        String vin = (String) jsonObj.get("vin");
        String manufacturer = (String) jsonObj.get("manufacturer");
        String model = (String) jsonObj.get("modelName");
        Long releaseYear = (long) jsonObj.get("releaseYear");
        String information = (String) jsonObj.get("information");
        Long price = (long) jsonObj.get("price");
        Car car = new Car(vin,manufacturer,model,releaseYear.intValue(),information,price.doubleValue());

        boolean everythingWasOk = false;
        try {
            UserDao.add(user);
            CarDao.add(car,user.getIdInDB());
            CarAdDao.add(car);
            everythingWasOk = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter writer = resp.getWriter();
        if (everythingWasOk) writer.println("ok");
        else writer.println("not ok");

    }

    private static CarSearchParameter[] formCarParameters(HttpServletRequest req){
        CarSearchParameter[] carSearchParameters = new CarSearchParameter[ServletConstants.NUMBER_OF_SEARCH_CRITERIA];
        carSearchParameters[0] = new CarSearchParameter(req.getParameter("manufacturer"));
        carSearchParameters[1] = new CarSearchParameter(req.getParameter("modelName"));
        carSearchParameters[2] = new CarSearchParameter(req.getParameter("yearFrom"));
        carSearchParameters[3] = new CarSearchParameter(req.getParameter("yearTo"));
        carSearchParameters[4] = new CarSearchParameter(req.getParameter("priceFrom"));
        carSearchParameters[5] = new CarSearchParameter(req.getParameter("priceTo"));
        return carSearchParameters;
    }

    @Override
    public void destroy() {
        UsedCarsTradeDB.closeConnection();
    }

    private static String getPostText(HttpServletRequest req) throws IOException{
        String line = null;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = req.getReader();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }
}
