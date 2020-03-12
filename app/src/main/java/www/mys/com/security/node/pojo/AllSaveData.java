package www.mys.com.security.node.pojo;

import com.mayousheng.www.basepojo.BasePoJo;
import com.mayousheng.www.basepojo.FieldDesc;

import java.util.ArrayList;

public class AllSaveData extends BasePoJo {

    @FieldDesc(key = "version")
    public int version;
    @FieldDesc(key = "datas", arrayType = BaseSaveNode.class)
    public ArrayList<BaseSaveNode> datas;
    @FieldDesc(key = "createAt")
    public long createAt;
    @FieldDesc(key = "updateAt")
    public long updateAt;

    public AllSaveData(String jsonStr) {
        super(jsonStr);
    }

}
