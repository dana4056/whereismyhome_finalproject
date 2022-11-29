package com.ssafy.happyhome.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhome.model.dao.QnaDao;
import com.ssafy.happyhome.model.dto.Qna;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	QnaDao qnaDao;
	
	public QnaServiceImpl() {}

	public QnaServiceImpl(QnaDao qna) {
		qnaDao = qna;
	}

	@Override
	public int insert(Qna qna) throws SQLException {
		return qnaDao.insert(qna);
	}
	
	@Override
	public List<Qna> select(int currentPage, int numPerPage) throws SQLException {
		return qnaDao.select(currentPage, numPerPage);
	}
	
	@Override
	public List<Qna> selectbygood(int currentPage, int numPerPage) throws SQLException {
		return qnaDao.selectbygood(currentPage, numPerPage);
	}
	
	@Override
	public List<Qna> selectbyview(int currentPage, int numPerPage) throws SQLException {
		return qnaDao.selectbyview(currentPage, numPerPage);
	}


	@Override
	public Qna getQna(int id) throws SQLException {
		return qnaDao.getQna(id);
	}

	@Override
	public int deleteQna(int id) throws SQLException {
		return qnaDao.deleteQna(id);
	}

	@Override
	public int update(Qna qna) throws SQLException {
		return qnaDao.update(qna);
	}

	@Override
	public int getSize() throws SQLException {
		return qnaDao.getSize();
	}

	@Override
	public int didgood(String id, int qnaid) throws SQLException {
		return qnaDao.didgood(id, qnaid);
	}

	@Override
	public void plusgood(String id, int qnaid) throws SQLException {
		qnaDao.plusgood(id, qnaid);
	}

	@Override
	public void minusgood(String id, int qnaid) throws SQLException {
		qnaDao.minusgood(id, qnaid);
	}

}
