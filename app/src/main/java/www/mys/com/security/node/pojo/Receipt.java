package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class Receipt extends BasePoJo {

    @FieldDesc(key = "name")
    public String name;                                 //抬头
    @FieldDesc(key = "number")
    public String number;                               //三证合一
    @FieldDesc(key = "phone")
    public String phone;                                //座机

    public Receipt(String jsonStr) {
        super(jsonStr);
    }

}
