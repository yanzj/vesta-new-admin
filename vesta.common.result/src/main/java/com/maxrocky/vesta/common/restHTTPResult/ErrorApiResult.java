package com.maxrocky.vesta.common.restHTTPResult;


import com.maxrocky.vesta.message.error.ErrorMessage;

/**
 * Created by JillChen on 2015/12/23.
 */
public class ErrorApiResult extends ApiResult{

    public ErrorApiResult(String errorKey){
        this(ErrorMessage.getErrorCode(errorKey), ErrorMessage.getMessage(errorKey),null);
    }

    public ErrorApiResult(int errorCode, String errorMessage){
        this(errorCode,errorMessage,null);
    }

    public ErrorApiResult(String errorKey, Exception exception){
        this.addAttribute("code",ErrorMessage.getErrorCode(errorKey));
        this.addAttribute("msg", ErrorMessage.getMessage(errorKey));
        this.addAttribute("data","");
        if(exception!=null){
            this.addAttribute("innerExceptionMessage",exception.getMessage());
            this.addAttribute("innerExceptionStackTrace",exception.getStackTrace());
        }
    }

    public ErrorApiResult(int errorCode, String errorMessage, Exception exception){
        this.addAttribute("code",errorCode);
        this.addAttribute("msg",errorMessage);
        this.addAttribute("data","");
        if(exception!=null){
            this.addAttribute("innerExceptionMessage",exception.getMessage());
            this.addAttribute("innerExceptionStackTrace",exception.getStackTrace());
        }
    }
}
