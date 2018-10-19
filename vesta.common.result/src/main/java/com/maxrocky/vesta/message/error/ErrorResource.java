package com.maxrocky.vesta.message.error;

import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by JillChen on 2015/12/25.
 */
public class ErrorResource {

    public static ErrorApiResult getError(String errorKey){
        String host =  (String) CustomizedPropertyPlaceholderConfigurer.getContextProperty(errorKey);
        int errorCode = getErrorCode(errorKey);
//        int errorCode = 500;
//        String[] errorKeyArray = errorKey.split("_");
////        errorCode = 301;
//        switch (errorKeyArray[0]){
//            case "tip":
//                errorCode = 301;
//                break;
//            case "form":
//                errorCode = 302;
//                break;
//            case "error":
//                errorCode = 301;
//                break;
//            case "action":
//                errorCode = 301;
//                break;
//            case "fun":
//                errorCode = 301;
//                break;
//            case "desc":
//                errorCode = 301;
//                break;
//            case "state":
//                errorCode = 301;
//                break;
//
//        }
        return new ErrorApiResult(errorCode,host);
    }

    public static int getErrorCode(String errorKey){
        int errorCode = 500;
        String[] errorKeyArray = errorKey.split("_");
//        errorCode = 301;
        switch (errorKeyArray[0]) {
            case "tip":
                errorCode = 301;
                break;
            case "form":
                errorCode = 302;
                break;
            case "error":
                errorCode = 301;
                break;
            case "action":
                errorCode = 301;
                break;
            case "fun":
                errorCode = 301;
                break;
            case "desc":
                errorCode = 301;
                break;
            case "state":
                errorCode = 301;
                break;
        }
        return errorCode;
    }

}
