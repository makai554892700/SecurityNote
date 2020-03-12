package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class BaseRealNode extends BasePoJo {

    @FieldDesc(key = "key")
    public String key;
    @FieldDesc(key = "type")
    public int type;
    @FieldDesc(key = "typeName")
    public String typeName;
    @FieldDesc(key = "name")
    public String name;
    @FieldDesc(key = "data")
    public String data;
    @FieldDesc(key = "createAt")
    public long createAt;
    @FieldDesc(key = "updateAt")
    public long updateAt;

    public BaseRealNode(String jsonStr) {
        super(jsonStr);
    }

    public static class Type {
        public static final int TEXT = 1;
        public static final int ADDRESS = 2;
        public static final int RECEIPT = 3;
        public static final int BANK_CARD = 4;
        public static final int CREDIT_CARD = 5;
    }

}
