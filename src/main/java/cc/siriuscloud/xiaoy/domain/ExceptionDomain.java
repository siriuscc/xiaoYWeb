package cc.siriuscloud.xiaoy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExceptionDomain {
    private  String name;
    private String message;
    private String causeMsg;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    public ExceptionDomain(String name, String message, String causeMsg) {
        this.name = name;
        this.message = message;
        this.causeMsg = causeMsg;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCauseMsg() {
        return causeMsg;
    }

    public void setCauseMsg(String causeMsg) {
        this.causeMsg = causeMsg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
