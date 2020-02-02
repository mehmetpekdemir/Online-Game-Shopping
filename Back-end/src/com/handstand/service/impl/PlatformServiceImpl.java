package com.handstand.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.handstand.dao.impl.PlatformDaoImpl;
import com.handstand.entity.Platform;
import com.handstand.service.PlatformService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public class PlatformServiceImpl implements PlatformService {

	@Override
	public List<Platform> listPlatform() throws ClassNotFoundException, SQLException {
		PlatformDaoImpl platformDao = new PlatformDaoImpl();
		return platformDao.listPlatform();
	}

	@Override
	public Platform findPlatform(int id) throws ClassNotFoundException, SQLException {
		PlatformDaoImpl platformDao = new PlatformDaoImpl();
		return platformDao.findPlatform(id);
	}

	@Override
	public void addPlatform(Platform platform) throws ClassNotFoundException, SQLException {
		PlatformDaoImpl platformDao = new PlatformDaoImpl();
		platformDao.addPlatform(platform);
	}

	@Override
	public void updatePlatform(Platform platform) throws ClassNotFoundException, SQLException {
		PlatformDaoImpl platformDao = new PlatformDaoImpl();
		platformDao.updatePlatform(platform);
	}

}
