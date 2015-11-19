package gymapp.android.com.yourgym.Model;

import org.json.JSONObject;

/**
 * Created by Cesar on 05/11/2015.
 */
public class InicioConsejo {
    private String idConsejo;
    private String tituloConsejo;
    private String descripcion;
    private String urlFoto;
    private String tituloConsejoIng;
    private String descripcionIng;

    public InicioConsejo(JSONObject jsonObject)
    {
        idConsejo = jsonObject.optString("idConsejo","");
        tituloConsejo = jsonObject.optString("tituloConsejo","");
        descripcion = jsonObject.optString("descripcion","");
        urlFoto = jsonObject.optString("urlFoto","");
        tituloConsejoIng = jsonObject.optString("tituloConsejoIng","");
        descripcionIng = jsonObject.optString("descripcionIng","");
    }

    public String getTituloConsejoIng() {
        return tituloConsejoIng;
    }

    public void setTituloConsejoIng(String tituloConsejoIng) {
        this.tituloConsejoIng = tituloConsejoIng;
    }

    public String getDescripcionIng() {
        return descripcionIng;
    }

    public void setDescripcionIng(String descripcionIng) {
        this.descripcionIng = descripcionIng;
    }

    public String getIdConsejo() {
        return idConsejo;
    }

    public void setIdConsejo(String idConsejo) {
        this.idConsejo = idConsejo;
    }

    public String getTituloConsejo() {
        return tituloConsejo;
    }

    public void setTituloConsejo(String tituloConsejo) {
        this.tituloConsejo = tituloConsejo;
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
