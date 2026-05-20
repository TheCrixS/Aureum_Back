package aureum.dto;

public class RegistroServicioDTO {

    private String nombre;
    private String descripcion;
    private String duracionMinutos;
    private String precio;

    public RegistroServicioDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(String duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
