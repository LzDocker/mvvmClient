package com.docker.core.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 */

public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

//    private Context mContext;
//    private DeviceInfo mDeviceInfo;
//    private GlobalToken mGlobalToken;
//    private ProgressRequestListener mRequestListener;
//    private HeaderParams mHeader;
//
//    public CommonParamsInterceptor(Context context, GlobalToken globalToken, DeviceInfo deviceInfo, HeaderParams header) {
//        mContext = context;
//        mGlobalToken = globalToken;
//        mDeviceInfo = deviceInfo;
//        mHeader = header;
//    }
//
//    public void setProgressRequestListener(ProgressRequestListener requestListener) {
//        mRequestListener = requestListener;
//    }
//
////    private static final String SERVER_URL = "http://app.hivescm.top/";
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request originRequest = chain.request();
//        HttpUrl oldHttpUrl = originRequest.url();
//        if (mDeviceInfo.isOpenServer() && (!TextUtils.isEmpty(mHeader.getBaseHost()) && oldHttpUrl.toString().startsWith(mHeader.getBaseHost()) || !TextUtils.isEmpty(mHeader.getServerUrl()) && oldHttpUrl.toString().startsWith(mHeader.getServerUrl()))) {
//            Request.Builder newRequest = originRequest.newBuilder();
//            if (!TextUtils.isEmpty(mHeader.getServerUrl()) && !mHeader.getServerUrl().equals(mHeader.getBaseHost())) {
//                if (!oldHttpUrl.toString().startsWith(mHeader.getServerUrl())) {
//                    HttpUrl newBaseUrl = HttpUrl.parse(oldHttpUrl.toString().replace(mHeader.getBaseHost(), mHeader.getServerUrl()));
//                    newRequest.url(newBaseUrl);
//                }
//
//            }
//            // Header
//            Headers.Builder newHeaderBuilder = originRequest.headers().newBuilder();
//            Map<String, String> headerMap = getHeaderMap();
//            if (headerMap != null && !headerMap.isEmpty()) {
//                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
//                    newHeaderBuilder.add(entry.getKey(), entry.getValue());
//                }
//                newRequest.headers(newHeaderBuilder.build());
//            }
//            String content_type = "";
//            // Query Param
//            if ("GET".equals(originRequest.method())) {
//                content_type = "get";
//                Map<String, Object> queryParamMap = new HashMap<>();
//                Uri uri = Uri.parse(originRequest.url().toString());
//                Set<String> set = uri.getQueryParameterNames();
//                Iterator<String> it = set.iterator();
//                while (it.hasNext()) {
//                    String key = it.next();
//                    queryParamMap.put(key, uri.getQueryParameter(key));
//                }
//                buildNewParams(newRequest, queryParamMap, content_type);
//            } else if ("POST".equals(originRequest.method())) {
//                RequestBody body = originRequest.body();
//                if (body != null && body instanceof FormBody) {
//                    content_type = "form";
//                    // POST Param x-www-form-urlencoded
//                    FormBody formBody = (FormBody) body;
//                    Map<String, Object> formBodyParamMap = new HashMap<>();
//                    int bodySize = formBody.size();
//                    for (int i = 0; i < bodySize; i++) {
//                        String key = formBody.name(i);
//                        String value = formBody.value(i);
//                        if (formBodyParamMap.get(key) != null) {
//                            Object obj = formBodyParamMap.get(key);
//                            if (obj instanceof String) {
//                                List<Object> values = new ArrayList<>();
//                                values.add(obj);
//                                values.add(value);
//                                formBodyParamMap.put(key, values);
//                            } else if (obj instanceof List) {
//                                List<Object> values = (List<Object>) obj;
//                                values.add(value);
//                                formBodyParamMap.put(key, values);
//                            }
//                        } else {
//                            formBodyParamMap.put(formBody.name(i), formBody.value(i));
//                        }
//
//                    }
//                    buildNewParams(newRequest, formBodyParamMap, content_type);
//                } else if (body != null && body instanceof MultipartBody) {
//                    content_type = "form";
//                    // POST Param form-data
//                    MultipartBody multipartBody = (MultipartBody) body;
//                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
//                    Map<String, Object> formBodyParamMap = new HashMap<>();
//                    List<MultipartBody.Part> parts = multipartBody.parts();
//                    for (MultipartBody.Part part : parts) {
//                        RequestBody requestBody = part.body();
//                        if (requestBody != null && requestBody instanceof FormBody) {
//                            FormBody formBody = (FormBody) requestBody;
//                            int bodySize = formBody.size();
//                            for (int i = 0; i < bodySize; i++) {
//                                String key = formBody.name(i);
//                                String value = formBody.value(i);
//                                if (formBodyParamMap.get(key) != null) {
//                                    Object obj = formBodyParamMap.get(key);
//                                    if (obj instanceof String) {
//                                        List<Object> values = new ArrayList<>();
//                                        values.add(obj);
//                                        values.add(value);
//                                        formBodyParamMap.put(key, values);
//                                    } else if (obj instanceof List) {
//                                        List<Object> values = (List<Object>) obj;
//                                        values.add(value);
//                                        formBodyParamMap.put(key, values);
//                                    }
//                                } else {
//                                    formBodyParamMap.put(formBody.name(i), formBody.value(i));
//                                }
//
//                            }
//                        } else {
//                            builder.addPart(part);
//                        }
//                    }
//                    Map<String, Object> headerParams = getHeaderParams();
//                    RequestParams requestParams = new RequestParams();
//                    requestParams.setBody(formBodyParamMap);
//                    requestParams.setHeader(headerParams);
//                    String data = requestParams.toString();
//                    Map<String, String> newParams = new HashMap<>();
//                    try {
//                        String edata = new String(Encoder.base64.encode(Encoder.encrypt.encode(data.getBytes())));
//                        newParams.put("edata", edata);
//                        if (mDeviceInfo.isDebug())
//                            newParams.put("dev", data);
//                        newParams.put("fid", AES.KEY_SERSION);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    for (Map.Entry<String, String> entry : newParams.entrySet()) {
//                        builder.addFormDataPart(entry.getKey(), entry.getValue());
//                    }
////                    ProgressRequestBody requestBody = ProgressHelper.addProgressRequestListener(builder.build(), mRequestListener);
//                    newRequest.method("POST", builder.build());
//                } else {
//                    MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");
//                    MediaType conteType = body.contentType();
//                    if (conteType != null && !TextUtils.isEmpty(conteType.toString()) && mediaType.toString().contains(conteType.toString())) {
//                        content_type = "json";
//                        String oriBody = bodyToString(body);
//                        RequestParams requestParams = new RequestParams();
//                        requestParams.setBody(null);
//                        Map<String, Object> headerParams = getHeaderParams();
//                        headerParams.put("content_type", content_type);
//                        requestParams.setHeader(headerParams);
//                        String data = requestParams.toString();
//                        try {
//                            JSONObject jsonObject = new JSONObject(data);
//                            jsonObject.put("body", new JSONObject(oriBody));
//                            data = jsonObject.toString();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            try {
//                                JSONObject jsonObject = new JSONObject(data);
//                                JSONObject fw_array_data = new JSONObject();
//                                fw_array_data.put("fw_array_data",new JSONArray(oriBody));
//                                jsonObject.put("body", fw_array_data);
//                                data = jsonObject.toString();
//                            } catch (Exception e1) {
//                                e1.printStackTrace();
//                            }
//                        }
//                        Map<String, String> newParams = new HashMap<>();
//                        try {
//                            String edata = new String(Encoder.base64.encode(Encoder.encrypt.encode(data.getBytes())));
//                            newParams.put("edata", edata);
//                            newParams.put("fid", AES.KEY_SERSION);
//                            if (mDeviceInfo.isDebug())
//                                newParams.put("dev", data);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        FormBody.Builder bodyBuilder = new FormBody.Builder();
//                        for (Map.Entry<String, String> entry : newParams.entrySet()) {
//                            bodyBuilder.add(entry.getKey(), entry.getValue());
//                        }
//                        newRequest.method(originRequest.method(), bodyBuilder.build());
//                    } else {
//                        content_type = "form";
//                        buildNewParams(newRequest, null, content_type);
//                    }
//                }
//            }
//            Request request = newRequest.build();
//            HLog.log("Request: " + request + "Headers:" + bodyToString(request.headers()) + "----Params:" + bodyToString(request.body()));
//            Response response = chain.proceed(request);
//            return interceptorResponce(response);
//        }
//        if (mHeader.getParams() != null) {
//            for (Map.Entry<String, String> entry : mHeader.getParams().entrySet()) {
//                originRequest = originRequest.newBuilder().addHeader(entry.getKey(), entry.getValue()).build();
//            }
//        }
//        if (mDeviceInfo.isDebug() && (!TextUtils.isEmpty(mHeader.getBaseHost()) && oldHttpUrl.toString().startsWith(mHeader.getBaseHost()) || !TextUtils.isEmpty(mHeader.getServerUrl()) && oldHttpUrl.toString().startsWith(mHeader.getServerUrl()))) {
//            Request.Builder newRequest = originRequest.newBuilder();
//            if (!TextUtils.isEmpty(mHeader.getServerUrl()) && !mHeader.getServerUrl().equals(mHeader.getBaseHost())) {
//                if (!oldHttpUrl.toString().startsWith(mHeader.getServerUrl())) {
//                    HttpUrl newBaseUrl = HttpUrl.parse(oldHttpUrl.toString().replace(mHeader.getBaseHost(), mHeader.getServerUrl()));
//                    newRequest.url(newBaseUrl);
//                }
//
//            }
//            Request request = newRequest.build();
//            HLog.log("Request: " + request + "Headers:" + bodyToString(request.headers()) + "----Params:" + bodyToString(request.body()));
//            Response response = chain.proceed(request);
//            ResponseBody responseBody = response.body();
//            try {
//                byte[] bytes = responseBody.string().getBytes();
//                loginFilter(bytes, responseBody.contentType().charset());
//                ResponseBody newResponceBody = ResponseBody.create(responseBody.contentType(), bytes);
//                response = response.newBuilder().body(newResponceBody).build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
//        HLog.log("Request: " + chain.request() + "Headers:" + bodyToString(originRequest.headers()) + "----Params:" + bodyToString(originRequest.body()));
//        Response response = chain.proceed(originRequest);
//        ResponseBody responseBody = response.body();
//        try {
//            byte[] bytes = responseBody.string().getBytes();
//            loginFilter(bytes, responseBody.contentType().charset());
//            ResponseBody newResponceBody = ResponseBody.create(responseBody.contentType(), bytes);
//            response = response.newBuilder().body(newResponceBody).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return response;
//
//
//    }
//
//    private void buildNewParams(Request.Builder newRequest, Map<String, Object> formBodyParamMap, String content_type) {
//        Map<String, Object> headerParams = getHeaderParams();
//        headerParams.put("content_type", content_type);
//        RequestParams requestParams = new RequestParams();
//        requestParams.setBody(formBodyParamMap);
//        requestParams.setHeader(headerParams);
//        String data = requestParams.toString();
//        Map<String, String> newParams = new HashMap<>();
//        try {
//            String edata = new String(Encoder.base64.encode(Encoder.encrypt.encode(data.getBytes())));
//            newParams.put("edata", edata);
//            newParams.put("fid", AES.KEY_SERSION);
//            if (mDeviceInfo.isDebug())
//                newParams.put("dev", data);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FormBody.Builder bodyBuilder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : newParams.entrySet()) {
//            bodyBuilder.add(entry.getKey(), entry.getValue());
//        }
//        newRequest.method("POST", bodyBuilder.build());
//    }
//
//    private Response interceptorResponce(Response response) throws IOException {
//        List<String> contentEncodings = response.headers("content-encoding");
//        if (contentEncodings != null && !contentEncodings.isEmpty()) {
//            ResponseBody responseBody = response.body();
//            String oriData = responseBody.string();
//            try {
//                byte[] bytes = Encoder.decode(oriData.getBytes(), contentEncodings);
//                loginFilter(bytes, responseBody.contentType().charset());
//                ResponseBody newResponceBody = ResponseBody.create(responseBody.contentType(), bytes);
//                response = response.newBuilder().body(newResponceBody).build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            ResponseBody responseBody = response.body();
//            try {
//                byte[] bytes = responseBody.string().getBytes();
//                loginFilter(bytes, responseBody.contentType().charset());
//                ResponseBody newResponceBody = ResponseBody.create(responseBody.contentType(), bytes);
//                response = response.newBuilder().body(newResponceBody).build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return response;
//    }
//
//    //拦截登录
//    private void loginFilter(byte[] bytes, Charset charset) {
//        try {
//            String data = null;
//            if (charset == null) {
//                data = new String(bytes);
//            } else {
//                data = new String(bytes, charset);
//            }
//            JSONObject jsonObject = new JSONObject(data);
//            JSONObject statusObject = jsonObject.getJSONObject("status");
//            long statusCode = statusObject.getLong("statusCode");
//            String statusCodeStr = String.valueOf(statusCode);
//            if (statusCodeStr.endsWith("1155001") && mContext != null) {
//                Intent intent = new Intent("android.intent.action.common.LoginReceiver");
//                mContext.sendBroadcast(intent);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String bodyToString(final RequestBody request) {
//        try {
//            final RequestBody copy = request;
//            final Buffer buffer = new Buffer();
//            if (copy != null) {
//                copy.writeTo(buffer);
//            } else {
//                return "";
//            }
//            return buffer.readUtf8();
//        } catch (final IOException e) {
//            return "did not work";
//        }
//    }
//
//    private static String bodyToString(final Headers headers) {
//        Map<String, List<String>> params = headers.toMultimap();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
//            stringBuilder.append(entry.getKey()).append(":").append(entry.getValue());
//        }
//        return stringBuilder.toString();
//    }
//
//    public Map<String, String> getHeaderMap() {
//        return mHeader.getParams();
//    }
//
//
//    public Map<String, Object> getHeaderParams() {
//        Map<String, Object> commonParams = new HashMap<>();
//        commonParams.put("userId", mGlobalToken.getUserId());//用户ID
//        commonParams.put("fwToken", mGlobalToken.getFwToken());//用户登录后获取到的用户token
//        commonParams.put("brand", mDeviceInfo.getBrand()); // 手机品牌
//        commonParams.put("mid", mDeviceInfo.getModel());//手机型号
//        commonParams.put("w", mDeviceInfo.getScreenWidth());// 手机屏幕宽度
//        commonParams.put("h", mDeviceInfo.getScreenHeight());// 手机屏幕高度
//        commonParams.put("channel", mDeviceInfo.getChannel());//渠道
//        commonParams.put("IMEI", mDeviceInfo.getIMEI());//IMEI
//        commonParams.put("mac", mDeviceInfo.getMac());//MAC地址
//        commonParams.put("udid", mDeviceInfo.getUdid());//UDID
//        commonParams.put("platform", "android");//平台:android,iphone,ipad
//        commonParams.put("os", mDeviceInfo.getSdk());   // 系统版本
//        commonParams.put("version", mDeviceInfo.getVersionName()); // 客户端版本名称,版本号格式：[1~9]+.[0~9].[0~9]
//        commonParams.put("version_code", mDeviceInfo.getVersionCode());   // 客户端版本号
//        commonParams.put("network", NetworkHelpers.getNetworkType(mContext));//客户端当前使用网络环境 例如:android,iphone,ipad,WIFI,unknown
//        commonParams.put("Accept-Encoding", mDeviceInfo.getAcceptEncoding());  //1 压缩,0 不压缩 //控制返回结果是否启用压缩
//        commonParams.put("encrypt", mDeviceInfo.getEncrypt());// 1加密, 0 非加密，返回结果是否启用加密,加密类型与当前客户端请求加密方式及加密版本保持一致
//        commonParams.put("appid", mDeviceInfo.getPackageName());  //手机APPID（客户端包名）
//        commonParams.put("guid", mDeviceInfo.getDeviceId());  //客户端唯一ID
//        commonParams.put("appType", mDeviceInfo.getAppType()); //应用类型 例如：OMS,TMS,WMS...
//        return commonParams;
//    }
}
