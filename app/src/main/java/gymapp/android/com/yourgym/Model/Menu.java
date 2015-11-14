package gymapp.android.com.yourgym.Model;

import java.util.LinkedList;

/**
 * Created by Cesar on 08/11/2015.
 */
public class Menu {
    private String identificador;
    private String url;
    private LinkedList<String> lista = new LinkedList<>();

    public Menu(String identificador, String url)
    {
        this.setIdentificador(identificador);
        this.setUrl(url);
    }
    public Menu(String identificador, LinkedList<String> lista)
    {
        this.setIdentificador(identificador);
        this.lista = lista;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinkedList<String> getLista() {
        return lista;
    }

    public void setLista(LinkedList<String> lista) {
        this.lista = lista;
    }
}
