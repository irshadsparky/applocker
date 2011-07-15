package com.gueei.applocker;

import gueei.binding.Binder;
import gueei.binding.Command;
import gueei.binding.Observable;
import gueei.binding.observables.StringObservable;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class LockScreenActivity extends Activity {
	public static final String BlockedPackageName = "locked package name";
	public static final String BlockedActivityName = "locked activity name";
	public static final String ACTION_APPLICATION_PASSED = "com.gueei.applocker.applicationpassedtest";
	public static final String EXTRA_PACKAGE_NAME = "com.gueei.applocker.extra.package.name";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Wallpaper.set(WallpaperManager.getInstance(this).getFastDrawable());
	    Binder.setAndBindContentView(this, R.layout.lockscreen, this);
	}
	
	public final Observable<Drawable> Wallpaper = new Observable<Drawable>(Drawable.class);
	public final Command Number0 = new NumberCommand(0);
	public final Command Number1 = new NumberCommand(1);
	public final Command Number2 = new NumberCommand(2);
	public final Command Number3 = new NumberCommand(3);
	public final Command Number4 = new NumberCommand(4);
	public final Command Number5 = new NumberCommand(5);
	public final Command Number6 = new NumberCommand(6);
	public final Command Number7 = new NumberCommand(7);
	public final Command Number8 = new NumberCommand(8);
	public final Command Number9 = new NumberCommand(9);
	public final Command Clear = new Command(){
		@Override
		public void Invoke(View view, Object... args) {
			Password.set("");
		}
	};
	public final Command Verify = new Command(){
		@Override
		public void Invoke(View view, Object... args) {
			if (verifyPassword()){
				test_passed();
			}else{
				Password.set("");
			}
		}
	};

	private void test_passed() {
		this.sendBroadcast(
				new Intent()
					.setAction(ACTION_APPLICATION_PASSED)
					.putExtra(
							EXTRA_PACKAGE_NAME, getIntent().getStringExtra(BlockedPackageName)));
		finish();
	}
    
    public boolean verifyPassword(){
    	if (Password.get() == null) return false;
    	return Password.get().equals(AppLockerPreference.getInstance(this).getPassword());
    }
    
    public final StringObservable Password = new StringObservable();

    @Override
	public void onBackPressed() {
    	Intent intent = new Intent();
    	intent
    		.setAction(Intent.ACTION_MAIN)
    		.addCategory(Intent.CATEGORY_HOME)
    		.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(intent);
    	finish();
	}

    private class NumberCommand extends Command{
    	private int mNumber;
    	public NumberCommand(int number){
    		mNumber = number;
    	}
		@Override
		public void Invoke(View view, Object... args) {
			Password.set(Password.get() + mNumber);
		}
    }
}
