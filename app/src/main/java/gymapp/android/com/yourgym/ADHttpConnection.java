package gymapp.android.com.yourgym;
  import android.os.AsyncTask;
        import android.util.Log;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URI;
        import java.net.URL;


public class ADHttpConnection extends AsyncTask<URI, Integer, String> {
    private OnConnectionFinished _listenerConenection;

    public ADHttpConnection(OnConnectionFinished listenerConnection){
        _listenerConenection = listenerConnection;
    }



    @Override
    protected String doInBackground(URI... params) {
        Log.e("URLConnection","-------------<<<<< "+params[0]);
        String json = null;
        if (params[0] != null){
            int status = 0;
            HttpURLConnection httpURLConnection = null;
            try {
                httpURLConnection = (HttpURLConnection) params[0].toURL().openConnection();
                httpURLConnection.setRequestProperty("Accept-Charset", "application/x-www-form-urlencoded; charset=utf-8");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                status = httpURLConnection.getResponseCode();
                if (status == 200){
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    StringBuilder stringBuilder = new StringBuilder();
                    String linea;
                    while ((linea = bufferedReader.readLine()) != null){
                        stringBuilder.append(linea + "\r\n");
                    }
                    bufferedReader.close();
                    json = stringBuilder.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
        }
        Log.e("URLConnection", "-------json------<<<<< " + json);
        return json;
    }

    protected void onPostExecute(String cadena){
        super.onPostExecute(cadena);
        if (_listenerConenection != null){
            _listenerConenection.onDownlandFinished(cadena);
        }
    }
}