import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public EmployeeDTO getSingleEmployeeByID(int id){
        EmployeeDTO employeesObject = new EmployeeDTO();
        try {
            PreparedStatement getSingleStatement = conn.prepareStatement("select * from emp where empno=?");
            getSingleStatement.setInt(1, id);
            ResultSet singleEmployee = getSingleStatement.executeQuery();
            while (singleEmployee.next()){
                employeesObject.setEmpno(singleEmployee.getInt("empno"));
                employeesObject.setEname(singleEmployee.getString("ename"));
                employeesObject.setJob(singleEmployee.getString("job"));
                employeesObject.setMgr(singleEmployee.getInt("mgr"));
                employeesObject.setHiredate(singleEmployee.getDate("hiredate"));
                employeesObject.setSal(singleEmployee.getInt("sal"));
                employeesObject.setComm(singleEmployee.getInt("comm"));
                employeesObject.setDeptno(singleEmployee.getInt("deptno"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesObject;
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> EmployeeListe = new ArrayList<>();
        try {
            PreparedStatement getStatement = conn.prepareStatement("select * from emp");
            ResultSet allEmployees = getStatement.executeQuery();
            while (allEmployees.next()){
                EmployeeListe.add(
                        new EmployeeDTO(
                            allEmployees.getInt("empno"),
                            allEmployees.getString("ename"),
                            allEmployees.getString("job"),
                            allEmployees.getInt("mgr"),
                            allEmployees.getDate("hiredate"),
                            allEmployees.getInt("sal"),
                            allEmployees.getInt("comm"),
                            allEmployees.getInt("deptno")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EmployeeListe;
    }

    public void setEmployee(EmployeeDTO emp) throws SQLException {
        String sql = "INSERT INTO emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, emp.getEmpno());
        statement.setString(2, emp.getEname());
        statement.setString(3, emp.getJob());
        statement.setInt(4, emp.getMgr());
        statement.setDate(5, emp.getHiredate());
        statement.setInt(6, emp.getSal());
        statement.setInt(7, emp.getComm());
        statement.setInt(8, emp.getDeptno());

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }
}
