package org.hy.common.json;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.Charset;

public class MappingXJSON2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public MappingXJSON2HttpMessageConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }
//    public void setObjectMapper(ObjectMapper objectMapper) {
//        Assert.notNull(objectMapper, "ObjectMapper must not be null");
//        this.objectMapper = objectMapper;
//    }

    @Override
    protected boolean supports(Class<?> aClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
//        JavaType javaType = this.getJavaType(clazz);
//
//        try {
//            return this.objectMapper.readValue(inputMessage.getBody(), javaType);
//        } catch (IOException var5) {
//            throw new HttpMessageNotReadableException("Could not read JSON: " + var5.getMessage(), var5);
//        }
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {

    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//
//package org.springframework.http.converter.json;
//
//        import com.fasterxml.jackson.core.JsonEncoding;
//        import com.fasterxml.jackson.core.JsonGenerator;
//        import com.fasterxml.jackson.databind.JavaType;
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import java.io.IOException;
//        import java.nio.charset.Charset;
//        import org.springframework.http.HttpInputMessage;
//        import org.springframework.http.HttpOutputMessage;
//        import org.springframework.http.MediaType;
//        import org.springframework.http.converter.AbstractHttpMessageConverter;
//        import org.springframework.http.converter.HttpMessageNotReadableException;
//        import org.springframework.http.converter.HttpMessageNotWritableException;
//        import org.springframework.util.Assert;
//
//public class MappingJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object> {
//    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
//    private ObjectMapper objectMapper = new ObjectMapper();
//    private boolean prefixJson = false;
//
//    public MappingJackson2HttpMessageConverter() {
//        super(new MediaType("application", "json", DEFAULT_CHARSET));
//    }
//
//    public void setObjectMapper(ObjectMapper objectMapper) {
//        Assert.notNull(objectMapper, "ObjectMapper must not be null");
//        this.objectMapper = objectMapper;
//    }
//
//    public ObjectMapper getObjectMapper() {
//        return this.objectMapper;
//    }
//
//    public void setPrefixJson(boolean prefixJson) {
//        this.prefixJson = prefixJson;
//    }
//
//    public boolean canRead(Class<?> clazz, MediaType mediaType) {
//        JavaType javaType = this.getJavaType(clazz);
//        return this.objectMapper.canDeserialize(javaType) && this.canRead(mediaType);
//    }
//
//    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
//        return this.objectMapper.canSerialize(clazz) && this.canWrite(mediaType);
//    }
//
//    protected boolean supports(Class<?> clazz) {
//        throw new UnsupportedOperationException();
//    }
//
//    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
//        JavaType javaType = this.getJavaType(clazz);
//
//        try {
//            return this.objectMapper.readValue(inputMessage.getBody(), javaType);
//        } catch (IOException var5) {
//            throw new HttpMessageNotReadableException("Could not read JSON: " + var5.getMessage(), var5);
//        }
//    }
//
//    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
//        JsonEncoding encoding = this.getJsonEncoding(outputMessage.getHeaders().getContentType());
//        JsonGenerator jsonGenerator = this.objectMapper.getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
//
//        try {
//            if (this.prefixJson) {
//                jsonGenerator.writeRaw("{} && ");
//            }
//
//            this.objectMapper.writeValue(jsonGenerator, object);
//        } catch (IOException var6) {
//            throw new HttpMessageNotWritableException("Could not write JSON: " + var6.getMessage(), var6);
//        }
//    }
//
//    protected JavaType getJavaType(Class<?> clazz) {
//        return this.objectMapper.constructType(clazz);
//    }
//
//    protected JsonEncoding getJsonEncoding(MediaType contentType) {
//        if (contentType != null && contentType.getCharSet() != null) {
//            Charset charset = contentType.getCharSet();
//            JsonEncoding[] var6;
//            int var5 = (var6 = JsonEncoding.values()).length;
//
//            for(int var4 = 0; var4 < var5; ++var4) {
//                JsonEncoding encoding = var6[var4];
//                if (charset.name().equals(encoding.getJavaName())) {
//                    return encoding;
//                }
//            }
//        }
//
//        return JsonEncoding.UTF8;
//    }
//}

