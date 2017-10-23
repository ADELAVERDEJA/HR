/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class HR {

    public int insertarRegion(Region region) {
        return 0;
    }

    public int BorrarRegion(int regionId) {
        return 0;
    }

    public int modificarRegion(int regionId, Region region) {
        return 0;
    }

    public Region leerRegion(int regionId) {
        return null;
    }

    public ArrayList<Region> leerRegions() {
        return null;
    }

    public int insertarCountry(Country country) {
        return 0;
    }

    public int BorrarCountry(String countryId) throws ExcepcionHR {
        int registrosAfectados;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            String llamada = "call BORRARCOUNTRIES(?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setInt(1, 150);
            System.out.println("Llamada final: " + sentenciaLlamable.toString());
            sentenciaLlamable.executeUpdate();
            System.out.println("Procedimiento Almacenado realizado");
            registrosAfectados = sentenciaLlamable.executeUpdate(llamada);
            sentenciaLlamable.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
        return 0;
    }

    public ArrayList<Country> leerCountries() {
        return null;
    }

    public int insertarLocation(Location location) {
        return 0;
    }

    public int BorrarLocation(int locationId) {
        return 0;
    }

    public int modificarLocation(int locationId, Location location) {
        return 0;
    }

    public Location leerLocation(int locationId) {
        return null;
    }

    public ArrayList<Location> leerLocations() {
        return null;
    }

    public int insertarDepartment(Department departmentId) {
        return 0;
    }

    public int BorrarDepartment(int departmentId) {
        return 0;
    }

    public int modificarDepartment(int departmentId, Department department) throws ExcepcionHR {
        int registrosAfectados = 0;
        String dml = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
            dml = " update DEPARTMENTS set DEPARTMENT_ID = " + department.getDepartmentId() + " , "
                    + " DEPARTMENT_NAME = '" + department.getDepartmentName() + "' , "
                    + " MANAGER_ID = " + department.getManager().getEmployeeId() + " , "
                    + " LOCATION_ID = " + department.getLocation().getLocationId()
                    + " where DEPARTMENT_ID = " + departmentId;
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("La localidad elegida ya exuste");
                    break;
                case 1407:
                    excepcionHR.setMensajeErrorUsuario("El identificador y el nombre del departamento son obligatorios");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de departamento ya que tiene "
                            + "empleados asociados o datos historicos asociados");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El identificador del departamento ya existe");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw excepcionHR;

        }
        return registrosAfectados;
    }

    public Department leerDepartment(int departmentId) {
        return null;
    }

    public ArrayList<Department> leerDepartments() {
        return null;
    }

    public int insertarEmployee(Employee employee) throws ExcepcionHR, ParseException {
        int registrosAfectados = 0;
        String dml = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            Statement sentencia = conexion.createStatement();
            dml = "insert into EMPLOYEES values (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            String s = "2011-07-08";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(s);
            sentenciaPreparada.setInt(1, 207);
            sentenciaPreparada.setString(2, "Adela");
            sentenciaPreparada.setString(3, "Verdeja");
            sentenciaPreparada.setString(4, "VERDEJA");
            sentenciaPreparada.setString(5, "123.456.7890");
            //sentenciaPreparada.setDate(6, date);
            sentenciaPreparada.setString(7, "IT_PROG");
            sentenciaPreparada.setDouble(8, 2500);
            sentenciaPreparada.setDouble(9, 5);
            sentenciaPreparada.setInt(10, 100);
            sentenciaPreparada.setInt(11, 80);
            registrosAfectados = sentencia.executeUpdate(dml);
            sentencia.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            ExcepcionHR excepcionHR = new ExcepcionHR(ex.getErrorCode(), ex.getMessage(), null, dml);
            switch (ex.getErrorCode()) {
                case 2291:
                    excepcionHR.setMensajeErrorUsuario("La localidad elegida ya exuste");
                    break;
                case 1400:
                    excepcionHR.setMensajeErrorUsuario("Los siguientes campos son obligatorios:"
                            + "Email"
                            + "Fecha de contratacion"
                            + "El identificador del trabajo"
                            + "El apellido del empleado");
                    break;
                case 2292:
                    excepcionHR.setMensajeErrorUsuario("No se puede modificar el identificador de departamento ya que tiene "
                            + "empleados asociados o datos historicos asociados");
                    break;
                case 1:
                    excepcionHR.setMensajeErrorUsuario("El email ya existe");
                    break;

                case 2290:
                    excepcionHR.setMensajeErrorUsuario("El salario tiene que ser mayor que 0");
                    break;
                default:
                    excepcionHR.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }

            throw excepcionHR;

        }
        return registrosAfectados;
    }

    public int BorrarEmployee(int employeeId) {
        return 0;
    }

    public int modificarEmployee(int employeeId, Employee employee) {
        return 0;
    }

    public Employee leerEmployee(int employeeId) {
        return null;
    }

    public ArrayList<Employee> leerEmployees() {
        return null;
    }

    public int insertarJob(Job job) {
        return 0;
    }

    public int BorrarJob(String jobId) {
        return 0;
    }

    public int modificarJob(String jobId, Job job) {
        return 0;
    }

    public Job leerJob(String jobId) {
        Connection c =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            c = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            sql = "select job_title from jobs where job_id = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, "AD_PRES");
            rs = ps.executeQuery(sql);
            while(rs.next()){
                String job_title = rs.getString("JOB_TITLE");
            }
        } catch (SQLException ex) {
            Logger.getLogger(HR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Job> leerJobs() {
        
        return null;
    }

    public int insertarJobHistory(JobHistory jobHistory) {
        return 0;
    }

    public int BorrarJobHistory(int employeeId, Date startDate) {
        int registrosAfectados;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "kk");
            String llamada = "call BORRARJOB_HISTORY(?,?)";
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);
            sentenciaLlamable.setInt(1, 150);
            //sentenciaLlamable.setDate(2, x);
            System.out.println("Llamada final: " + sentenciaLlamable.toString());
            sentenciaLlamable.executeUpdate();
            System.out.println("Procedimiento Almacenado realizado");
            registrosAfectados = sentenciaLlamable.executeUpdate(llamada);
            sentenciaLlamable.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error - Clase no Encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getErrorCode() + " - " + ex.getMessage());
        }
        return 0;
    }

    public int modificarJobHistory(int employeeId, Date startDate, JobHistory jobHistory) {
        return 0;
    }

    public JobHistory leerJobHistory(int employeeId, Date startDate) {
        return null;
    }

    public ArrayList<JobHistory> leerJobHistories() {
        return null;
    }

}
