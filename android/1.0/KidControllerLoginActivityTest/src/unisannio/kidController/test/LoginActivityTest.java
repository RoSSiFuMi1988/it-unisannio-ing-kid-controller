package unisannio.kidController.test;

import unisannio.kidController.LoginActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

	private LoginActivity l;
	private EditText username,password;
	private Button login;
	private CheckBox rememberPassword;
	private static final String USERNAME="Username",
			PASSWORD="Password",
			BUTTON="Login",
			CHECK_BOX="Remember password",
			PROVA1 = "try";
	
	public LoginActivityTest(){
		super("unisannio.kidController.LoginActivity"	, LoginActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		setActivityInitialTouchMode(false);//altrimenti se invio degli eventi riguardanti il touchpad verranno ignorati
		l = getActivity();
		username = (EditText) l.findViewById(unisannio.kidController.R.id.usernameEditText);
		password = (EditText) l.findViewById(unisannio.kidController.R.id.PasswordEditText);
		login = (Button) l.findViewById(unisannio.kidController.R.id.loginButton);
		rememberPassword = (CheckBox) l.findViewById(unisannio.kidController.R.id.rememberPasswordCheckBox);
		
	}

	//controllo delle precondizioni
	public void testPreCondition(){
		assertTrue(username != null);
		assertTrue(password != null);
		assertTrue(login != null);
		assertTrue(rememberPassword != null);
		assertEquals(USERNAME	, username.getText().toString());
		assertEquals(PASSWORD, password.getText().toString());
		assertEquals(BUTTON, login.getText().toString());
		assertEquals(CHECK_BOX, rememberPassword.getText().toString());
		assertEquals(false, rememberPassword.isChecked());
		
		
	}
	
	public void testActivityUi(){
		
		//username richiede il focus
		l.runOnUiThread(new Runnable() {
			public void run() {
			username.requestFocusFromTouch();
			username.setSelected(true);
			}
		});
		
		this.sendKeys(KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL);
		this.sendKeys(KeyEvent.KEYCODE_T, 
				KeyEvent.KEYCODE_R, 
				KeyEvent.KEYCODE_Y);
		assertEquals(PROVA1	, username.getText().toString());
		
		//password richiede il focus
		l.runOnUiThread(new Runnable() {
			public void run() {
				password.requestFocusFromTouch();
				password.setSelected(true);
			}
		});
		
		this.sendKeys(KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL,
				KeyEvent.KEYCODE_DEL);
		this.sendKeys(KeyEvent.KEYCODE_T, 
				KeyEvent.KEYCODE_R,
				KeyEvent.KEYCODE_Y);
		assertEquals(PROVA1, password.getText().toString());
		
		//la checkbox richiede il focus
		l.runOnUiThread(new Runnable() {
			public void run() {
				rememberPassword.requestFocusFromTouch();
			}
		});	
		this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
		assertEquals(BUTTON, login.getText().toString());
		assertTrue(rememberPassword.isChecked());
		
		//la checkbox richiede il focus
				l.runOnUiThread(new Runnable() {
					public void run() {
						rememberPassword.requestFocusFromTouch();
						
						rememberPassword.setSelected(true);
					}
				});	
				this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
				assertEquals(BUTTON, login.getText().toString());
				assertTrue(!rememberPassword.isChecked());
		
		
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
		l=null;
	}


	
	public void testComponentValue(){
		
	}
	
}
