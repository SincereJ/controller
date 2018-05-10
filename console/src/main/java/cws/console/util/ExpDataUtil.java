package cws.console.util;

import java.util.ArrayList;
import java.util.List;

public class ExpDataUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List splitList(List restList) {
		List rest = new ArrayList();
		long sz = restList.size();
		
		if(sz > ExpConfig.MAX_ITEM_NUM) {
			rest.add(restList.subList(0, (int)ExpConfig.MAX_ITEM_NUM));
			//rest.add(restList.subList(((int)ExpConfig.MAX_ITEM_NUM)+1, restList.size()));		
			rest = listSplitor(restList.subList(((int)ExpConfig.MAX_ITEM_NUM)+1, restList.size()), rest);
		}else {
			rest.add(restList);
		}

		return rest;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List listSplitor(List restList,List rest) {
		long sz = restList.size();
		
		if(sz > ExpConfig.MAX_ITEM_NUM) {
			rest.add(restList.subList(0, (int)ExpConfig.MAX_ITEM_NUM));
			listSplitor(restList.subList(((int)ExpConfig.MAX_ITEM_NUM)+1, restList.size()),rest);
		}else {
			rest.add(restList);
		}

		return rest;
	}
	
}
