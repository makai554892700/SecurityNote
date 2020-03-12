package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class ContactAddress extends BasePoJo {

    @FieldDesc(key = "name")
    public String name;                     //联系人姓名
    @FieldDesc(key = "phone")
    public String phone;                    //联系人号码
    @FieldDesc(key = "address")
    public String address;                  //地址
    @FieldDesc(key = "zipCode")
    public String zipCode;                  //邮编

    public ContactAddress(String jsonStr) {
        super(jsonStr);
    }

}
