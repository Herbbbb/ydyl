package base;

import android.content.Context;
import android.content.SharedPreferences;

import util.Constants;

/**
* Describe:     sharepreference属性存储
* User:         LF
* Date:         2016/3/18 16:32
*/
public class OptsharepreInterface {

	private SharedPreferences settings; // static

	public OptsharepreInterface(Context context) {
		// 载入配置文件
		settings = context.getSharedPreferences(Constants.SHARE_FILES,
				Context.MODE_PRIVATE);
	}

	public SharedPreferences.Editor getEditor() {
		return settings.edit();
	}

	public void putPres(String optName, String values) {
		SharedPreferences.Editor editor = settings.edit();
		if (optName.equals("guid")) {
			editor.putString("guid", values);// 登录人guid
		} else if (optName.equals("username")) {
			editor.putString("username", values);// 登录人userid
		} else if (optName.equals("userid")) {
			editor.putString("userid", values);// 登录人名称
		}  else if (optName.equals("nickname")) {
			editor.putString("nickname", values);// 昵称
		} else if (optName.equals("password")) {
			editor.putString("password", values);// 密码
		} else if (optName.equals("isRemPwd")) {
			editor.putString("isRemPwd", values);// 是否记住密码
		} else if (optName.equals("account")) {
			editor.putString("account", values);// 登录账号
		} else if (optName.equals("versionNo")) {
			editor.putString("versionNo", values);// 版本号
		} else if (optName.equals("mobileNumber")) {
			editor.putString("mobileNumber", values);// 登录手机号
		} else if (optName.equals("state")) {
			editor.putString("state", values);// 状态
		} else {
			editor.putString(optName, values);
		}
		
		
		editor.commit();
	}

	public String getPres(String optName) {
		String values = "";
		if (optName.equals("guid")) {// 获取登陆人唯一标识
			values = settings.getString("guid", "");
		} else if (optName.equals("isRemPwd")) {// 获取是否记忆密码参数
			values = settings.getString("isRemPwd", "0");
		} else if (optName.equals("password")) {// 获取密码参数
			values = settings.getString("password", "");
		} else if (optName.equals("account")) {// 获取账号
			values = settings.getString("account", "");
		} else if (optName.equals("username")) {// 获取用户名
			values = settings.getString("username", "");
		}  else if (optName.equals("nickname")) {// 获取昵称
			values = settings.getString("nickname", "");
		}else if (optName.equals("mobileNumber")) {//
			values = settings.getString("mobileNumber", "");
		}  else if (optName.equals("state")) {//
			values = settings.getString("state", "");
		} else{
			values= settings.getString(optName, "");
		}
		// System.out.println("读取配置文件操作------" + optName + "---" + values);
		return values;
	}

	public boolean existResult(String result) {
		return settings.contains(result);
	}

	public void removePre(String preName) {
		// 必须马上提交，否则就删不了？？！
		settings.edit().remove(preName).commit(); // .commit()
		// settings.edit().commit();
	}

}
