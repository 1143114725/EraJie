package com.erajie.httptoos;

import android.text.TextUtils;

import com.erajie.rxutils.RxLogTool;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 网络请求日志管理类
 * @author EraJi
 */
public class NetLogInterceptor implements Interceptor {

    private static final String TAG = "eehNetLog";
    private static boolean DEBUG = true;
    private String tag;
    private boolean showResponse;

    public NetLogInterceptor(String tag) {

        this(tag, false);
    }

    public NetLogInterceptor(String tag, boolean showResponse) {

        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private void logForRequest(Request request) {

        try {
            String url = request.url().toString();
            Headers headers = request.headers();
            if (DEBUG) {
                RxLogTool.e(tag, "========request'zxLog=======");
                RxLogTool.e(tag, "method : " + request.method());
                RxLogTool.e(tag, "url : " + url);
            }

            if (headers != null && headers.size() > 0) {
                if (DEBUG) {
                    RxLogTool.e(tag, "headers : " + headers.toString());
                }

            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    if (DEBUG) {
                        RxLogTool.e(tag, "requestBody's contentType : " + mediaType.toString());
                    }
                    if (isText(mediaType)) {
                        if (DEBUG) {
                            RxLogTool.e(tag, "requestBody's content : " + bodyToString(request));
                        }
                    } else {
                        if (DEBUG) {
                            RxLogTool.e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                    }
                }
            }
            if (DEBUG) {
                RxLogTool.e(tag, "========request'zxLog=======end");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response logForResponse(Response response) {

        try {
            if (DEBUG) {
                RxLogTool.e(tag, "========response'zxLog=======");
            }

            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            if (DEBUG) {
                RxLogTool.e(tag, "url : " + clone.request().url());
                RxLogTool.e(tag, "code : " + clone.code());
                RxLogTool.e(tag, "protocol : " + clone.protocol());
            }

            if (! TextUtils.isEmpty(clone.message())) {
                if (DEBUG) {
                    RxLogTool.e(tag, "message : " + clone.message());
                }

            }

            if (showResponse) {

                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        if (DEBUG) {
                            RxLogTool.e(tag, "responseBody's contentType : " + mediaType.toString());
                        }

                        if (isText(mediaType)) {
                            String resp = body.string();
                            if (DEBUG) {
                                RxLogTool.e(tag, "responseBody's content : " + resp);
                            }

                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            if (DEBUG) {
                                RxLogTool.e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            /*e.printStackTrace();*/
        } finally {
            if (DEBUG) {
                RxLogTool.e(tag, "========response'zxLog=======end");
            }
        }

        return response;
    }

    private boolean isText(MediaType mediaType) {

        if (mediaType.type() != null && "text".equals(mediaType.type())) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") || mediaType.subtype().equals("xml") || mediaType.subtype().equals("html") || mediaType.subtype().equals("webviewhtml")) {
                return true;
            }

        }
        return false;
    }

    private String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}