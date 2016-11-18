package org.sagittarius90.api.utils;

import org.hashids.Hashids;

public class IdUtils {

	public static String encodeId(Long id) {
		Hashids h = new Hashids();
		return h.encode(id);
	}
	
	public static long decodeId(String hashId) {
		Hashids h = new Hashids();
		long[] decodedObj = h.decode(hashId);
		
		if (decodedObj.length > 0) {
			return decodedObj[0];
		} else {
			System.out.println("Id not able to decode with Hashids.");
		}
		
		return 0l;
	}
}
