package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class Rutina {

    private String id_rutina;
    private String nombre;
    private String descripcion;
    private int estado;
    private String descripcionIng;
    private String nombreIng;

    public Rutina(JSONObject jsonObject)
    {
        setId_rutina(jsonObject.optString("id_rutina",""));
        setNombre(jsonObject.optString("nombre", ""));
        setDescripcion(jsonObject.optString("descripcion", ""));
        setEstado(jsonObject.optInt("estado", 0));
        setDescripcionIng(jsonObject.optString("descripcionIng", ""));
        setNombreIng(jsonObject.optString("nombreIng", ""));
    }

    public String getId_rutina() {
        return id_rutina;
    }

    public void setId_rutina(String id_rutina) {
        this.id_rutina = id_rutina;
    }

    public String getDescripcionIng() {
        return descripcionIng;
    }

    public void setDescripcionIng(String descripcionIng) {
        this.descripcionIng = descripcionIng;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreIng() {
        return nombreIng;
    }

    public void setNombreIng(String nombreIng) {
        this.nombreIng = nombreIng;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
