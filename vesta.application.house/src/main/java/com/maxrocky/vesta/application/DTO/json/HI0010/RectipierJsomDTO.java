package com.maxrocky.vesta.application.DTO.json.HI0010;

/**
 * Created by Magic on 2016/5/12.
 */
public class RectipierJsomDTO {

    private String id;
    private String type;

    public RectipierJsomDTO(String id,String type){
        this.id=id;
        this.type=type;
    }

    public  RectipierJsomDTO(){
        id="";
        type="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
