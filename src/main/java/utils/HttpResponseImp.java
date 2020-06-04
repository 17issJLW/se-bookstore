package utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpResponseImp implements HttpServletResponse {


    private String url = "";
    private int statusCode = SC_OK;
    private int ContentLength = 0;
    private String message = "";
    private String ContentType = "application/json";
    private String charset = "utf-8";
    private Map<String,Object> headerMap = new HashMap<>();
    private StringBuffer httpResp = new StringBuffer();
    private String protocol = "HTTP/1.1";
    private OutputStream outputStream;

    public HttpResponseImp(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void loadResp(){

        if(!headerMap.containsKey("Date")){
            setDateHeader("Date", new Date().getTime());
        }
        if(!headerMap.containsKey("Content-Length")){
            setHeader("Content-Length",String.valueOf(message.getBytes().length));
        }
        if(!headerMap.containsKey("Content-Type")){
            setHeader("Content-Type",ContentType+";"+"charset="+charset);
        }
        if(!headerMap.containsKey("Server")){
            setHeader("Server","MyServer/1.0");
        }
        // 处理跨域,默认允许跨域
        if(!headerMap.containsKey("Access-Control-Allow-Origin")){
            setHeader("Access-Control-Allow-Origin","*");
            setHeader("Access-Control-Allow-Headers","DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Cookie,Content-Type,Token,Authorization");
            setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, OPTIONS");
            setHeader("Access-Control-Allow-Credentials","true");
        }

        httpResp.append(protocol).append(" ")
                .append(statusCode).append(" ")
                .append("OK").append("\r\n");
        for(Map.Entry<String,Object> e :headerMap.entrySet()){
            httpResp.append(e.getKey()).append(":").append(e.getValue().toString()).append("\r\n");
        }
        httpResp.append("\r\n");
        httpResp.append(message).append("\r\n\r\n");
        System.out.println(httpResp.toString());

    }

    public void sendResponse() throws IOException {
        this.loadResp();
        this.outputStream.write(httpResp.toString().getBytes());
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return headerMap.containsKey(s);
    }

    @Override
    public String encodeURL(String s) {
        return s;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return s;
    }

    @Override
    public String encodeUrl(String s) {
        return s;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return s;
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        this.statusCode = i;
        this.message = s;
    }

    @Override
    public void sendError(int i) throws IOException {
        this.statusCode = i;
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        this.url = s;
    }

    @Override
    public void setDateHeader(String s, long l) {
        Date date = new Date(l);
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        headerMap.put(s,dateString);
    }

    @Override
    public void addDateHeader(String s, long l) {
        headerMap.put(s,l);
    }

    @Override
    public void setHeader(String s, String s1) {
        this.headerMap.put(s,s1);
    }

    @Override
    public void addHeader(String s, String s1) {
        this.headerMap.put(s,s1);
    }

    @Override
    public void setIntHeader(String s, int i) {
        this.headerMap.put(s,i);
    }

    @Override
    public void addIntHeader(String s, int i) {
        this.headerMap.put(s,i);
    }

    @Override
    public void setStatus(int i) {
        this.statusCode = i;

    }

    @Override
    public void setStatus(int i, String s) {
        this.statusCode = i;
        this.message = s;
    }

    @Override
    public int getStatus() {
        return this.statusCode;
    }

    @Override
    public String getHeader(String s) {
        return (String) headerMap.get(s);
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return headerMap.keySet();
    }

    @Override
    public String getCharacterEncoding() {
        return this.charset;
    }

    @Override
    public String getContentType() {
        return this.ContentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return (ServletOutputStream) this.outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) {
        this.charset = s;
    }

    @Override
    public void setContentLength(int i) {
        this.ContentLength = i;
        setHeader("Content-Length",String.valueOf(i));
    }

    @Override
    public void setContentLengthLong(long l) {
        this.ContentLength = (int)l;
        setHeader("Content-Length",String.valueOf(this.ContentLength));
    }

    @Override
    public void setContentType(String s) {
        this.ContentType = s;

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }


}
