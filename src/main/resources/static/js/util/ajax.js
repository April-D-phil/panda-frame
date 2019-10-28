/**
 * 异步请求封装
 * author: phil
 */
var Ajax = {
		options: {
			type: 'GET',//默认为get请求
			url: '',//请求路径
			async: true,//默认异步
			data: null,//数据
			dataType: 'JSON',//数据类型
			sendData: null,//数据格式处理后
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',//Http请求头的contentType
			beforeSend: function(){},
			success: function(){},
			error: function(){}
		},
		getAjaxObject(){//获取请求对象
			var reqHttp;
			//为了应对所有现代浏览器，检查是否支持XMLHttpRequst，若支持则创建XMLHttpRequest对象，若不支持，则创建ActiveXObject对象
			if (window.XMLHttpRequest) {
				reqHttp = new XMLHttpRequest();
			} else {
				reqHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			return reqHttp;
		},
		convertData(data){//请求参数格式转换
			if(typeof data === 'object'){
				var reqData = [];
				for(var key in data){
					reqData.push(key + "=" + data[key]);
				}
				return reqData.join("&");
			}else{
				return data;
			}
		},
		setPostParams(reqHttp){//post请求设置
			reqHttp.setRequestHeader("Content-type", Ajax.options.contentType);//想请求添加Http请求头
			Ajax.options.sendData = Ajax.convertData(Ajax.options.data);
			return reqHttp;
		},
		setHttpRequest(){//请求对象参数设置
			var reqHttp = Ajax.getAjaxObject();
			reqHttp.open(Ajax.options.type, Ajax.options.url, Ajax.options.async); //规定请求的类型、URL、是否异步请求
			//添加请求头必须在open打开之后，send之前
			if(Ajax.options.type == "POST"){
				Ajax.setPostParams(reqHttp);
			}
			return reqHttp;
		},
		sendRequest(){//发送请求
			var reqHttp = Ajax.setHttpRequest();
			Ajax.options.beforeSend();//发送前事件触发
			reqHttp.send(Ajax.options.sendData);//将请求和数据发送到服务器
			reqHttp.onreadystatechange = function(){
				if(reqHttp.readyState === XMLHttpRequest.DONE){//请求完成
					Ajax.callback(reqHttp);
				}
			}
		},
		callback(reqHttp){//请求完成后，触发回调函数
			if(reqHttp.status === 200){//调用成功
				var result = reqHttp.responseText;
				if(Ajax.options.dataType == 'JSON'){
					result = JSON.parse(result);
				}
				Ajax.options.success(result);
			}else{//调用失败
				Ajax.options.error(reqHttp);
			}
		},
		init(options){//初始化
			//感觉可以优化
			if(options.url!=null && options.url!=undefined){
				Ajax.options.url = options.url;
			}
			if(options.type!=null && options.type!=undefined){
				Ajax.options.type = options.type.toUpperCase();
			}
			if(options.async!=null && options.async!=undefined){
				Ajax.options.async = options.async;
			}
			if(options.data!=null && options.data!=undefined){
				Ajax.options.data = options.data;
			}
			if(options.dataType!=null && options.dataType!=undefined){
				Ajax.options.dataType = options.dataType.toUpperCase();
			}
			if(options.beforeSend!=null && options.beforeSend!=undefined){
				Ajax.options.beforeSend = options.beforeSend;
			}
			if(options.success!=null && options.success!=undefined){
				Ajax.options.success = options.success;
			}
			if(options.error!=null && options.error!=undefined){
				Ajax.options.error = options.error;
			}
			Ajax.sendRequest();//调用触发
		}
}
