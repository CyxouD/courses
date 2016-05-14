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
            List carAds = null;
            if (req.getParameterNames().hasMoreElements()){  // check if has any parameter
                carAds = (List) Factory.getInstance().getCarAdDao().getSpecificAds(formCarParameters(req));
            }
            else {
                carAds = (List) Factory.getInstance().getCarAdDao().getAllCarAds();
            }

            String outputString = formJsonStringToPrint(carAds);
            resp.getWriter().println(outputString);
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

        User user = UserDao.createRandomUser();

        CarNew car = produceCarObjectFromParameters(obj);
        CarAd  carAd = produceCarAdObjectFromParameters(obj);

        boolean everythingWasOk = false;
        try {
            UserDao.addOld(user);
            car.setCarOwner(user.getIdInDB());
            Factory.getInstance().getCarDao().add(car);
            Factory.getInstance().getCarAdDao().add(carAd);
            everythingWasOk = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter writer = resp.getWriter();
        if (everythingWasOk) writer.println("ok");
        else writer.println("not ok");

    }

    private static CarNew produceCarObjectFromParameters(Object obj){
        JSONObject jsonObj = (JSONObject) obj;
        String vin = (String) jsonObj.get("vin");
        String manufacturer = (String) jsonObj.get("manufacturer");
        String model = (String) jsonObj.get("modelName");
        int releaseYear = (int) jsonObj.get("releaseYear");
        String information = (String) jsonObj.get("information");
        Double price = (double) jsonObj.get("price");
        CarNew car = new CarNew(vin,manufacturer,model,releaseYear,information);

        return car;
    }

    private static CarAd produceCarAdObjectFromParameters(Object obj){
        JSONObject jsonObj = (JSONObject) obj;
        String vin = (String) jsonObj.get("vin");
        Double price = (double) jsonObj.get("price");

        CarAd carAd = new CarAd(vin,price);
        return carAd;
    }

    private static String formJsonStringToPrint(List<String> carAds){
        int count = 0;
        JSONObject resultJson = new JSONObject();
        boolean noResult = true;
        StringBuilder sBuilderResult = new StringBuilder();
        for (Object s : carAds){
            resultJson.put(ServletConstants.TABLE_COLUMN_NAMES[count], s);
            count++;
            if (count == ServletConstants.NUMBER_OF_COLUMNS){
                sBuilderResult.append("<h1>" + resultJson.toJSONString() + "</h1>"+"\n");
                noResult = false;
                count = 0;
            }
        }
        if (noResult) sBuilderResult.append("<h1>" + "No results" + "</h1>");

        return sBuilderResult.toString();
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
