package com.sc.cmk.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @ingroup system
 * @author  
 * @brief   类简单描述
 *
 * 类功能详细描述
 */
public class Children implements java.io.Serializable
{
	private static final long serialVersionUID = 5454155825314635342L;
	
	///alias
	public static final String TABLE_ALIAS = "Children";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_SEX = "sex";
	public static final String ALIAS_AGE = "age";
	public static final String ALIAS_PARENT1_ID = "parent1Id";
	public static final String ALIAS_PARENT2_ID = "parent2Id";
	public static final String ALIAS_REMARK = "remark";
	
	///date formats
	
	///可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	///columns START
	private java.lang.Integer id;
	private java.lang.String name;
	private java.lang.String sex;
	private java.lang.Integer age;
	private java.lang.Integer parent1Id;
	private java.lang.Integer parent2Id;
	private java.lang.String remark;
	///columns END

	public Children(){
	}

	public Children(
		java.lang.Integer id
	){
		this.id = id;
	}

	public void setId(java.lang.Integer value)
    {
		this.id = value;
	}
	
	public java.lang.Integer getId()
    {
		return this.id;
	}
	public void setName(java.lang.String value)
    {
		this.name = value;
	}
	
	public java.lang.String getName()
    {
		return this.name;
	}
	public void setSex(java.lang.String value)
    {
		this.sex = value;
	}
	
	public java.lang.String getSex()
    {
		return this.sex;
	}
	public void setAge(java.lang.Integer value)
    {
		this.age = value;
	}
	
	public java.lang.Integer getAge()
    {
		return this.age;
	}
	public void setParent1Id(java.lang.Integer value)
    {
		this.parent1Id = value;
	}
	
	public java.lang.Integer getParent1Id()
    {
		return this.parent1Id;
	}
	public void setParent2Id(java.lang.Integer value)
    {
		this.parent2Id = value;
	}
	
	public java.lang.Integer getParent2Id()
    {
		return this.parent2Id;
	}
	public void setRemark(java.lang.String value)
    {
		this.remark = value;
	}
	
	public java.lang.String getRemark()
    {
		return this.remark;
	}

    /**
     * @brief   功能: 将Children对象数据进行串化
     * @param   
     * @return  
     */
	public String toString()
    {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Sex",getSex())
			.append("Age",getAge())
			.append("Parent1Id",getParent1Id())
			.append("Parent2Id",getParent2Id())
			.append("Remark",getRemark())
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
     * @brief   功能: 检测两个Children对象是否相同
     * @param   
     * @return  
     */
	public boolean equals(Object obj) {
		if(obj instanceof Children == false) return false;
		if(this == obj) return true;
		Children other = (Children)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

