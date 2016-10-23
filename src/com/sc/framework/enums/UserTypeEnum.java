package com.sc.framework.enums;

/**
 * 用户类型
 * 
 * @author cuibin
 * 
 */
public enum UserTypeEnum {

	merchant(0L) {
		public String value() {
			return "商户";
		}
	},
	employee(1L) {
		public String value() {
			return "员工";
		}
	},
	distributor(2L) {
		public String value() {
			return "员工";
		}
	};

	private UserTypeEnum(long id) {
		this.id = id;
	}

	public static UserTypeEnum valueForState(Long lg) {
		for (UserTypeEnum handle : UserTypeEnum.values()) {
			if (handle.id == lg) {
				return handle;
			}
		}
		return null;
	}

	public long id() {
		return id;
	}

	public abstract String value();

	private long id;
}
