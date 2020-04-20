package com.movierank.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.movierank.domain.MovieDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MongoDAO {
	
	@Autowired
	private MongoOperations mongoOper; //sqlsession같은 존재
	
	public void save(MovieDTO mDto) {
		log.info(">>>>>>>>>>>> 영화 랭킹정보 MongoDB에 저장");
		mongoOper.save(mDto);
		//mDto값을 통해서 들어감. 
	}
	
	public void dropCol() {
		log.info(">>>>>> Collection Drop");
		mongoOper.dropCollection("movie");
		
	}
	
	public List<MovieDTO> movieList(){
		log.info(">>>>>>>>>>>> 영화 랭킹정보 MongoDB에서  Select");
		Criteria cri = new Criteria(); // Creiteria(key) // cri.is(value)
		// SELECT * FROM movie
		// WHERE key = value;
		Query query = new Query(cri); 
		//Query를 하려면 위의 두가지가 필요함. criteria = key값 Query = value값
		List<MovieDTO> list = mongoOper.find(query, MovieDTO.class, "movie");
		//query가 있어야함. type도 써줘야함(resultType = MovieDTO) 
		//MovieDTO List에 넣어랑
		// = select * from movie 랑 같음
		for(MovieDTO one : list) {
			log.info(one.toString());
		}
		return list;
		}
	}
