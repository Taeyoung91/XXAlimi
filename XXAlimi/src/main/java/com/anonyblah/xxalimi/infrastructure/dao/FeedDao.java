package com.anonyblah.xxalimi.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonyblah.xxalimi.domain.model.entity.Feed;

/**
 * Feed Data Access Object, JPA Repository(Data�� ����, Data�� PrimaryŰ�� ����)�� ��ӹ���
 * @see Feed
 */
public interface FeedDao extends JpaRepository<Feed, String> {

}
