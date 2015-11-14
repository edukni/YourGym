package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class InicioPromocion {

    private String idPromocion;
    private String tituloPromocion;
    private String urlFoto;

    public InicioPromocion(JSONObject jsonObject)
    {
        setIdPromocion(jsonObject.optString("idPromocion",""));
        setTituloPromocion(jsonObject.optString("tituloPromocion",""));
        setUrlFoto(jsonObject.optString("urlFoto",""));
    }

    public String getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(String idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getTituloPromocion() {
        return tituloPromocion;
    }

    public void setTituloPromocion(String tituloPromocion) {
        this.tituloPromocion = tituloPromocion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
