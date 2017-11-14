package baseFrame;



import java.sql.Connection;
import java.sql.PreparedStatement;


public class BaseDAO {


	
	
	public int executeSQL(String sql,Object[] objs,Connection con ) throws Exception{

		PreparedStatement pstmt = con.prepareStatement(sql);
		if(objs!=null && objs.length!=0){
			for (int i = 0; i < objs.length; i++) {
				pstmt.setObject(i + 1, objs[i]);
				//void setObject(int parameterIndex,Object x)
			

			}
		}
		int result = pstmt.executeUpdate();
		
		 
		DBUtil.release(pstmt,con);
		return result;
	}
	
	public boolean update(String sql,Object[] objs,Connection con) throws Exception{
		int result = executeSQL(sql, objs,con);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
}
