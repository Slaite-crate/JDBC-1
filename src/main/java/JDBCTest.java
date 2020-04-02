import java.sql.*;
import java.util.*;
import java.sql.Date;

import static java.util.stream.Collectors.toMap;
import static java.util.Map.Entry.*;

public class JDBCTest {

    public static void main(String[]args) throws SQLException {
        //jdbc1();
        jdbc2();

    }

    public static void jdbc2() throws SQLException {
        DatabaseConnectionManager loginInfo = new DatabaseConnectionManager();
        EmployeeDAO something = new EmployeeDAO(loginInfo.getConnection());
        System.out.println(something.getAllEmployees());
        System.out.println(something.getSingleEmployeeByID(7369));
        EmployeeDTO emp = new EmployeeDTO(1235, "john", "funnyman", 321, Date.valueOf("2000-11-11"), 23, 34, 10);
        something.setEmployee(emp);

        System.out.println(something.getAllEmployees());
    }

    public static void jdbc1() throws SQLException {

            DatabaseConnectionManager loginInfo = new DatabaseConnectionManager();

            Connection tilSQL = loginInfo.getConnection();

            Statement mitStatement = tilSQL.createStatement();

            String getAllDepartments = "select * from dept";

            ResultSet alledepart = mitStatement.executeQuery(getAllDepartments);

            ResultSet rs = loginInfo.getConnection().createStatement().executeQuery("select * from dept");

            ResultSetMetaData meta = alledepart.getMetaData();
            int columCount = meta.getColumnCount();

            for (int i = 1; i <= columCount; i++){
                System.out.print(meta.getColumnName(i) + " ");
            }

            System.out.println();

            while(alledepart.next()){
                System.out.println(alledepart.getInt("deptno") + " " + alledepart.getString("dname") + " " + alledepart.getString("loc"));

            }


            System.out.println("\n");


            Map<Integer, Employee> banan;
            Employee ep = new Employee();
            banan = ep.getAllEmployees();

            Map<Integer, Employee> sorted2 = banan
                    .entrySet()
                    .stream()
                    .sorted(comparingByValue())
                    .collect(
                            toMap(e -> e.getKey(),
                                    e -> e.getValue(),
                                    (e1, e2) -> e2,
                                    LinkedHashMap::new)
                    );

            System.out.println("\n" + banan + "\n");

            System.out.println("insane sort!!!!!\n" + sorted2 +"\n\n");

            ArrayList<Employee> dl = new ArrayList<Employee>(banan.values());
            Collections.sort(dl/*, Comparator.comparing(Employee::getHiredate)*/);
            System.out.println(dl);


            System.out.println("\n");


            DepartmentManager dp = new DepartmentManager();
            Set etSet = dp.getSetOfDepartments();
            Iterator<Department> iSet = etSet.iterator();
            while (iSet.hasNext()){
                System.out.println(iSet.next());
            }
    }
}
