package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class CreditCard extends BasePoJo {

    @FieldDesc(key = "name")
    public String name;                                 //银行名称
    @FieldDesc(key = "userName")
    public String userName;                                 //用户名称
    @FieldDesc(key = "number")
    public String number;                               //信用卡号
    @FieldDesc(key = "year")
    public String year;                                 //到期年
    @FieldDesc(key = "month")
    public String month;                                //到期月
    @FieldDesc(key = "securityCode")
    public String securityCode;                         //后三位
    @FieldDesc(key = "phone")
    public String phone;                                //手机号码
    @FieldDesc(key = "branch")
    public String branch;                               //支行

    public CreditCard(String jsonStr) {
        super(jsonStr);
    }

}
