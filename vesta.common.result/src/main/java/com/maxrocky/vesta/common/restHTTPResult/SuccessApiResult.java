package com.maxrocky.vesta.common.restHTTPResult;

import java.util.Map;

/**
 * Created by JillChen on 2015/12/23.
 */
public class SuccessApiResult extends ApiResult  {
    public SuccessApiResult(Object data){
        this.init();
        this.addAttribute("data", data);
    }

    public SuccessApiResult(Map<Integer,String> codemap,Object data){

        java.util.Iterator it = codemap.entrySet().iterator();
            java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
        this.addAttribute("code", entry.getKey());
        this.addAttribute("msg", entry.getValue());
        this.addAttribute("data", data);
    }
    public SuccessApiResult(){
        this.init();
        this.addAttribute("data","");
    }

    private void init(){
        this.addAttribute("code", 0);
        this.addAttribute("msg","OK");
    }

    public Object getData(){
        return this.getOrDefault("data","");
    }
}
