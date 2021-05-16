package com.example.motorshop.helper;

import java.text.SimpleDateFormat;

public class Regex {
    //class này chứa các hàm xử lý ràng buộc hoặc hàm kiểm tra lỗi input (only num, only alphabet, etc)

    public boolean onlyNum(String str){
        return str.trim().matches("\\d+") ? true : false;
    }

    public boolean onlyAlpha(String str){
        return str.trim().matches("[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+") ? true : false;
    }

    public boolean onlyAlphaAndNum(String str){
        return str.trim().matches("[a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+") ? true : false;
    }

    public String formatCurrency(String unfmtStr){
        int mod = unfmtStr.trim().length()%3;

        String output = "";
        output += unfmtStr.substring(0,mod);
        unfmtStr = unfmtStr.substring(mod);
        int count = 0;
        while(count < unfmtStr.length()-3){
            output += "." + unfmtStr.substring(count, count+3);
            count += 3;
        }
        output += "." + unfmtStr.substring(count, unfmtStr.length());

        //xoa cac '.' du thua
        if(output.charAt(0) == '.') output = output.substring(1);
        if(output.charAt(0) == '-' && output.charAt(1) == '.') output = output.substring(0,1) + output.substring(2,output.length());
        if(output.charAt(output.length()-1) == '.') output = output.substring(0,output.length()-1);
        return output;
    }

    public String unFormatCurrency(String fmtStr){
        String tmp = "";
        String parts[] = fmtStr.split("\\.");
        for (String part : parts) {
            tmp += part;
        }
        return tmp;
    }

    public String getDateTime(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        //get dd/MM/yyyy only:  str.substring(0,10)
        //get hh:mm:ss only:    str.substring(11)
    }

}
