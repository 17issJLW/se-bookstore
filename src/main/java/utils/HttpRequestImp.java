package utils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.*;

public class HttpRequestImp implements HttpServletRequest {

    private String method = "GET";
    private Map<String,String> headerMap = new HashMap<>();
    private String url;
    private String protocol;
    private Map<String,String> parameterMap = new HashMap<>();

    /**
     * 解析http报文
     * @param httpRequest
     */
    public HttpRequestImp(String httpRequest) throws IOException {

        System.out.println(httpRequest);
        // 分析第一行,请求方法，url，url内参数，协议
        try {
            String firstLine = httpRequest.split("\r\n")[0];
            this.method = firstLine.split("\\s")[0];
            String requestUrl = firstLine.split("\\s")[1];
            url = requestUrl.split("\\?")[0].trim();
            if(url.endsWith("\\")){
                url = url.substring(0, url.length()-1);
            }
            // 分析url中的参数 ？号后面，按&分割
            if(requestUrl.split("\\?").length >= 2){
                String[] parameterArray = requestUrl.split("\\?")[1].split("&");
                for(int i=0;i<parameterArray.length;i++){
                    if(parameterArray[i].split("=").length > 1){
                        parameterMap.put(parameterArray[i].split("=")[0].trim(),
                                parameterArray[i].split("=")[1].trim());
                    }
                }
            }

            this.protocol = firstLine.split("\\s")[2];

        }catch (Exception e){
            e.printStackTrace();
        }

        //分析header
        try{
            String[] headerArray = httpRequest.split("\r\n\r\n")[0].split("\r\n");
            for(int i=1;i < headerArray.length;i++){
                headerMap.put(headerArray[i].split(":")[0],headerArray[i].split(":")[1].trim());
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        //分析body
        try {
            if(httpRequest.split("\r\n\r\n").length > 1){
                // json
                if(getContentType()!=null && getContentType().startsWith("application/json")){
                    parameterMap.put("json",httpRequest.split("\r\n\r\n")[1]);
                }
                // 表单
                else {
                    String bodyString = httpRequest.split("\r\n\r\n")[1];
                    if(getContentType()!=null && getContentType().startsWith("application/x-www-form-urlencoded"))
                        bodyString = URLDecoder.decode(bodyString,"UTF-8");

                    String[] pArray = bodyString.split("&");
                    for(int i=0;i<pArray.length;i++){
                        if(pArray[i].split("=").length > 1){
                            parameterMap.put(pArray[i].split("=")[0].trim(),
                                    pArray[i].split("=")[1].trim());
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String getAuthType() {
        throw new RuntimeException("Not implemented");
//        return null;
    }

    @Override
    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    @Override
    public long getDateHeader(String s) {
        long res = 0;
        try{
            res = Long.valueOf(headerMap.get(s));
        }catch (Exception e){
            res = 0;
        }
        return res;
    }

    @Override
    public String getHeader(String s) {
        return headerMap.get(s);
    }

    @Override
    public Enumeration<String> getHeaders(String s) {
        return Collections.enumeration(headerMap.values());
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(headerMap.keySet());
    }

    @Override
    public int getIntHeader(String s) {
        int res = 0;
        try{
           res = Integer.valueOf(headerMap.get(s));
        }catch (Exception e){
           res = 0;
        }
        return res;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getPathInfo() {
        return null;
    }

    @Override
    public String getPathTranslated() {
        throw new RuntimeException("Not implemented");
//        return null;
    }

    @Override
    public String getContextPath() {
        throw new RuntimeException("Not implemented");
//        return null;
    }

    @Override
    public String getQueryString() {
        return null;
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return this.url;
    }

    @Override
    public StringBuffer getRequestURL() {
        return null;
    }

    @Override
    public String getServletPath() {
        return null;
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public String changeSessionId() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    @Override
    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String s, String s1) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    @Override
    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

    }

    @Override
    public int getContentLength() {
        return Integer.valueOf(headerMap.get("Content-Length"));
    }

    @Override
    public long getContentLengthLong() {
        return Long.valueOf(headerMap.get("Content-Length"));
    }

    @Override
    public String getContentType() {
        return headerMap.get("Content-Type");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public String getParameter(String s) {
        return parameterMap.get(s);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(parameterMap.keySet());
    }

    @Override
    public String[] getParameterValues(String s) {
        return (String[]) parameterMap.values().toArray();
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return null;
    }

    @Override
    public String getProtocol() {
        return this.protocol;
    }

    @Override
    public String getScheme() {
        return null;
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return 0;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return null;
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public void removeAttribute(String s) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
