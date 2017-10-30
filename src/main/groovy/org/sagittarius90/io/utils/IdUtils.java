package org.sagittarius90.io.utils;

import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IdUtils {

	private static IdUtils instance;
	private static Logger logger= LoggerFactory.getLogger(IdUtils.class);

	public static IdUtils getInstance() {
		if (instance == null) {
			instance = new IdUtils();
		}

		return instance;
	}

	protected IdUtils() {

	}

	public String encodeId(Long id) {
		Hashids h = getHashids();
		return h.encode(id);
	}
	
	public long decodeId(String hashId) {
		Hashids h = getHashids();
		long[] decodedObj = h.decode(hashId);
		
		if (decodedObj != null && decodedObj.length > 0) {
			return decodedObj[0];
		} else {
			logger.warn("Id not able to decode with Hashids.");
		}
		
		return 0l;
	}

	protected Hashids getHashids() {
		return new Hashids("HeiseNerds");
	}
}
