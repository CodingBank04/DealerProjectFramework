package utils;

import models.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DbUtil {
    public static List<Car> getAllCarsInDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://95.217.14.25:3306/dealerlot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&"
                    ,"testuser"
                    ,"Password@1");
            Statement statement = conn.createStatement();
            String sampleQuery = "SELECT * FROM cars";
            ResultSet rs = statement.executeQuery(sampleQuery);
            List<Car> carList = new ArrayList<>();
            while(rs.next()) {
                Car car = new Car(rs.getInt("year")
                        , rs.getInt("stock")
                        , rs.getString("make")
                        , rs.getString("model")
                        , rs.getString("color")
                        , rs.getString("image"));
                carList.add(car);
            }
            return carList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNumberOfCarsFromDb() {
        return getAllCarsInDb().size();
    }

    public static boolean doesStockExistInDb(int stockNumber) {
        List<Car> carList = getAllCarsInDb();
        for (Car car : carList) {
            if (car.stock == stockNumber) {
                return true;
            }
        }
        return false;
    }

    public static String getModelNameForCar(int stockNumber) {
        List<Car> carList = getAllCarsInDb();
        String modelNameInDb = "";
        for (Car car : carList) {
            if (car.stock == stockNumber) {
                modelNameInDb = car.model;
                break;
            }
        }
        return modelNameInDb;
    }
}
