package myLibs;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class GSonRequest<T> extends Request<T> {

    private Gson mGSon;
    private final Class<T> mClass;
    private final Listener<T> mListener;

    private Map<String, String> mParams;
    private Map<String, String> mAdditionalHeaders;

    public GSonRequest(int method
            , String url
            , Class<T> objectClass
            , Listener<T> listener
            , ErrorListener errorListener) {
        this(method, url, objectClass, listener, errorListener, null);
    }

    public GSonRequest(int method
            , String url
            , Class<T> objectClass
            , Listener<T> listener
            , ErrorListener errorListener,
                       GsonBuilder gSonBuilder) {
        super(method, url, errorListener);
        this.mClass = objectClass;
        this.mListener = listener;

        if(gSonBuilder == null) {
            gSonBuilder = new GsonBuilder();
        }

        mGSon = gSonBuilder.create();
        mAdditionalHeaders = new HashMap<String, String>();
        mParams = new HashMap<String, String>();
    }

    public void addHeaderField(String key, String value) {
        mAdditionalHeaders.put(key, value);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mAdditionalHeaders;
    }

    public void addParam(String key, String value) {
        mParams.put(key, value);
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
              T tem = mGSon.fromJson(json, mClass);
            return Response.success(tem,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}