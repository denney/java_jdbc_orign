package baseFrame.WorkDAO;


import baseFrame.BaseDAO;
import baseFrame.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */
public class EsStorageDao extends BaseDAO {

    public static List<String> findTableNameList() throws Exception {

        Connection con = DBUtil.getConH21();
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            list.add(rs.getString("TABLE_NAME"));

        }
        DBUtil.release(rs, pstmt, con);
        return list;


    }



    public static  String getSql() throws Exception {
        List<String> list=findTableNameList();
        String s="";
        for (int i = 0; i <list.size() ; i++) {
            String sql="CALL CSVWRITE('/home/H2Data/ES_STORAGE/"+list.get(i)+"DM_APPLICATION.csv', 'SELECT * FROM "+list.get(i)+"');";
            s=sql+s;
        }

        return s;
    }


}
