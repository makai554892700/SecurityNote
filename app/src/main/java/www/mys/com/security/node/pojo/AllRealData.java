package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

import java.util.ArrayList;

public class AllRealData extends BasePoJo {

    @FieldDesc(key = "version")
    public int version;
    @FieldDesc(key = "datas", arrayType = BaseRealNode.class)
    public ArrayList<BaseRealNode> datas;
    @FieldDesc(key = "createAt")
    public long createAt;
    @FieldDesc(key = "updateAt")
    public long updateAt;

    public AllRealData(String jsonStr) {
        super(jsonStr);
    }

}
