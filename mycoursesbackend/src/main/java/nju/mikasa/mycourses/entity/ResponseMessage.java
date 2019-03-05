package nju.mikasa.mycourses.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {

    private int code;
    private String msg;
    private Object data;

    public ResponseMessage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMessage setData(Object data){
        this.data=data;
        return this;
    }

}
