package com.simplelight.app;


public class Code {
	String key = "" ;
	int[] keyCode = null ;
	//构造函数
	Code(String key){
		this.key = key ;
		if(key!=null&&!key.equals("")){
			this.keyCode = new int[key.length()] ;
			for(int i = 0 ; i < keyCode.length ; i++){
				keyCode[i] = this.key.codePointAt(i);
			}
		}
	}
	//编码
	public String encode(String content){		
		StringBuffer text = new StringBuffer();
		for(int i = 0 ,j = 0; i < content.length() ; i++,j++){
			if(keyCode!=null&&keyCode.length!=0){
				if(j>=keyCode.length){
					j = 0 ;
				}
				//相加加密
				int charInt = content.codePointAt(i)+keyCode[j] ;
				//越界判断
				if(charInt>Character.MAX_CODE_POINT){
					charInt = Character.MAX_CODE_POINT ;
				}
				//添加
				text.append((char)charInt);
			}else{
				return content ;
			}
			
		}	
		return text.toString() ;
	}
	//解码
	public String decode(String content){		
		StringBuffer text = new StringBuffer();
		for(int i = 0 ,j = 0; i < content.length() ; i++,j++){
			if(keyCode!=null&&keyCode.length!=0){
				if(j>=keyCode.length){
					j = 0 ;
				}
				//想减解码
				int charInt = content.codePointAt(i)-keyCode[j] ;
				//临界判断
				if(charInt<0){
					charInt = 0 ;
				}
				//添加
				text.append((char)charInt);
			}else{
				return content ;
			}
		}	
		return text.toString() ;
	}
}
