package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class Instructor {

    private String idInstructor;
    private String nombre;
    private String descripcion;
    private String urlFoto;

    public Instructor(JSONObject jsonObject)
    {
        setIdInstructor(jsonObject.optString("idInstructor",""));
        setNombre(jsonObject.optString("nombre",""));
        setDescripcion(jsonObject.optString("descripcion",""));
        setUrlFoto(jsonObject.optString("urlFoto",""));
    }

    public String getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(String idInstructor) {
        this.idInstructor = idInstructor;
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

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
