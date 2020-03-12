package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

public class BaseSaveNode extends BasePoJo {

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

    public BaseSaveNode(String jsonStr) {
        super(jsonStr);
    }

}
