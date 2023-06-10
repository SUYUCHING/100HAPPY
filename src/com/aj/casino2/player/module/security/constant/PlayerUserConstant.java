package com.aj.casino2.player.module.security.constant;

public class PlayerUserConstant {

	public static final Long SYSTEM_USER_ID= 1L;
	
	public static final Integer USER_VALID_STATUS=1;
	public static final Integer USER_INVALID_STATUS=0;
	public static final Integer USER_DELETE_STATUS=-1;
	public static final Integer USER_BAN_STATUS=-2;
	public static final Integer USER_REJECTED_STATUS=-10;
	public static final Integer USER_PENDING_STATUS=10;

	//acl type
	public static final Integer ACL_TYPE_VIEW=10;
	public static final Integer ACL_TYPE_ACTION=20;

	//userRole
	public static final Integer USERROLE_VALID_STATUS=1;
	public static final Integer USERROLE_DELETE_STATUS=0;

	//userAcl
	public static final Integer USERACL_VALID_STATUS=1;
	public static final Integer USERACL_DELETE_STATUS=0;

	//entity
	public static final Integer ENTITY_VALID_STATUS=1;
	public static final Integer ENTITY_DELETE_STATUS=0;

	public static final Integer ENTITY_TYPE_ADMIN = -1;
	public static final Integer ENTITY_TYPE_DEFAULT = 0;
	public static final Integer ENTITY_TYPE_PARENT = 1;
	public static final Integer ENTITY_TYPE_CHILD = 2;
	public static final Integer ENTITY_TYPE_GROUP = 3;

	//account type
	public static final String USR_ACC_TYPE_MAIN="usr.acc.type.main";
	public static final String USR_ACC_TYPE_SUB="usr.acc.type.sub";
	
	//admin domain type
	public static final Integer ADMIN_DOMAIN_TYPE=10;

}
