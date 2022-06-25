package org.nina.dsj.common.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class HttpUtil {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();

        client.dispatcher().setMaxRequestsPerHost(100);
        client.dispatcher().setMaxRequests(300);
    }


    public static String doGet(String url) throws Exception{
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> headerParams) throws Exception {
        try {
            Request.Builder requestBuilder = new Request.Builder();
            requestBuilder.url(url);
            if (headerParams != null && !headerParams.isEmpty()) {
                requestBuilder.headers(setHeaders(headerParams));
            }
            Request request = requestBuilder.build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                throw new RuntimeException(toExceptionInfo(url, "GET", e.getMessage(), null, null));
            } else {
                throw e;
            }
        }
    }

    public static String doPostJSON(String url, String json) throws Exception{
        return doPostJSON(url, json, null);
    }

    public static String doPostJSON(String url, String json, Map<String, String> headerParams) throws Exception {
        try {
            RequestBody body = RequestBody.create(JSON, json);
            Request.Builder requestBuilder = new Request.Builder();
            requestBuilder.url(url).post(body);
            if (headerParams != null && !headerParams.isEmpty()) {
                requestBuilder.headers(setHeaders(headerParams));
            }
            Request request = requestBuilder.build();
            return doPost(request);
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                throw new RuntimeException(toExceptionInfo(url, "POST", e.getMessage(), json, null));
            } else {
                throw e;
            }
        }
    }

    public static String doPostForm(String url, Map<String, String> params) throws Exception{
        return doPostForm(url, params, null);
    }

    public static String doPostForm(String url, Map<String, String> params, Map<String, String> headerParams) throws Exception {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if(params !=null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue());
                }
            }
            FormBody body = builder.build();
            Request.Builder requestBuilder = new Request.Builder();
            requestBuilder.url(url).post(body);
            if (headerParams != null && !headerParams.isEmpty()) {
                requestBuilder.headers(setHeaders(headerParams));
            }
            Request request =requestBuilder.build();
            return doPost(request);
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                throw new RuntimeException(toExceptionInfo(url, "POST", e.getMessage(), null, params));
            } else {
                throw e;
            }
        }
    }

    private static String doPost(Request request) throws Exception{
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static Response execute(Request request) throws Exception {
        Response response = client.newCall(request).execute();
        return response;
    }

    private static String toExceptionInfo(String url, String method, String errMsg, String json, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("http请求异常:\r\n");
        sb.append("请求url：").append(url).append("\r\n");
        if (StringUtils.isNotEmpty(json)) {
            sb.append("请求参数：").append(json).append("\r\n");
        }
        if (params != null && !params.isEmpty()) {
            sb.append("请求参数：").append(JSONObject.toJSONString(params)).append("\r\n");
        }
        sb.append("请求方法：").append(method).append("\r\n");
        sb.append("异常信息：").append("[").append(errMsg).append("]");
        return sb.toString();
    }

    /**
     * 设置头信息
     */
    private static Headers setHeaders(Map<String, String> headersParams) {
        Headers headers = null;
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headersParams != null && !headersParams.isEmpty()) {
            for (Map.Entry<String, String> entry : headersParams.entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        headers = headerBuilder.build();
        return headers;
    }

    public static void main(String[] args) {
        String url = "http://localhost:9010/api/user/test";
        try {
            doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
