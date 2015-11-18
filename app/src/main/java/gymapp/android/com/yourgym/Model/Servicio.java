package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class Servicio {

    private String idServicio;
    private String nombreServicio;
    private String url;
    private String nombreServicioIng;

    public Servicio(JSONObject jsonObject)
    {
        setIdServicio(jsonObject.optString("idServicio", ""));
        setNombreServicio(jsonObject.optString("nombreServicio", ""));
        setUrl(jsonObject.optString("url", ""));
        setNombreServicioIng(jsonObject.optString("nombreServicioIng", ""));
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public String getNombreServicioIng() {
        return nombreServicioIng;
    }

    public void setNombreServicioIng(String nombreServicioIng) {
        this.nombreServicioIng = nombreServicioIng;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
