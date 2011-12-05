/**
 * 
 */
package unisannio.kidController;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author roberto
 *
 */
public class LoginActivity extends Activity {

	private static final String LAST_USERNAME = "LAST_USERNAME";
	private static final String LAST_PASSWORD = "LAST_PASSWORD";
	private static final String LAST_CHECK_BOX_STATUS = "LAST_CHECK_BOX_STATUS";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
	}
	
}
