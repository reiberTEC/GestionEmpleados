
package Logica;

public class Empleado {
    private String nombre;
    private boolean Status;
    private String area;
    private double salario;
    private String antiguedad;
    private String ingreso;

   

    public Empleado(String nombre, boolean Status, String area, double salario, String antiguedad, String ingreso) {
        this.nombre = nombre;
        this.Status = Status;
        this.area = area;
        
        this.salario = salario;
        this.antiguedad = antiguedad;
        this.ingreso = ingreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }
    
    @Override
    public String toString(){
           return "Empleado{" +
           "nombre='" + nombre + '\'' +
           ", apellido='" +'\'' +
           ", salario=" + salario +
           '}';};
    
}
