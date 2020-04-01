import java.io.InputStream;
import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class DepartmentManager {
    private Set<Department> dpSet;

    public DepartmentManager(){
        dpSet = new HashSet<>();
        addDepartments();
    }

    public void addDepartments(){
        try{
            DatabaseConnectionManager DCM = new DatabaseConnectionManager();
            Connection tilSQL = DCM.getConnection();
            Statement mitStatement = tilSQL.createStatement();
            String getAllDepartments = "select * from dept";
            ResultSet allDepartments = mitStatement.executeQuery(getAllDepartments);
            while(allDepartments.next()){
                dpSet.add(new Department(
                        allDepartments.getInt("deptno"),
                        allDepartments.getString("dname"),
                        allDepartments.getString("loc")
                        )
                );
            }
        } catch (SQLException e){
        }
    }

    public Set<Department> getSetOfDepartments(){
        return dpSet;
    }

}
