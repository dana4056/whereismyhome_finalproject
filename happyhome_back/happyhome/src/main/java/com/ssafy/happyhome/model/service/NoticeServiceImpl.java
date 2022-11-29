package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.NoticeDao;
import com.ssafy.happyhome.model.dto.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeDao noticeDao;
	
	public NoticeServiceImpl() {}

	public NoticeServiceImpl(NoticeDao dao) {
		noticeDao = dao;
	}

	@Override
	public int insert(Notice notice) throws SQLException {
		return noticeDao.insert(notice);
	}

	@Override
	public List<Notice> select(int currentPage, int numPerPage) throws SQLException {
		return noticeDao.select(currentPage, numPerPage);
	}

	@Override
	public Notice getNotice(int id) throws SQLException {
		return noticeDao.getNotice(id);
	}

	@Override
	public int deleteNotice(int id) throws SQLException {
		return noticeDao.deleteNotice(id);
	}

	@Override
	public int update(Notice notice) throws SQLException {
		return noticeDao.update(notice);
	}

	@Override
	public int getSize() throws SQLException {
		return noticeDao.getSize();
	}
	
	
	@Override
	public List<Notice> selectpin() throws SQLException {
		return noticeDao.selectpin();
	}
	
	@Override
	public int getPinSize() throws SQLException {
		return noticeDao.getPinSize();
	}

	@Override
	public List<Notice> search(int currentPage, int numPerPage, String keyword) throws SQLException {
		return noticeDao.search(currentPage, numPerPage, keyword);
	}


}
