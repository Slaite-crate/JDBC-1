import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Employee implements Comparable<Employee> {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Integer sal;
    private Integer comm;
    private Integer deptno;

    public Employee(){
    }

    public Employee(Integer empno, String ename, String job, Integer mgr, Date hiredate, Integer sal, Integer comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
        this.deptno = deptno;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Map<Integer, Employee> getAllEmployees() {
        Map<Integer, Employee> resultat = new HashMap<>();
        try {
            DatabaseConnectionManager DCM = new DatabaseConnectionManager();
            Connection tilSQL = DCM.getConnection();
            Statement mitStatement = tilSQL.createStatement();
            String getAllEmployees = "select * from emp";
            ResultSet allEmployees = mitStatement.executeQuery(getAllEmployees);
            while(allEmployees.next()){
                resultat.put(
                        allEmployees.getInt("empno"), new Employee(
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
        } catch (SQLException e){
        }
        return resultat;
    }


    @Override
    public String toString(){
        return "\n" + hiredate + " " + ename + " " + job + " " + mgr + " " + empno + " " + sal + " " + comm +" " + deptno;
    }

    @Override
    public int compareTo(Employee o) {
        return this.hiredate.compareTo(o.hiredate);
    }
}

