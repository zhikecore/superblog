//package com.zhike.blogbase.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.*;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.*;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created with IntelliJ IDEA.
// * User: lenovo
// * Date: 2021/2/1
// * Time: 23:25
// * Description: No Description
// */
//@Slf4j
//public class HttpClientUtils {
//
//    //private static final Logger LOG = Logger.getLogger(HttpClientUtils.class);
//
//    public static String doGet(String url, Map<String, String> param, Map<String, String> header) throws UnsupportedEncodingException {
//        //header = SignUtils.AppendGatewayHeaders(header,
//        //SignUtils.GetQueryParamsFromQueryString(url));
//        // 创建Httpclient对象
//        CloseableHttpClient  httpclient = HttpClients .createDefault();
//
//        String resultString = "";
//        CloseableHttpResponse response = null;
//        try {
//            // 创建uri
//            URIBuilder builder = new URIBuilder(url);
//            if (param != null) {
//                for (String key : param.keySet()) {
//                    builder.addParameter(key, param.get(key));
//                }
//            }
//
//            URI uri = builder.build();
//
//            // 创建http GET请求
//            HttpGet httpGet = new HttpGet(uri);
//            if (header != null) {
//                for (String key : header.keySet()) {
//                    httpGet.addHeader(key, header.get(key));
//                }
//            }
//            // 执行请求
//            response = httpclient.execute(httpGet);
//            // 判断返回状态是否为200
//            if (response.getStatusLine().getStatusCode() == 200) {
//                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                // httpclient.close();
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//        return resultString;
//    }
//
//    public static String doPost(String url, Map<String, String> param, Map<String, String> header ) throws UnsupportedEncodingException {
//        // 创建Httpclient对象
//        //header = SignUtils.AppendGatewayHeaders(header, null);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            // 创建参数列表
//            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"UTF-8");
//                httpPost.setEntity(entity);
//
//            }
//            if (header != null) {
//                for (String key : header.keySet()) {
//                    httpPost.addHeader(key, header.get(key));
//                }
//            }
//
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            //LOG.error(e);
//
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                //LOG.error(e);
//            }
//        }
//
//        return resultString;
//    }
//
//    public static String doPostOfMultipartFormData(String url, File file, Map<String, String> param) {
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//
//            // 创建请求内容
//            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//            if (file != null && file.exists() && file.isFile()) {
//                multipartEntityBuilder.addBinaryBody("file", file);
//            }
//            if (param != null) {
//                for (String key : param.keySet()) {
//                    multipartEntityBuilder.addTextBody(key, param.get(key));
//                }
//            }
//            httpPost.setEntity(multipartEntityBuilder.build());
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//
//        return resultString;
//    }
//
//    public static void printResponse(HttpResponse httpResponse)
//            throws ParseException, IOException {
//        // 获取响应消息实体
//        HttpEntity entity = httpResponse.getEntity();
//        // 响应状态
//        System.out.println("status:" + httpResponse.getStatusLine());
//        System.out.println("headers:");
//        HeaderIterator iterator = httpResponse.headerIterator();
//        while (iterator.hasNext()) {
//            System.out.println("\t" + iterator.next());
//        }
//        // 判断响应实体是否为空
//        if (entity != null) {
//            String responseString = EntityUtils.toString(entity);
//            System.out.println("response length:" + responseString.length());
//            System.out.println("response content:"
//                    + responseString.replace("\r\n", ""));
//        }
//    }
//
//
//    public static String doPostOfJson(String url, String json, Map<String, String> header ) throws Exception {
//        // 创建Httpclient对象
//
//        //header = SignUtils.AppendGatewayHeaders(header, null);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            if (header != null) {
//                for (String key : header.keySet()) {
//                    httpPost.addHeader(key, header.get(key));
//                }
//            }
//            // 创建请求内容
//            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//            httpPost.setEntity(entity);
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//            if(response.getStatusLine().getStatusCode()!=200 )
//                throw new Exception("status:"+response.getStatusLine()+"body:"+resultString);
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            throw e;
//
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//                throw e;
//            }
//        }
//
//        return resultString;
//    }
//
//    public static String doPutOfJson(String url, String json) {
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Put请求
//            HttpPut httpPut = new HttpPut(url);
//            // 创建请求内容
//            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//            httpPut.setEntity(entity);
//            // 执行http请求
//            response = httpClient.execute(httpPut);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//
//        return resultString;
//    }
//
//    public static String doDelete(String url, Map<String, String> param) {
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建uri
//            URIBuilder builder = new URIBuilder(url);
//            if (param != null) {
//                for (String key : param.keySet()) {
//                    builder.addParameter(key, param.get(key));
//                }
//            }
//            URI uri = builder.build();
//            // 创建Http Delete请求
//            HttpDelete httpDelete = new HttpDelete(uri);
//            // 执行http请求
//            response = httpClient.execute(httpDelete);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//
//        return resultString;
//    }
//
//    public static String doDeleteOfJson(String url, String json) {
//        /**
//         * 没有现成的delete可以带json的，自己实现一个，参考HttpPost的实现
//         */
//        class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
//            public static final String METHOD_NAME = "DELETE";
//
//            @SuppressWarnings("unused")
//            public HttpDeleteWithBody() {
//            }
//
//            @SuppressWarnings("unused")
//            public HttpDeleteWithBody(URI uri) {
//                setURI(uri);
//            }
//
//            public HttpDeleteWithBody(String uri) {
//                setURI(URI.create(uri));
//            }
//
//            @Override
//            public String getMethod() {
//                return METHOD_NAME;
//            }
//        }
//
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
//            StringEntity params = new StringEntity(json, ContentType.APPLICATION_JSON);
//            httpDelete.setEntity(params);
//            // 执行http请求
//            response = httpClient.execute(httpDelete);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//
//        return resultString;
//    }
//}
