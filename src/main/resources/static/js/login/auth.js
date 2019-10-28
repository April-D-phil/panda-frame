/**
 * 登录验证
 * author: phil
 */
var Auth = {
		vars:{
			flag: 'LOGIN',//标志：LOGIN为登录，REGISTER为注册
			
			//登录信息
			login_email: document.getElementById('login-email'),
			login_password: document.getElementById('login-password'),
			login_email_tip: document.getElementById('login-email-tip'),
			login_password_tip: document.getElementById('login-password-tip'),
			login_btn: document.getElementById('login-btn'),
			
			//注册信息
			username: document.getElementById('username'),
			email: document.getElementById('email'),
			password: document.getElementById('password'),
			confirm_password: document.getElementById('confirm-password'),
			name_tip: document.getElementById('name-tip'),
			email_tip: document.getElementById('email-tip'),
			password_tip: document.getElementById('password-tip'),
			confirm_password_tip: document.getElementById('confirm-password-tip'),
			register_btn: document.getElementById('register-btn'),
			
			options: {
				send: function(){}//验证通过后，自定义后续逻辑
			}
		},
		setDomInnerText(dom, value){//设置dom元素的显示内容
			dom.innerText = value;
		},
		selectDomAndSetText(dom1, dom2, value){//根据标志，判断需要设置哪一个dom元素的显示内容
			if(Auth.vars.flag=='LOGIN'){//登录
				Auth.setDomInnerText(dom1, value);
			}else{//注册
				Auth.setDomInnerText(dom2, value);
			}
		},
		checkUsername(name){//检查用户名称
			if(name.length>0){
				Auth.setDomInnerText(Auth.vars.name_tip, "");
				return true;
			}else{
				Auth.setDomInnerText(Auth.vars.name_tip, "姓名长度必须大于0!");
				return false;
			}
		},
		checkEmail(str){//检验邮箱格式
			var reg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
			if(reg.test(str)){
				Auth.selectDomAndSetText(Auth.vars.login_email_tip, Auth.vars.email_tip, "");
				return true;
			}else{
				Auth.selectDomAndSetText(Auth.vars.login_email_tip, Auth.vars.email_tip, "邮箱格式不对!");
				return false;
			}
		},
		checkPassword(password){//检验密码长度
			if(password.length>=6 && password.length<=10){
				Auth.selectDomAndSetText(Auth.vars.login_password_tip, Auth.vars.password_tip, "");
				return true;
			}else{
				Auth.selectDomAndSetText(Auth.vars.login_password_tip, Auth.vars.password_tip, "密码长度在6-10之间!");
				return false;
			}
		},
		checkConfirmPassword(password, confirmPassword){//检查密码是否一致
			if(password==confirmPassword){
				Auth.setDomInnerText(Auth.vars.confirm_password_tip, "");
				return true;
			}else{
				Auth.setDomInnerText(Auth.vars.confirm_password_tip, "确认密码需要跟密码一致！");
				return false;
			}
		},
		signIn(e){//登录事件
			var checkBoolean = Auth.checkLogin();
			if(checkBoolean){
				Auth.vars.options.send();
			}
		},
		checkLogin(){//登录检验
			var email = Auth.vars.login_email.value;
			var password = Auth.vars.login_password.value;
			//检查
			var emailFormat = Auth.checkEmail(email);
			var passwordFormat = Auth.checkPassword(password);
			return emailFormat && passwordFormat;
		},
		register(e){//注册用户
			var checkBoolean = Auth.checkRegister();
			if(checkBoolean){
				Auth.vars.options.send();
			}
		},
		checkRegister(e){//检查注册内容
			var username = Auth.vars.username.value;
			var email = Auth.vars.email.value;
			var password = Auth.vars.password.value;
			var confirm_password = Auth.vars.confirm_password.value;
			//检查
			var usernameFormat = Auth.checkUsername(username);
			var emailFormat = Auth.checkEmail(email);
			var passwordFormat = Auth.checkPassword(password);
			var confirmFormat = Auth.checkConfirmPassword(password, confirm_password);
			return usernameFormat && emailFormat && passwordFormat && confirmFormat;
		},
		initLogin(options){//初始化登录功能
			Auth.vars.options.send = options.send;
			//绑定登录事件
			Auth.vars.login_btn.addEventListener('click',(e)=>{
				Auth.signIn(e);
			});
		},
		initRegister(options){//初始化注册功能
			Auth.vars.options.send = options.send;
			Auth.vars.flag = 'REGISTER';//注册标志
			//绑定注册事件
			Auth.vars.register_btn.addEventListener('click',(e)=>{
				Auth.register(e);
			});
		}
}