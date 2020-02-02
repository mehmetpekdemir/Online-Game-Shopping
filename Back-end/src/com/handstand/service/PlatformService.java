package com.handstand.service;

import java.sql.SQLException;
import java.util.List;

import com.handstand.entity.Platform;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface PlatformService {

	public List<Platform> listPlatform() throws ClassNotFoundException, SQLException;

	public Platform findPlatform(int id) throws ClassNotFoundException, SQLException;

	public void addPlatform(Platform platform) throws ClassNotFoundException, SQLException;

	public void updatePlatform(Platform platform) throws ClassNotFoundException, SQLException;
}
