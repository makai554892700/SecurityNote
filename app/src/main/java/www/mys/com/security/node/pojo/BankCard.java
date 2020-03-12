package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class BankCard extends BasePoJo {

    @FieldDesc(key = "name")
    public String name;                         //银行名称
    @FieldDesc(key = "number")
    public String number;                       //银行卡号
    @FieldDesc(key = "userName")
    public String userName;                     //用户名
    @FieldDesc(key = "pass")
    public String pass;                         //银行卡密码
    @FieldDesc(key = "branch")
    public String branch;                       //支行
    @FieldDesc(key = "phone")
    public String phone;                        //手机号码
    @FieldDesc(key = "zipCode")
    public String zipCode;                      //zipCode
    @FieldDesc(key = "swiftCode")
    public String swiftCode;                    //SwiftCode
    @FieldDesc(key = "CNAPS")
    public String CNAPS;                        //CNAPS

    public BankCard(String jsonStr) {
        super(jsonStr);
    }

}
