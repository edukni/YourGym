package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class Dieta {
    private String idDieta;
    private String nombre;
    private String descripcion;
    private String urlFoto;

    public Dieta(JSONObject jsonObject)
    {
        idDieta = jsonObject.optString("idDieta","");
        nombre = jsonObject.optString("nombre","");
        descripcion = jsonObject.optString("descripcion","");
        urlFoto = jsonObject.optString("urlFoto","");
    }

    public String getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(String idDieta) {
        this.idDieta = idDieta;
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
