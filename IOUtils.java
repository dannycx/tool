package danny.com.sale.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流操作，避免多处抛异常
 */
public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				Log.e(tag,e.getMessage());
			}
		}
		return true;
	}
}
