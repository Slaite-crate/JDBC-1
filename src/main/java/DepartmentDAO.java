import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//data access object
public class DepartmentDAO {
    private Connection conn;

    public DepartmentDAO(Connection conn) {
        this.conn = conn;
    }

    public DepartmentDTO getSingleDepartmentByID(int id){
        //Department data transfer object
        DepartmentDTO departmentObject = new DepartmentDTO();
        try {
            PreparedStatement getSingleStatement = conn.prepareStatement("select * from dept where deptno=?");
            getSingleStatement.setInt(1,id);
            ResultSet singleDepartment = getSingleStatement.executeQuery();
            while (singleDepartment.next()){
                departmentObject.setDeptno(singleDepartment.getInt("deptno"));
                departmentObject.setDname(singleDepartment.getString("dname"));
                departmentObject.setLoc(singleDepartment.getString("loc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentObject;
    }
}
