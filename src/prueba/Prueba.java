/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import hr.Country;
import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.Location;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //Clase Main
        Employee emp = new Employee();
        //Modificar Departamento
        HR hr = new HR();
        Employee e = new Employee();
        e.setEmployeeId(114);
        Location l = new Location();
        l.setLocationId(1700);
        Department d = new Department(30, "Compras", e, l);
        try {
            hr.modificarDepartment(30, d);
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }

        //Borrar Country
        Country c = new Country();
        try {
            hr.BorrarCountry("kk");
        } catch (ExcepcionHR ex) {
            System.out.println(ex);
        }

        //Insertar Empleado
        emp.setManager(e);
        d.setDepartmentId(80);
        String s = "2011-07-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(s);
        Job j = new Job();
        j.setJobId("IT_PROG");
        Employee emp1 = new Employee(207, "Adela", "VerdejA", "verdeja", "123.456.7890", date, j, 5000, 5, e, d);
        try {
            try {
                hr.insertarEmployee(emp1);
            } catch (ParseException ex) {
                System.out.println(ex);
            }
        } catch (ExcepcionHR ex) {
            System.out.println(ex);;
        }

    }

}
