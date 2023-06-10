package com.aj.casino2.player.module.security.service;

import java.security.acl.Acl;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aj.casino2.player.contant.EntityConstant;
import com.aj.casino2.player.contant.PlayerConstant;
import com.aj.casino2.player.dao.entity.EntityHbmDaoInf;
import com.aj.casino2.player.dao.player.PlayerHbmDaoInf;
import com.aj.casino2.player.module.security.constant.PlayerErrorConstant;
import com.aj.casino2.player.module.security.constant.PlayerUserAclConstant;
import com.aj.db.domain.user.DomainSetting;
import com.aj.db.domain.user.Player;
import com.aj.module.security.dto.UserDetail;
import com.aj.module.security.exception.PlayerLoginException;
import com.aj.module.security.service.UserDetailsService;

public class PlayerUserDetailsServiceImpl implements UserDetailsService {

	protected PlayerHbmDaoInf playerHbmDao;
	protected EntityHbmDaoInf entityHbmDao;
	
	public void setPlayerHbmDao(PlayerHbmDaoInf playerHbmDao) {
		this.playerHbmDao = playerHbmDao;
	}

	public void setEntityHbmDao(EntityHbmDaoInf entityHbmDao) {
		this.entityHbmDao = entityHbmDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		try{

			System.out.println("PlayerUserDetailsServiceImpl.loadUserByUsername()");
			if(RequestContextHolder.getRequestAttributes() == null) {
				throw new PlayerLoginException(PlayerErrorConstant.CODE_ERROR_ENTITY+","+PlayerErrorConstant.ERROR_ENTITY);
			}

			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			DomainSetting domainSetting = validateEntityByRequestUrl(req);

			if(domainSetting==null) {
				throw new PlayerLoginException(PlayerErrorConstant.CODE_ERROR_USER_NOT_FOUND+","+PlayerErrorConstant.ERROR_USER_NOT_FOUND);
			}



			Player usr = playerHbmDao.getPlayerByUsername(username,domainSetting.getEntity().getId());

			if (usr == null) {
				throw new PlayerLoginException(PlayerErrorConstant.CODE_ERROR_USER_NOT_FOUND+","+PlayerErrorConstant.ERROR_USER_NOT_FOUND);
			}

			if(usr.getStatus().compareTo(PlayerConstant.PLAYER_STATUS_PENDING) == 0) {
				throw new PlayerLoginException(PlayerErrorConstant.CODE_ERROR_USER_VERIFICATION+","+PlayerErrorConstant.ERROR_USER_VERIFICATION);
			}



			String entityIdStr ="";
			if(usr.getEntity()!=null){
				entityIdStr = ":"+usr.getEntity().getId().toString();
			}

			UserDetail usrDetail = new UserDetail();
			usrDetail.setId(usr.getId());
			usrDetail.setUsername(usr.getName()+entityIdStr);
			usrDetail.setPassword(usr.getPassword());
//			usrDetail.setOrganization(usr.getRole().getId().toString());
			usrDetail.setLoginUsername(usr.getName());
			usrDetail.setEntity(usr.getEntity());
			usrDetail.setExtPlayerId(usr.getRefPlayerId());

//
//			GrantedAuthority[] authorities = new GrantedAuthorityImpl[roleList.size()];
//			int i =0;
//			for(Acl acl:roleList){
//				GrantedAuthority authority = new GrantedAuthorityImpl(acl.getName());
//				authorities[i] = authority;
//				i++;
//			}
//			usrDetail.setAuthorities(authorities);

			return usrDetail;

		}catch(PlayerLoginException ex){
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new PlayerLoginException(PlayerErrorConstant.CODE_ERROR_USER_EX+","+PlayerErrorConstant.ERROR_USER_EX);
		}
	}

	private DomainSetting validateEntityByRequestUrl(HttpServletRequest req)throws Exception{

		String requestURL = req.getServerName().toString();//www.abc.com
		return entityHbmDao.getEntityDomainByDomain(requestURL, EntityConstant.DOMAIN_TYPE_PLAYER);


	}

	public List<Acl> getUserAuthority(Long userId,Long roleId,String userAccountType)throws Exception{
		//check role login
		boolean flag = false;
		for(Long checkRoleId:getLoginRoles()){
			if(checkRoleId.compareTo(roleId)==0){
				flag = true;
				break;
			}
		}
		if(!flag){
			throw new Exception("error role login");
		}

		//TODO:
//		List<Acl> aclList = new ArrayList<Acl>();
//		Acl acl = new Acl();
//		acl.setName("ACL_USER");
//		aclList.add(acl);

//		return aclList;
		
		return null;
	}

	protected Long[] getLoginRoles() {
		return new Long[]{PlayerUserAclConstant.ROLE_PLAYER_ID};
	}

}
