package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 03/11/2015.
 */
public class Ejercicio {
    private String idEjercicio;
    private String nombreEjercicio;
    private String urlFoto;

    public Ejercicio(JSONObject object)
    {
        this.setIdEjercicio(object.optString("idEjercicio",""));
        this.setNombreEjercicio(object.optString("nombreEjercicio",""));
        this.setUrlFoto(object.optString("urlFoto",""));
    }

    public Ejercicio()
    {

    }

    public String getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
