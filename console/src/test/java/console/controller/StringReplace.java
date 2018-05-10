package console.controller;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class StringReplace {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		String sql = "select * from user where user_id = ?";
		
		List lst = new ArrayList();
		Map p = new HashMap();
		p.put(0, 'a');
		lst.add(p);
		
		for(int i=0;i<lst.size();i++) {
			Map m = (Map) lst.get(i);
			String val = "'"+(String) m.get(i).toString()+"'";
			sql = sql.replaceFirst("\\?".toString(),val );
		}
		
		
		
		System.out.println(sql);
	}

}
