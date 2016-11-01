package com.sc.cmk.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sc.framework.utils.DateConvertUtils;

/**
 * @ingroup system
 * @author  
 * @brief   类简单描述
 *
 * 类功能详细描述
 */
public class Parent implements java.io.Serializable
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	///alias
	public static final String TABLE_ALIAS = "Parent";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_PASSWORD = "password";
	public static final String ALIAS_MAIL = "mail";
	public static final String ALIAS_PHONE = "phone";
	public static final String ALIAS_QUESTION1 = "question1";
	public static final String ALIAS_ANSWER1 = "answer1";
	public static final String ALIAS_QUESTION2 = "question2";
	public static final String ALIAS_ANSWER2 = "answer2";
	public static final String ALIAS_QUESTION3 = "question3";
	public static final String ALIAS_ANSWER3 = "answer3";
	public static final String ALIAS_QUESTION4 = "question4";
	public static final String ALIAS_ANSWER4 = "answer4";
	// 
	public static final String FORMAT_DATE = "yyyy-MM-dd hh:mm:ss";
	
	///date formats
	
	///可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	///columns START
	private java.lang.Integer id;
	private String name;
	private java.lang.String password;
	private java.lang.String mail;
	private java.lang.String phone;
	private java.lang.String question1;
	private java.lang.String answer1;
	private java.lang.String question2;
	private java.lang.String answer2;
	private java.lang.String question3;
	private java.lang.String answer3;
	private java.lang.String question4;
	private java.lang.String answer4;
	private java.util.Date date;
	///columns END

	public Parent(){
	}

	public Parent(
		java.lang.Integer id
	){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(java.lang.Integer value)
    {
		this.id = value;
	}
	
	public java.lang.Integer getId()
    {
		return this.id;
	}
	public void setPassword(java.lang.String value)
    {
		this.password = value;
	}
	
	public java.lang.String getPassword()
    {
		return this.password;
	}
	public void setMail(java.lang.String value)
    {
		this.mail = value;
	}
	
	public java.lang.String getMail()
    {
		return this.mail;
	}
	public void setPhone(java.lang.String value)
    {
		this.phone = value;
	}
	
	public java.lang.String getPhone()
    {
		return this.phone;
	}
	public void setQuestion1(java.lang.String value)
    {
		this.question1 = value;
	}
	
	public java.lang.String getQuestion1()
    {
		return this.question1;
	}
	public void setAnswer1(java.lang.String value)
    {
		this.answer1 = value;
	}
	
	public java.lang.String getAnswer1()
    {
		return this.answer1;
	}
	public void setQuestion2(java.lang.String value)
    {
		this.question2 = value;
	}
	
	public java.lang.String getQuestion2()
    {
		return this.question2;
	}
	public void setAnswer2(java.lang.String value)
    {
		this.answer2 = value;
	}
	
	public java.lang.String getAnswer2()
    {
		return this.answer2;
	}
	public void setQuestion3(java.lang.String value)
    {
		this.question3 = value;
	}
	
	public java.lang.String getQuestion3()
    {
		return this.question3;
	}
	public void setAnswer3(java.lang.String value)
    {
		this.answer3 = value;
	}
	
	public java.lang.String getAnswer3()
    {
		return this.answer3;
	}
	public void setQuestion4(java.lang.String value)
    {
		this.question4 = value;
	}
	
	public java.lang.String getQuestion4()
    {
		return this.question4;
	}
	public void setAnswer4(java.lang.String value)
    {
		this.answer4 = value;
	}
	
	public java.lang.String getAnswer4()
    {
		return this.answer4;
	}
	public String getDateString()
    {
		return DateConvertUtils.format(getDate(), FORMAT_DATE);
	}
	public void setDateString(String value)
    {
		setDate(DateConvertUtils.parse(value, FORMAT_DATE,java.util.Date.class));
	}
	
	public void setDate(java.util.Date value)
    {
		this.date = value;
	}
	
	public java.util.Date getDate()
    {
		return this.date;
	}
    /**
     * @brief   功能: 将Parent对象数据进行串化
     * @param   
     * @return  
     */
	public String toString()
    {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Password",getPassword())
			.append("Mail",getMail())
			.append("Phone",getPhone())
			.append("Question1",getQuestion1())
			.append("Answer1",getAnswer1())
			.append("Question2",getQuestion2())
			.append("Answer2",getAnswer2())
			.append("Question3",getQuestion3())
			.append("Answer3",getAnswer3())
			.append("Question4",getQuestion4())
			.append("Answer4",getAnswer4())
			.toString();
	}
	
    /**
     * @brief   功能: 
     * @param   
     * @return  
     */
	public int hashCode()
    {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
    /**
     * @brief   功能: 检测两个Parent对象是否相同
     * @param   
     * @return  
     */
	public boolean equals(Object obj) {
		if(obj instanceof Parent == false) return false;
		if(this == obj) return true;
		Parent other = (Parent)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

