package com.lay.util.controllerResponse.mutexBody;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static java.time.LocalDateTime.now;
import static java.util.Collections.emptyMap;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/2/24 11:48
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/2/24 lei.yue 1.0 create file
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE, creatorVisibility = NONE)
public final class WebError {

    /**
     *
     */
    public static final String EXCEPTION_ERROR_DETAIL_ATTRIBUTE = "exception";

    /**
     *
     */
    public static final String EXCEPTION_MESSAGE_ERROR_DETAIL_ATTRIBUTE = "exceptionMessage";

    /**
     *
     */
    public static final String EXCEPTION_STACKTRACE_ERROR_DETAIL_ATTRIBUTE = "exceptionStacktrace";

    /**
     * 时间戳【必须】
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * HTTP状态码【必须】
     */
    private int status;


    /**
     * 错误状态码【可选，当status为4XX,5XX时必须】
     */
    private String error;

    /**
     *
     */
    private String message;

    /**
     * 错误明细【可选，当status为4XX,5XX时必须】
     */
    private Map<String, String> details = new LinkedHashMap<>();

    /**
     *
     */
    private WebError() {
    }

    /**
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = ofNullable(timestamp).orElse(now());
    }

    public WebError timestamp(LocalDateTime timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return HttpStatus.valueOf(status);
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HttpStatus status) {
        this.status = Optional.of(requireNonNull(status, "status"))
                .filter(HttpStatus::isError)
                .map(HttpStatus::value)
                .orElseThrow(() -> new IllegalArgumentException("status"));
    }

    public WebError status(HttpStatus status) {
        setStatus(status);
        return this;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = ofNullable(error).orElse("UNKNOW_ERROR");
    }

    public WebError error(String error) {
        setError(error);
        return this;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = ofNullable(message).orElse("No available error messages.");
    }

    public WebError message(String message) {
        setMessage(message);
        return this;
    }

    /**
     * @return the details
     */
    public Map<String, String> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(Map<String, String> details) {
        getDetails().clear();
        getDetails().putAll(ofNullable(details).orElse(emptyMap()));
    }

    public WebError details(Map<String, String> details) {
        setDetails(details);
        return this;
    }

    public WebError detail(String key, String value) {
        putDetail(key, value);
        return this;
    }

    /**
     * @param key
     * @param value
     */
    public String putDetail(String key, String value) {
        return getDetails().put(key, value);
    }

    /**
     * @param key
     * @return
     */
    public String getDetail(String key) {
        return getDetails().get(key);
    }

    /**
     * @param key
     */
    public String removeDetail(String key) {
        return getDetails().remove(key);
    }

    @Override
    public String toString() {
        return "WebError{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", details=" + details +
                '}';
    }

    public static WebError of(HttpStatus status) {
        return of(status, null, null, null);
    }

    public static WebError of(HttpStatus status, String error) {
        return of(status, error, null, null);
    }

    public static WebError of(HttpStatus status, String error, String message) {
        return of(status, error, message, null);
    }

    public static WebError of(HttpStatus status, String error, String message, Map<String, String> details) {
        return new WebError().timestamp(now())
                .status(status)
                .error(ofNullable(error).orElse(status.name()))
                .message(ofNullable(message).orElse(status.getReasonPhrase()))
                .details(details);
    }

    public static WebError enhance(WebError webError, Throwable exception) {
        if (nonNull(exception)) {
            final StringWriter stackTrace = new StringWriter();
            exception.printStackTrace(new PrintWriter(stackTrace));
            stackTrace.flush();
            Class<? extends Throwable> exceptionClass = exception.getClass();
            webError.putDetail(EXCEPTION_ERROR_DETAIL_ATTRIBUTE, exceptionClass.getName());
            webError.putDetail(EXCEPTION_MESSAGE_ERROR_DETAIL_ATTRIBUTE, exception.getMessage());
            webError.putDetail(EXCEPTION_STACKTRACE_ERROR_DETAIL_ATTRIBUTE, stackTrace.toString());
        }
        return webError;
    }

}

