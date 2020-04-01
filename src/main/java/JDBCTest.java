import java.sql.*;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static java.util.Map.Entry.*;

public class JDBCTest {

    public static void main(String[]args) throws SQLException{

        DatabaseConnectionManager DCM = new DatabaseConnectionManager();

        Connection tilSQL = DCM.getConnection();

        Statement mitStatement = tilSQL.createStatement();

        String getAllDepartments = "select * from dept";

        ResultSet alledepart = mitStatement.executeQuery(getAllDepartments);

        ResultSet rs = DCM.getConnection().createStatement().executeQuery("select * fraom dept");

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

        System.out.println("insane sort!!!!!\n" + sorted2 +"\n\n");

        ArrayList<Employee> dl = new ArrayList<>();
        for (Integer i : banan.keySet()){
            dl.add(banan.get(i));
        }
        Collections.sort(dl, Comparator.comparing(Employee::getHiredate));
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
